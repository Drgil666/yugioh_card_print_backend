package project.yugioh.card_print.service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 * @author Gilbert
 * @date 2022/3/6 16:56
 */
public interface CardPrintService {
    void createFile(List<InputStream> inputStreamList) throws IOException;
}
