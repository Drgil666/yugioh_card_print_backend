package project.yugioh.card_print.service.Impl;


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.yugioh.card_print.service.CardPrintService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Gilbert
 * @date 2022/3/6 17:01
 */
@Service
public class CardPrintServiceImpl implements CardPrintService {
    @Value("${template.path}")
    private String templateFileName;
    @Value("${card.path}")
    private String cardPath;
    private static final long LEFT_MARGIN = 568L;
    private static final long RIGHT_MARGIN = 568L;
    private static final long TOP_MARGIN = 284L;
    private static final long BOTTOM_MARGIN = 114L;
    @Value("${template.path}")
    private String templatePath;

    /**
     * 创建模板文件
     *
     * @param size 文件大小
     * @throws IOException              IO异常
     * @throws IllegalArgumentException 参数异常
     */
    @Override
    public void createTemplate(Integer size) throws IOException, IllegalArgumentException {
        XWPFDocument document = new XWPFDocument();
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(LEFT_MARGIN);
        pageMar.setRight(RIGHT_MARGIN);
        pageMar.setTop(TOP_MARGIN);
        pageMar.setBottom(BOTTOM_MARGIN);
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        // 设置边距
        for (int i = 1; i <= Math.ceil(size / 3.0); i++) {
            // 设置左对齐
            XWPFRun run = paragraph.createRun();
            //创建段落文本
            String text = "{{@image" + (3 * (i - 1) + 1) + "}}  {{@image" + (3 * (i - 1) + 2) + "}}  {{@image" + (3 * (i - 1) + 3) + "}}";
            run.setText(text);
            if (i != Math.ceil(size / 3.0)) {
                run.addBreak(BreakType.TEXT_WRAPPING);
            }
            if (i % 3 != 0) {
                run.addBreak(BreakType.TEXT_WRAPPING);
            }
        }
        FileOutputStream out = new FileOutputStream(new File(templateFileName));
        //生成文件
        document.write(out);
        out.close();
    }

    /**
     * 生成打印文件
     *
     * @param imageList 图片输入流
     * @return 文件
     */
    @Override
    public XWPFTemplate createExport(List<String> imageList) {
        int width = 225;
        int height = 328;
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(new HashMap<String, Object>(10) {{
            for (int i = 0; i < imageList.size(); i++) {
                put("image" + (i + 1), Pictures.ofLocal(cardPath + "/" + imageList.get(i))
                        .size(width, height).create());
            }
        }});
        return template;
    }
}
