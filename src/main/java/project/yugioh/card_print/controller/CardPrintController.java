package project.yugioh.card_print.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.util.PoitlIOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.yugioh.card_print.pojo.Card;
import project.yugioh.card_print.service.CardPrintService;
import project.yugioh.card_print.service.CardService;
import project.yugioh.card_print.service.GridFsService;
import project.yugioh.card_print.util.SeleniumUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Gilbert
 * @date 2022/3/6 16:56
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/file")
public class CardPrintController {
    @Resource
    private CardPrintService cardPrintService;
    @Resource
    private CardService cardService;
    @Value("${file.path}")
    private String filePath;
    @Value("${card.path}")
    private String cardPath;
    @Value("${template.path}")
    private String templatePath;
    @Value("${export.path}")
    private String exportPath;
    @Value("${export.name}")
    private String exportFileName;
    @Value("${ydk.suffix}")
    private String ydkSuffix;
    @Resource
    private GridFsService gridFsService;

    @ResponseBody
    @PostMapping("/upload")
    public void uploadFile(@RequestParam(value = "file")
                                   MultipartFile multipartFile,
                           HttpServletResponse response) throws IOException, InterruptedException {
        if (multipartFile.getOriginalFilename().endsWith(ydkSuffix)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
            String lineTxt;
            List<InputStream> imageList = new ArrayList<>();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                System.out.println(lineTxt);
                if (org.apache.commons.lang3.StringUtils.isNumeric(lineTxt)) {
                    Integer cardCode = Integer.parseInt(lineTxt);
                    Card card = cardService.getCardByCode(cardCode);
                    GridFsResource gridFsServiceFile = gridFsService.getFile(card.getImg());
                    if (gridFsServiceFile != null) {
                        imageList.add(gridFsServiceFile.getInputStream());
                    } else {
                        SeleniumUtil.getImage(card.getNwbbsName());
                        FileInputStream inputStream = new FileInputStream(cardPath + "/" + card.getNwbbsName() + ".png");
                        imageList.add(inputStream);
                        MultipartFile file = new MockMultipartFile(card.getCode() + ".png", card.getCode() + ".png",
                                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
                        String mongoId = gridFsService.createFile(file);
                        cardService.updateCard(card.getId(), mongoId);
                    }
                }
            }
            SeleniumUtil.after();
            cardPrintService.createTemplate(imageList.size());
            XWPFTemplate template = cardPrintService.createExport(imageList);
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=\"" + exportFileName + "\"");
            OutputStream out = response.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
            template.writeAndClose(bufferedOutputStream);
            bufferedOutputStream.flush();
            out.flush();
            PoitlIOUtils.closeQuietlyMulti(template, bufferedOutputStream, out);
        }
    }

    @ResponseBody
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response) throws IOException {
        String[] imageSuffix = {".emf", ".wmf", ".pict", ".jpeg", ".png", ".dib", ".gif", ".tiff", ".eps", ".bmp", ".wpg", ".jpg"};
        File desktop = new File(cardPath);
        String[] arr = desktop.list();
        List<String> cardFileList = new ArrayList<>();
        for (String fileName : arr) {
            for (String suffix : imageSuffix) {
                if (fileName.endsWith(suffix)) {
                    cardFileList.add(fileName);
                    break;
                }
            }
        }
        cardPrintService.createTemplate(cardFileList.size());
        int width = 225;
        int height = 328;
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(new HashMap<String, Object>(10) {{
            for (int i = 0; i < cardFileList.size(); i++) {
                put("image" + (i + 1), Pictures.ofLocal(cardPath + "/" + cardFileList.get(i)).size(width, height).create());
            }
        }});
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=\"" + exportFileName + "\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        template.writeAndClose(bufferedOutputStream);
        bufferedOutputStream.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bufferedOutputStream, out);
        log.debug("总文件个数:" + arr.length);
        log.debug("文件个数:" + cardFileList.size());
    }
}
