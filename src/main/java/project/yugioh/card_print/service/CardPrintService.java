package project.yugioh.card_print.service;
import java.io.IOException;
/**
 * @author Gilbert
 * @date 2022/3/6 16:56
 */
public interface CardPrintService {
    /**
     * 创建模板文件
     *
     * @param size 文件大小
     * @throws IOException
     */
    void createTemplate (Integer size) throws IOException;
}
