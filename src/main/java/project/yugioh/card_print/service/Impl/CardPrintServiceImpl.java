package project.yugioh.card_print.service.Impl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.yugioh.card_print.service.CardPrintService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
     */
    @Override
    public void createTemplate(Integer size) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(LEFT_MARGIN);
        pageMar.setRight(RIGHT_MARGIN);
        pageMar.setTop(TOP_MARGIN);
        pageMar.setBottom(BOTTOM_MARGIN);
        // 设置边距
        // 新建段落
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        // 设置左对齐
        for (int i = 1; i <= Math.ceil(size / 3.0); i++) {
            XWPFRun run = paragraph.createRun();
            //创建段落文本
            run.setText("{{@image" + (3 * (i - 1) + 1) + "}}  {{@image" + (3 * (i - 1) + 2) + "}}  {{@image" + (3 * (i - 1) + 3) + "}}");
            if (i != Math.ceil(size / 3.0)) {
                run.addBreak(BreakType.TEXT_WRAPPING);
            }
            if (i % 3 != 0 && i != Math.ceil(size / 3.0)) {
                run.addBreak(BreakType.TEXT_WRAPPING);
            }
        }
        OutputStream out = new FileOutputStream(templateFileName);
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
    public XWPFTemplate createExport(List<InputStream> imageList) {
        int width = 225;
        int height = 328;
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(new HashMap<String, Object>(10) {{
            for (int i = 0; i < imageList.size(); i++) {
                put("image" + (i + 1), Pictures.ofStream(imageList.get(i)).size(width, height).create());
            }
        }});
        return template;
    }
}
