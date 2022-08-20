package project.yugioh.card_print.util;

import project.yugioh.card_print.pojo.Card;
import project.yugioh.card_print.pojo.vo.CardDb;

import java.util.ArrayList;
import java.util.Arrays;
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
        card.setJpRuby(cardDb.getJp_ruby());
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
        //TODO:禁卡表数量需要手动修改
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
                List<Character> cardTypeCharacter = new ArrayList<>();
                for (String type : cardTypes) {
                    Character character = CardInfoUtil.getCardTypeByName(type);
                    if (character == null) {
                        System.out.println("出现未知字段：" + type);
                    } else {
                        cardTypeCharacter.add(character);
                    }
                    Collections.sort(cardTypeCharacter);
                    StringBuilder temp = new StringBuilder();
                    for (Character character1 : cardTypeCharacter) {
                        temp.append(character1);
                    }
                    card.setType(temp.toString());
                }
                if (cardTypes[0].equals(CardInfoUtil.CardType.TYPE_MONSTER.getName())) {
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
                    if (cardTypeCharacter.contains(CardInfoUtil.CardType.TYPE_LINK.getCode())) {
                        card.setDef(null);
                        //连接怪兽没有守备力
                        //连接怪兽
                        int level = 0;
                        List<Integer> linkNumber=new ArrayList<>();
                        int atkIndex = attributeIndex + 1;
                        for (int i = atkIndex; i < description.length(); i++) {
                            if(description.charAt(i)>='0' && description.charAt(i)<='9') {
                                card.setLevel(description.charAt(i) - '0');
                                atkIndex=i+1;
                                break;
                            }
                        }
                        List<String> linkMark= Arrays.asList("↖","↑","↗","←","o","→","↙","↓","↘");
                        for (int i = atkIndex; i < description.length(); i++) {
                            String mark= String.valueOf(description.charAt(i));
                            if(linkMark.contains(mark)){
                                linkNumber.add(linkMark.indexOf(mark)+1);
                            }
                        }
                        Collections.sort(linkNumber);
                        StringBuilder linkAttribute= new StringBuilder();
                        for(Integer number:linkNumber){
                            linkAttribute.append(number);
                        }
                        card.setLinkMark(linkAttribute.toString());
                    } else {
                        //其他怪兽都有星级或阶级
                        int level = 0;
                        int atkIndex = attributeIndex + 1;
                        for (int i = atkIndex; i < description.length(); i++) {
                            if (description.charAt(i) == '★' || description.charAt(i) == '☆') {
                                int k = i + 1;
                                while (k < description.length() && description.charAt(k)>='0' && description.charAt(k)<='9') {
                                    level = level * 10 + description.charAt(k) - '0';
                                    k++;
                                }
                                i = k;
                                atkIndex = i;
                                card.setLevel(level);
                                for(int j=atkIndex+1;j<description.length();j++){
                                    if(description.charAt(j)>='0' && description.charAt(j)<='9'){
                                        int indexK=j;
                                        int value=0;
                                        while (indexK < description.length() && description.charAt(indexK)>='0' && description.charAt(indexK)<='9') {
                                            value = value * 10 + description.charAt(indexK) - '0';
                                            indexK++;
                                        }
                                        card.setAtk(value);
                                        value=0;
                                        while(indexK < description.length() && (description.charAt(indexK)<'0' || description.charAt(indexK)>'9')){
                                            indexK++;
                                        }
                                        while (indexK < description.length() && description.charAt(indexK)>='0' && description.charAt(indexK)<='9') {
                                            value = value * 10 + description.charAt(indexK) - '0';
                                            indexK++;
                                        }
                                        card.setDef(value);
                                        atkIndex=indexK;
                                        break;
                                    }
                                }
                                if(cardTypeCharacter.contains(CardInfoUtil.CardType.TYPE_PENDULUM.getCode())){
                                    //灵摆怪兽有左右刻度
                                    for(int j=atkIndex;j<description.length();j++){
                                        if(description.charAt(j)>='0' && description.charAt(j)<='9'){
                                            int indexK=j;
                                            int pendulumScale=0;
                                            while (indexK < description.length() && description.charAt(indexK)>='0' && description.charAt(indexK)<='9') {
                                                pendulumScale = pendulumScale * 10 + description.charAt(indexK) - '0';
                                                indexK++;
                                            }
                                            card.setPendulumLeftScale(pendulumScale);
                                            pendulumScale=0;
                                            while(indexK < description.length() && (description.charAt(indexK)<'0' || description.charAt(indexK)>'9')){
                                                indexK++;
                                            }
                                            while (indexK < description.length() && description.charAt(indexK)>='0' && description.charAt(indexK)<='9') {
                                                pendulumScale = pendulumScale * 10 + description.charAt(indexK) - '0';
                                                indexK++;
                                            }
                                            card.setPendulumRightScale(pendulumScale);
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
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
