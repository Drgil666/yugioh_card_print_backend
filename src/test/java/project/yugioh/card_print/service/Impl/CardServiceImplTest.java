package project.yugioh.card_print.service.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.yugioh.card_print.CardPrintApplication;
import project.yugioh.card_print.pojo.Card;
import project.yugioh.card_print.pojo.vo.CardDb;
import project.yugioh.card_print.service.CardService;
import project.yugioh.card_print.util.CardUtil;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Gilbert
 * @date 2022/3/19 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CardPrintApplication.class)
public class CardServiceImplTest {
    @Resource
    private CardService cardService;

    @Test
    public void createCard() {
        File file = new File("C:/Users/25741/Desktop/cards.json");
        StringBuilder stringBuilder = new StringBuilder();
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(System.lineSeparator()).append(s);
                cnt++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        LinkedHashMap<String, CardDb> cardList = gson.fromJson(String.valueOf(stringBuilder),
                new TypeToken<LinkedHashMap<String, CardDb>>() {
                }.getType());
        for (HashMap.Entry<String, CardDb> entry : cardList.entrySet()) {
            CardDb cardDb = entry.getValue();
            //System.out.println(entry.getKey());
            Card card= CardUtil.cardTransform(cardDb);
            cardService.createCard(card);
        }
        System.out.println("读取完成!" + cardList.size());
    }
}