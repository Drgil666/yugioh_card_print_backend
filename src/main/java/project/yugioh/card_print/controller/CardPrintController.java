package project.yugioh.card_print.controller;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.util.PoitlIOUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.yugioh.card_print.service.CardPrintService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Gilbert
 * @date 2022/3/6 16:56
 */
@Controller
@Slf4j
@CrossOrigin (origins = "*")
@RequestMapping ("/api/test")
public class CardPrintController {
    @Resource
    private CardPrintService cardPrintService;
    @Value ("${file.path}")
    private String filePath;
    @Value ("${card.path}")
    private String cardPath;
    @Value ("${template.path}")
    private String templatePath;
    @Value ("${export.path}")
    private String exportPath;
    @Value ("${export.name}")
    private String exportFileName;
    @ResponseBody
    @GetMapping ("/download")
    @ApiOperation (value = "下载文件")
    public void downloadFile (HttpServletResponse response) throws IOException {
        String[] imageSuffix = {".png",".jpg",".jpeg"};
        File desktop = new File (cardPath);
        String[] arr = desktop.list ();
        List<String> cardFileList = new ArrayList<> ();
        for (String fileName : arr) {
            for (String suffix : imageSuffix) {
                if (fileName.endsWith (suffix)) {
                    cardFileList.add (fileName);
                    break;
                }
            }
        }
        log.debug ("文件个数:" + cardFileList.size ());
        cardPrintService.createTemplate (cardFileList.size ());
        int width = 223;
        int height = 325;
        XWPFTemplate template = XWPFTemplate.compile (templatePath).render (new HashMap<String, Object> (10) {{
            for (int i = 0;i < cardFileList.size ();i++) {
                put ("image" + (i + 1),Pictures.ofLocal (cardPath + "/" + cardFileList.get (i)).size (width,height).create ());
            }
        }});
        response.setContentType ("application/octet-stream");
        response.setHeader ("Content-disposition","attachment;filename=\"" + exportFileName + "\"");
        OutputStream out = response.getOutputStream ();
        BufferedOutputStream bos = new BufferedOutputStream (out);
        template.writeAndClose (bos);
        bos.flush ();
        out.flush ();
        PoitlIOUtils.closeQuietlyMulti (template,bos,out);
    }
}
