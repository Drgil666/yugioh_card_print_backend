package project.yugioh.card_print.controller;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.util.PoitlIOUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.yugioh.card_print.service.CardPrintService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;
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
    @ResponseBody
    @GetMapping ("/download")
    @ApiOperation (value = "下载文件")
    public void downloadFile (HttpServletResponse response) throws IOException {
        String fileName = "D://card_print_test/test.docx";
        XWPFTemplate template = XWPFTemplate.compile (fileName).render (new HashMap<String, Object> (10) {{
            put ("image1",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image2",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image3",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image4",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image5",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image6",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image7",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image8",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
            put ("image9",Pictures.ofLocal ("D://card_print_test/111.png").size (217,332).create ());
        }});
        response.setContentType ("application/octet-stream");
        response.setHeader ("Content-disposition","attachment;filename=\"" + UUID.randomUUID () + ".docx" + "\"");
        OutputStream out = response.getOutputStream ();
        BufferedOutputStream bos = new BufferedOutputStream (out);
        template.writeAndClose (bos);
        cardPrintService.createFile (null);
        bos.flush ();
        out.flush ();
        PoitlIOUtils.closeQuietlyMulti (template,bos,out);
    }
}
