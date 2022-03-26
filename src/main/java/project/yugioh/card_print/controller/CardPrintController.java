package project.yugioh.card_print.controller;

import com.deepoove.poi.XWPFTemplate;
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
import project.yugioh.card_print.service.MailService;
import project.yugioh.card_print.util.SeleniumUtil;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
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
    @Resource
    private MailService mailService;

    @ResponseBody
    @PostMapping("/upload")
    public void uploadFile(@RequestParam(value = "file")
                                   MultipartFile multipartFile,
                           @RequestParam(value = "email")
                                   String email) throws IOException, InterruptedException, MessagingException, IllegalArgumentException {
        if (multipartFile.getOriginalFilename().endsWith(ydkSuffix)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
            String lineTxt;
            List<String> imageList = new ArrayList<>();
            SeleniumUtil.pre();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                System.out.println(lineTxt);
                if (org.apache.commons.lang3.StringUtils.isNumeric(lineTxt)) {
                    Integer cardCode = Integer.parseInt(lineTxt);
                    Card card = cardService.getCardByCode(cardCode);
                    GridFsResource gridFsServiceFile = gridFsService.getFile(card.getImg());
                    if (gridFsServiceFile == null) {
                        SeleniumUtil.getImage(card.getNwbbsName());
                        File file = new File(cardPath, card.getNwbbsName() + ".png");
                        file.renameTo(new File(cardPath, card.getCode() + ".png"));
                        FileInputStream inputStream = new FileInputStream(file);
                        MultipartFile multipartFile1 = new MockMultipartFile(card.getCode() + ".png", card.getCode() + ".png",
                                ContentType.IMAGE_PNG.toString(), inputStream);
                        String mongoId = gridFsService.createFile(multipartFile1);
                        card.setImg(mongoId);
                        cardService.updateCard(card.getId(), mongoId);
                    } else {
                        File file = new File(cardPath, card.getCode() + ".png");
                        if (!file.exists()) {
                            gridFsServiceFile = gridFsService.getFile(card.getImg());
                            InputStream inputStream = gridFsServiceFile.getInputStream();
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            while ((len = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, len);
                            }
                            fileOutputStream.flush();
                        }
                    }
                    imageList.add(card.getCode() + ".png");
                }
            }
            SeleniumUtil.after();
            log.debug("共" + imageList.size() + "张图片！");
            log.debug("正在创建模板文件...");
            cardPrintService.createTemplate(imageList.size());
            log.debug("正在生成文档...");
            XWPFTemplate template = cardPrintService.createExport(imageList);
            log.debug("生成文档成功！");
            OutputStream stream = new FileOutputStream(filePath + "/" + exportFileName);
            template.writeAndClose(stream);
            log.debug("准备发送邮件...");
            mailService.sendMail(new File(filePath, exportFileName), email);
            log.debug("发动成功!");
        }
    }

    @ResponseBody
    @Deprecated
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
        XWPFTemplate template = cardPrintService.createExport(cardFileList);
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
