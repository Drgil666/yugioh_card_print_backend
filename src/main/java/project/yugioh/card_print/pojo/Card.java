package project.yugioh.card_print.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Gilbert
 * @date 2022/3/19 14:46
 */
@Data
@EqualsAndHashCode
public class Card {
    /**
     * 卡片id
     */
    private Integer id;
    /**
     * 卡片代码
     */
    private Integer code;
    /**
     * NWBBS译名
     */
    private String nwbbsName;
    /**
     * CNOCG译名
     */
    private String cnocgName;
    /**
     * 英文译名
     */
    private String enName;
    /**
     * 日文平假名
     */
    private String jpRuby;
    /**
     * 日文译名
     */
    private String jpName;
    /**
     * 卡片类型
     */
    private String type;
    /**
     * 卡片效果描述
     */
    private String description;
    /**
     * 卡片灵摆效果描述
     */
    private String pendulumDescription;
    /**
     * 灵摆左刻度
     */
    private Integer pendulumLeftScale;
    /**
     * 灵摆右刻度
     */
    private Integer pendulumRightScale;
    /**
     * 攻击力
     */
    private Integer atk;
    /**
     * 守备力
     */
    private Integer def;
    /**
     * 等级/阶级/Link数
     */
    private Integer level;
    /**
     * 卡片种族
     */
    private Integer race;
    /**
     * 卡片属性
     */
    private Integer attribute;
    /**
     * 卡片的mongoId
     */
    private String img;
    /**
     * OCG限制数
     */
    private Integer ocgLimit;
    /**
     * TCG限制数
     */
    private Integer tcgLimit;
    /**
     * 卡片简要介绍
     */
    private String briefDescription;
    /**
     * 连接箭头
     */
    private String linkMark;

}
