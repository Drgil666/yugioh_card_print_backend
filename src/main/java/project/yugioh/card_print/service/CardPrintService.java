package project.yugioh.card_print.service;

import com.deepoove.poi.XWPFTemplate;

import java.io.IOException;
import java.util.List;

/**
 * @author Gilbert
 * @date 2022/3/6 16:56
 */
public interface CardPrintService {
    /**
     * 创建模板文件
     *
     * @param size 文件大小
     * @throws IOException IO异常
     * @throws IllegalArgumentException 参数异常
     */
    void createTemplate (Integer size) throws IOException,IllegalArgumentException;

    /**
     * 生成打印文件
     * @param imageList 图片输入流
     * @return 文件
     */
    XWPFTemplate createExport(List<String> imageList);
}
