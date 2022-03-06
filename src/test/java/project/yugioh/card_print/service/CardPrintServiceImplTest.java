package project.yugioh.card_print.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.yugioh.card_print.CardPrintApplication;

import javax.annotation.Resource;
import java.io.IOException;

import static org.junit.Assert.*;
/**
 * @author Gilbert
 * @date 2022/3/6 22:20
 */
@RunWith (SpringRunner.class)
@SpringBootTest (classes = CardPrintApplication.class)
public class CardPrintServiceImplTest {
    @Resource
    private CardPrintService cardPrintService;
    @Test
    public void createTemplate () throws IOException {
        cardPrintService.createTemplate (15);
    }
}