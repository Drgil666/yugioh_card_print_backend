package project.yugioh.card_print.util;

import project.yugioh.card_print.pojo.Card;
import project.yugioh.card_print.pojo.vo.CardDb;

/**
 * @author Gilbert
 * @date 2022/3/19 16:41
 */
public class CardUtil {
    public static Card cardTransform(CardDb cardDb) {
        Card card = new Card();
        card.setCode(cardDb.getId());
        card.setNwbbsName(cardDb.getCn_name());
        card.setCnocgName(cardDb.getCnocg_n());
        card.setEnName(cardDb.getEn_name());
        card.setJpCode(cardDb.getJp_ruby());
        card.setJpName(cardDb.getJp_name());
        card.setBriefDescription("暂无信息描述");
        card.setPendulumDescription("暂无灵摆效果模数");
        card.setDescription("暂无效果描述");
        if (cardDb.getText() != null) {
            card.setBriefDescription(cardDb.getText().getTypes());
            card.setPendulumDescription(cardDb.getText().getPdesc());
            card.setDescription(cardDb.getText().getDesc());
        }
        card.setOcgLimit(3);
        card.setTcgLimit(3);
        //TODO:禁卡表数量需要手动修改，种族效果属性等也需要代码修正
        if(card.getBriefDescription()!=null){

        }
        if (cardDb.getData() != null) {
            if (cardDb.getData().getAtk() != null) {
                card.setAtk(cardDb.getData().getAtk());
            }
            if (cardDb.getData().getDef() != null) {
                card.setDef(cardDb.getData().getDef());
            }
            card.setLevel(cardDb.getData().getLevel());
            card.setRace(cardDb.getData().getRace());
            card.setAttribute(cardDb.getData().getAttribute());
        }
        return card;
    }
}
