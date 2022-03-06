package project.yugioh.card_print.service;
import com.deepoove.poi.XWPFTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
/**
 * @author Gilbert
 * @date 2022/3/6 17:01
 */
@Service
public class CardPrintServiceImpl implements CardPrintService {
    private static final String fileName="D://card_print_test/test.docx";
    @Override
    public void createFile (List<InputStream> inputStreamList) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(fileName).render(
                new HashMap<String, Object> (10){{
                    put("image1", "D://card_print_test/111.png");
                }});
        template.writeAndClose(new FileOutputStream ("D://card_print_test/output.docx"));
    }
}
