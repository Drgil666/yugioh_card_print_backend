package project.yugioh.card_print.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * @author Gilbert
 * @date 2022/3/19 17:24
 */
public class CardInfoUtil {
    private static final String MONSTER_ATTRIBUTE = "怪兽";
    private static final String NORMAL_ATTRIBUTE = "通常";
    private static final String EFFECT_ATTRIBUTE = "效果";
    private static final String SPECIAL_SUMMON_ATTRIBUTE = "特殊召唤";
    private static final String CARTOON_ATTRIBUTE = "卡通";
    private static final String UNION_ATTRIBUTE = "同盟";
    private static final String SPIRIT_ATTRIBUTE = "灵魂";
    private static final String FLIP_ATTRIBUTE = "反转";
    private static final String GEMINI_ATTRIBUTE = "二重";
    private static final String RITUAL_ATTRIBUTE = "仪式";
    private static final String TUNER_ATTRIBUTE = "调整";
    private static final String FUSION_ATTRIBUTE = "融合";
    private static final String SYNCHRO_ATTRIBUTE = "同调";
    private static final String XYZ_ATTRIBUTE = "超量";
    private static final String PENDULUM_ATTRIBUTE = "灵摆";
    private static final String LINK_ATTRIBUTE = "连接";
    private static final String MAGIC_ATTRIBUTE = "魔法";
    private static final String EQUIP_ATTRIBUTE = "装备";
    private static final String CONTINUOUS_ATTRIBUTE = "永续";
    private static final String QUICK_PLAY_ATTRIBUTE = "速攻";
    private static final String FIELD_ATTRIBUTE = "场地";
    private static final String TRAP_ATTRIBUTE = "陷阱";
    private static final String COUNTER_ATTRIBUTE = "反击";


    @AllArgsConstructor
    @Getter
    public enum CardType {
        /**
         * 怪兽
         */
        TYPE_MONSTER(1, MONSTER_ATTRIBUTE),
        /**
         * 通常怪兽
         */
        TYPE_NORMAL(2, NORMAL_ATTRIBUTE);
        //TODO:待补充
        private final Integer code;
        private final String name;
    }

    public static final CardType[] CARD_TYPE_LIST = CardType.values();
    public static final HashMap<String, Integer> CARD_TYPE_MAP = getCardTypeMap();

    public static HashMap<String, Integer> getCardTypeMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (CardType cardType : CARD_TYPE_LIST) {
            hashMap.put(cardType.name, cardType.code);
        }
        return hashMap;
    }

    public static Integer getCharacterByName(String name) {
        if (CARD_TYPE_MAP.containsKey(name)) {
            return CARD_TYPE_MAP.get(name);
        }
        return null;
    }
}
