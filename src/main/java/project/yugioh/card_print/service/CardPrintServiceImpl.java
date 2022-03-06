package project.yugioh.card_print.service;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
/**
 * @author Gilbert
 * @date 2022/3/6 17:01
 */
@Service
public class CardPrintServiceImpl implements CardPrintService {
    @Value ("${template.path}")
    private String templateFileName;
    /**
     * 创建模板文件
     *
     * @param size 文件大小
     * @throws IOException
     */
    @Override
    public void createTemplate (Integer size) throws IOException {
        XWPFDocument doc = new XWPFDocument ();
        XWPFParagraph paragraph = doc.createParagraph ();
        long LEFT_MARGIN = 568L;
        long RIGHT_MARGIN = 568L;
        long TOP_MARGIN = 284L;
        long BOTTOM_MARGIN = 568L;
        CTSectPr sectPr = doc.getDocument ().getBody ().addNewSectPr ();
        CTPageMar pageMar = sectPr.addNewPgMar ();
        pageMar.setLeft (BigInteger.valueOf (LEFT_MARGIN));
        pageMar.setRight (BigInteger.valueOf (RIGHT_MARGIN));
        pageMar.setTop (BigInteger.valueOf (TOP_MARGIN));
        pageMar.setBottom (BigInteger.valueOf (BOTTOM_MARGIN));
        //设置边距
        // 新建段落
        paragraph.setAlignment (ParagraphAlignment.LEFT);
        // 设置左对齐
        for (int i = 1;i <= size / 3;i++) {
            XWPFRun run = paragraph.createRun ();
            //创建段落文本
            run.setText ("{{@image" + (3 * (i - 1) + 1) + "}}  {{@image" + (3 * (i - 1) + 2) + "}}  {{@image" + (3 * (i - 1) + 3) + "}}");
            run.addBreak (BreakType.TEXT_WRAPPING);
            if (i != size / 3) {
                run.addBreak (BreakType.TEXT_WRAPPING);
            }
        }
        FileOutputStream out = new FileOutputStream (templateFileName);
        //生成文件
        doc.write (out);
        out.close ();
    }
}
