package project.yugioh.card_print.service;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
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
        // 新建一个段落
        paragraph.setAlignment (ParagraphAlignment.CENTER);
        // 设置段落的对齐方式
        //设置左边框
        for (int i = 1;i <= size / 3;i++) {
            XWPFRun r = paragraph.createRun ();
            //创建段落文本
            r.setText ("{{@image" + (3 * (i - 1) + 1) + "}}  {{@image" + (3 * (i - 1) + 2) + "}}  {{@image" + (3 * (i - 1) + 3) + "}}");
        }
        FileOutputStream out = new FileOutputStream (templateFileName);
        doc.write (out);
        out.close ();
    }
}
