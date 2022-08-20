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
import project.yugioh.card_print.util.CardTransformUtil;
import project.yugioh.card_print.util.PdfUtil;
import project.yugioh.card_print.util.SeleniumUtil;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    public void ImportCard() {
        File file = new File("Y:/Users/DrGilbert/Desktop/cards.json");
        StringBuilder stringBuilder = new StringBuilder();
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s;
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
        System.out.println("解析完成，开始录入...");
        for (HashMap.Entry<String, CardDb> entry : cardList.entrySet()) {
            CardDb cardDb = entry.getValue();
            //System.out.println(entry.getKey());
            Card card = CardTransformUtil.cardTransform(cardDb);
            if (cardService.getCardByCode(card.getCode()) == null) {
                cardService.createCard(card);
            } else {
                cardService.updateCard(card);
            }
        }
        System.out.println("读取完成!" + cardList.size());
    }

    @Test
    public void anotherImportCard() {
        CardDb cardDb = new CardDb();
        cardDb.setCnocg_n("召唤神 艾克佐迪亚");
        cardDb.setId(58604027);
        cardDb.setCn_name("召唤神 艾库佐迪亚");
        cardDb.setJp_ruby("しょうかんしんエクゾディア");
        cardDb.setJp_name("召喚神エクゾディア");
        cardDb.setEn_name("The Legendary Exodia Incarnate");
        CardDb.CardText text = new CardDb.CardText();
        text.setDesc("①：支付1000基本分才能发动。对方场上盖放的卡全部确认。");
        text.setTypes("[魔法]");
        cardDb.setText(text);
        CardDb.CardData data = new CardDb.CardData();
        data.setAtk(0);
        data.setAttribute(0);
        data.setRace(0);
        data.setDef(0);
        data.setLevel(0);
        data.setSetcode(0L);
        data.setOt(3);
        cardDb.setData(data);
        Card card = CardTransformUtil.cardTransform(cardDb);
        System.out.println(card);
    }

    @Test
    public void downloadCard() throws InterruptedException {
        SeleniumUtil.pre();
        SeleniumUtil.getImageByCardCode("89631143");
        SeleniumUtil.getImageByCardCode("89631146");
        SeleniumUtil.after();
    }

    @Test
    public void convertToPdf() throws IOException {
        PdfUtil.convertToPdf();
    }
}