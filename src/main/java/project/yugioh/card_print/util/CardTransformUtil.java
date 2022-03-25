package project.yugioh.card_print.util;

import project.yugioh.card_print.pojo.Card;
import project.yugioh.card_print.pojo.vo.CardDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gilbert
 * @date 2022/3/19 16:41
 */
public class CardTransformUtil {
    public static Card cardTransform(CardDb cardDb) {
        Card card = new Card();
        card.setCode(cardDb.getId());
        card.setNwbbsName(cardDb.getCn_name());
        card.setCnocgName(cardDb.getCnocg_n());
        card.setEnName(cardDb.getEn_name());
        card.setJpCode(cardDb.getJp_ruby());
        card.setJpName(cardDb.getJp_name());
        card.setBriefDescription(null);
        card.setPendulumDescription(null);
        card.setDescription(null);
        if (cardDb.getText() != null) {
            card.setBriefDescription(cardDb.getText().getTypes());
            card.setPendulumDescription(cardDb.getText().getPdesc());
            card.setDescription(cardDb.getText().getDesc());
        }
        card.setOcgLimit(3);
        card.setTcgLimit(3);
        //TODO:禁卡表数量需要手动修改，种族效果属性等也需要代码修正
        if (card.getBriefDescription() != null) {
            String description = card.getBriefDescription();
            int quote1Left = 0;
            int quote1Right = 0;
            //第一个括号的位置
            for (int i = 1; i < description.length(); i++) {
                if (description.charAt(i) == ']') {
                    quote1Right = i;
                    break;
                }
            }
            if (quote1Left + 1 <= quote1Right) {
                String quote1 = description.substring(quote1Left + 1, quote1Right);
                String[] cardTypes = quote1.split("\\|");
                List<Character> card_type = new ArrayList<>();
                for (String type : cardTypes) {
                    Character character = CardInfoUtil.getCardTypeByName(type);
                    if (character == null) {
                        System.out.println("出现未知字段：" + type);
                    } else {
                        card_type.add(character);
                    }
                    Collections.sort(card_type);
                    StringBuilder temp = new StringBuilder();
                    for (Character character1 : card_type) {
                        temp.append(character1);
                    }
                    card.setType(temp.toString());
                }
                if (cardTypes[0].equals(CardInfoUtil.CardType.TYPE_MONSTER.getName())) {
                    //TODO:待补充
                    int raceIndex = quote1Right + 2;
                    for (int i = quote1Right + 2; i < description.length(); i++) {
                        if (description.charAt(i) == '/') {
                            raceIndex = i;
                            break;
                        }
                    }
                    String race = description.substring(quote1Right + 2, raceIndex);
                    Integer cardRace = CardInfoUtil.getCardRaceByName(race);
                    if (cardRace != null) {
                        card.setRace(cardRace);
                    }
                    int attributeIndex = raceIndex + 1;
                    for (int i = raceIndex + 1; i < description.length(); i++) {
                        if (description.charAt(i) == '[') {
                            attributeIndex = i - 1;
                            break;
                        }
                    }
                    String attribute = description.substring(raceIndex + 1, attributeIndex);
                    Integer cardAttribute = CardInfoUtil.getCardAttributeByName(attribute);
                    if (cardAttribute != null) {
                        card.setAttribute(cardAttribute);
                    }
                    if (card_type.contains(CardInfoUtil.CardType.TYPE_LINK.getCode())) {
                        //连接怪兽
                    } else {
                        //其他怪兽都有星级或阶级
                        //TODO:需要补全
                        int level = 0;
                        int atkIndex = attributeIndex + 1;
                        for (int i = attributeIndex + 1; i < description.length(); i++) {
                            if (description.charAt(i) == '★' || description.charAt(i) == '☆') {
                                int k = i + 1;
                                while (k < description.length() && description.charAt(k)>='0' && description.charAt(k)<='9') {
                                    level = level * 10 + description.charAt(k) - '0';
                                    k++;
                                }
                                i = k;
                                atkIndex = i;
                                break;
                            }
                        }
                        card.setLevel(level);
                    }
                }
            } else {
                System.out.println("出现异常!" + card.getCode() + " " + card.getNwbbsName() + " " + card.getEnName());
            }
        }
        if (cardDb.getData() != null) {
            if (cardDb.getData().getAtk() != null) {
                card.setAtk(cardDb.getData().getAtk());
            }
            if (cardDb.getData().getDef() != null) {
                card.setDef(cardDb.getData().getDef());
            }
            card.setAttribute(cardDb.getData().getAttribute());
        }
        return card;
    }
}
