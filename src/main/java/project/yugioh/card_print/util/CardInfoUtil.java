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
    private static final String DARK_ATTRIBUTE = "暗";
    private static final String EARTH_ATTRIBUTE = "地";
    private static final String LIGHT_ATTRIBUTE = "光";
    private static final String DIVINE_ATTRIBUTE = "神";
    private static final String WIND_ATTRIBUTE = "风";
    private static final String FIRE_ATTRIBUTE = "炎";
    private static final String WATER_ATTRIBUTE = "水";
    private static final String AQUA_ATTRIBUTE = "水";
    private static final String BEAST_ATTRIBUTE = "兽";
    private static final String BEAST_WARRIOR_ATTRIBUTE = "兽战士";
    private static final String CREATOR_GOD_ATTRIBUTE = "创造神";
    private static final String CYBERSE_ATTRIBUTE = "电子界";
    private static final String DINOSAUR_ATTRIBUTE = "恐龙";
    private static final String DIVINE_BEAST_ATTRIBUTE = "幻神兽";
    private static final String DRAGON_ATTRIBUTE = "龙";
    private static final String FAIRY_ATTRIBUTE = "天使";
    private static final String FISH_ATTRIBUTE = "鱼";
    private static final String INSECT_ATTRIBUTE = "昆虫";
    private static final String MACHINE_ATTRIBUTE = "机械";
    private static final String PSYCHIC_ATTRIBUTE = "念动力";
    private static final String PYRO_ATTRIBUTE = "炎";
    private static final String REPTILE_ATTRIBUTE = "爬虫";
    private static final String ROCK_ATTRIBUTE = "岩石";
    private static final String SEA_SERPENT_ATTRIBUTE = "海龙";
    private static final String SPELL_CASTER_ATTRIBUTE = "魔法师";
    private static final String THUNDER_ATTRIBUTE = "雷";
    private static final String WARRIOR_ATTRIBUTE = "战士";
    private static final String WINGED_BEAST_ATTRIBUTE = "鸟兽";
    private static final String WYRM_ATTRIBUTE = "幻龙";
    private static final String ZOMBIE_ATTRIBUTE = "不死";

    @AllArgsConstructor
    @Getter
    public enum CardType {
        /**
         * 怪兽
         */
        TYPE_MONSTER('1', MONSTER_ATTRIBUTE),
        /**
         * 通常怪兽
         */
        TYPE_NORMAL('2', NORMAL_ATTRIBUTE),
        /**
         * 效果怪兽
         */
        TYPE_EFFECT('3', EFFECT_ATTRIBUTE),
        /**
         * 特殊召唤怪兽
         */
        TYPE_SPECIAL_SUMMON('4', SPECIAL_SUMMON_ATTRIBUTE),
        /**
         * 卡通怪兽
         */
        TYPE_CARTOON('5', CARTOON_ATTRIBUTE),
        /**
         * 同盟怪兽
         */
        TYPE_UNION('6', UNION_ATTRIBUTE),
        /**
         * 灵魂怪兽
         */
        TYPE_SPIRIT('7', SPIRIT_ATTRIBUTE),
        /**
         * 反转怪兽
         */
        TYPE_FLIP('8', FLIP_ATTRIBUTE),
        /**
         * 二重怪兽
         */
        TYPE_GEMINI('9', GEMINI_ATTRIBUTE),
        /**
         * 仪式怪兽
         */
        TYPE_RITUAL('A', RITUAL_ATTRIBUTE),
        /**
         * 调整怪兽
         */
        TYPE_TUNER('B', TUNER_ATTRIBUTE),
        /**
         * 融合怪兽
         */
        TYPE_FUSION('C', FUSION_ATTRIBUTE),
        /**
         * 同调怪兽
         */
        TYPE_SYNCHRO('D', SYNCHRO_ATTRIBUTE),
        /**
         * 超量怪兽
         */
        TYPE_XYZ('E', XYZ_ATTRIBUTE),
        /**
         * 灵摆怪兽
         */
        TYPE_PENDULUM('F', PENDULUM_ATTRIBUTE),
        /**
         * 连接怪兽
         */
        TYPE_LINK('G', LINK_ATTRIBUTE),
        /**
         * 魔法
         */
        TYPE_MAGIC('H', MAGIC_ATTRIBUTE),
        /**
         * 装备魔法
         */
        TYPE_EQUIP('I', EQUIP_ATTRIBUTE),
        /**
         * 永续魔法/陷阱
         */
        TYPE_CONTINUOUS('J', CONTINUOUS_ATTRIBUTE),
        /**
         * 速攻魔法
         */
        TYPE_QUICK_PLAY('K', QUICK_PLAY_ATTRIBUTE),
        /**
         * 场地魔法
         */
        TYPE_FIELD('L', FIELD_ATTRIBUTE),
        /**
         * 陷阱
         */
        TYPE_TRAP('M', TRAP_ATTRIBUTE),
        /**
         * 反击陷阱
         */
        TYPE_COUNTER('N', COUNTER_ATTRIBUTE);
        private final char code;
        private final String name;
    }

    @AllArgsConstructor
    @Getter
    public enum CardAttribute {
        /**
         * 神属性
         */
        TYPE_GOD(1, DIVINE_ATTRIBUTE),
        /**
         * 暗属性
         */
        TYPE_DARK(2, DARK_ATTRIBUTE),
        /**
         * 地属性
         */
        TYPE_EARTH(3, EARTH_ATTRIBUTE),
        /**
         * 光属性
         */
        TYPE_LIGHT(4, LIGHT_ATTRIBUTE),
        /**
         * 火属性
         */
        TYPE_FLAME(5, FIRE_ATTRIBUTE),
        /**
         * 水属性
         */
        TYPE_AQUA(6, WATER_ATTRIBUTE),
        /**
         * 风属性
         */
        TYPE_WIND(7, WIND_ATTRIBUTE);
        private final int code;
        private final String name;
    }

    @AllArgsConstructor
    @Getter
    public enum CardRace {
        /**
         * 水族
         */
        TYPE_AQUA(1, AQUA_ATTRIBUTE),
        /**
         * 兽族
         */
        TYPE_BEAST(2, BEAST_ATTRIBUTE),
        /**
         * 兽战士族
         */
        TYPE_BEAST_WARRIOR(3, BEAST_WARRIOR_ATTRIBUTE),
        /**
         * 创造神族
         */
        TYPE_CREATOR_GOD(4, CREATOR_GOD_ATTRIBUTE),
        /**
         * 电子界族
         */
        TYPE_CYBERSE(5, CYBERSE_ATTRIBUTE),
        /**
         * 恐龙族
         */
        TYPE_DINOSAUR(6, DINOSAUR_ATTRIBUTE),
        /**
         * 幻神兽族
         */
        TYPE_DIVINE_BEAST(7, DIVINE_BEAST_ATTRIBUTE),
        /**
         * 龙族
         */
        TYPE_DRAGON(8, DRAGON_ATTRIBUTE),
        /**
         * 天使族
         */
        TYPE_FAIRY(9, FAIRY_ATTRIBUTE),
        /**
         * 鱼族
         */
        TYPE_FISH(10, FISH_ATTRIBUTE),
        /**
         * 昆虫族
         */
        TYPE_INSECT(11, INSECT_ATTRIBUTE),
        /**
         * 机械族
         */
        TYPE_MACHINE(12, MACHINE_ATTRIBUTE),
        /**
         * 念动力族
         */
        TYPE_PSYCHIC(13, PSYCHIC_ATTRIBUTE),
        /**
         * 炎族
         */
        TYPE_PYRO(14, PYRO_ATTRIBUTE),
        /**
         * 爬虫族
         */
        TYPE_REPTILE(15, REPTILE_ATTRIBUTE),
        /**
         * 岩石族
         */
        TYPE_ROCK(16, ROCK_ATTRIBUTE),
        /**
         * 海龙族
         */
        TYPE_SEA_SERPENT(17, SEA_SERPENT_ATTRIBUTE),
        /**
         * 魔法师族
         */
        TYPE_SPELL_CASTER(18, SPELL_CASTER_ATTRIBUTE),
        /**
         * 雷族
         */
        TYPE_THUNDER(19, THUNDER_ATTRIBUTE),
        /**
         * 战士族
         */
        TYPE_WARRIOR(20, WARRIOR_ATTRIBUTE),
        /**
         * 鸟兽族
         */
        TYPE_WINGED_BEAST(21, WINGED_BEAST_ATTRIBUTE),
        /**
         * 幻龙族
         */
        TYPE_WYRM(22, WYRM_ATTRIBUTE),
        /**
         * 不死族
         */
        TYPE_ZOMBIE(23, ZOMBIE_ATTRIBUTE);
        private final int code;
        private final String name;
    }

    public static final CardType[] CARD_TYPE_LIST = CardType.values();
    public static final HashMap<String, Character> CARD_TYPE_MAP = getCardTypeMap();
    public static final CardAttribute[] CARD_ATTRIBUTE_LIST = CardAttribute.values();
    public static final HashMap<String, Integer> CARD_ATTRIBUTE_MAP = getCardAttributeMap();
    public static final CardRace[] CARD_RACE_LIST = CardRace.values();
    public static final HashMap<String, Integer> CARD_RACE_MAP = getCardRaceMap();

    public static HashMap<String, Integer> getCardRaceMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (CardRace cardRace : CARD_RACE_LIST) {
            hashMap.put(cardRace.name, cardRace.code);
        }
        return hashMap;
    }

    public static Integer getCardRaceByName(String name) {
        if (CARD_RACE_MAP.containsKey(name)) {
            return CARD_RACE_MAP.get(name);
        }
        return null;
    }

    public static HashMap<String, Character> getCardTypeMap() {
        HashMap<String, Character> hashMap = new HashMap<>(10);
        for (CardType cardType : CARD_TYPE_LIST) {
            hashMap.put(cardType.name, cardType.code);
        }
        return hashMap;
    }

    public static HashMap<String, Integer> getCardAttributeMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (CardAttribute cardAttribute : CARD_ATTRIBUTE_LIST) {
            hashMap.put(cardAttribute.name, cardAttribute.code);
        }
        return hashMap;
    }

    public static Character getCardTypeByName(String name) {
        if (CARD_TYPE_MAP.containsKey(name)) {
            return CARD_TYPE_MAP.get(name);
        }
        return null;
    }

    public static Integer getCardAttributeByName(String name) {
        if (CARD_ATTRIBUTE_MAP.containsKey(name)) {
            return CARD_ATTRIBUTE_MAP.get(name);
        }
        return null;
    }

}
