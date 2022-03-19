package project.yugioh.card_print.pojo.vo;

import lombok.Data;

/**
 * @author Gilbert
 * @date 2022/3/19 15:42
 */
@Data
public class CardDb {
    private Integer cid = null;
    private Integer id = null;
    private String cn_name = "";
    private String cnocg_n = "";
    private String jp_ruby = "";
    private String jp_name = "";
    private String en_name = "";
    private CardText text;
    private CardData data;

    @Data
    public static class CardText {
        private String types = "";
        private String pdesc = "";
        private String desc = "";
    }

    @Data
    public static class CardData {
        private Integer ot = null;
        private Long setcode = null;
        private Integer atk = null;
        private Integer def = null;
        private Integer level = null;
        private Integer race = null;
        private Integer attribute = null;
    }
}
