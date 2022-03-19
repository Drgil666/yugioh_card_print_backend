package project.yugioh.card_print.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import project.yugioh.card_print.pojo.Card;

/**
 * @author Gilbert
 * @date 2022/3/19 15:17
 */
public interface CardService {
    /**
     * 创建卡片
     *
     * @param card 被创建的卡片
     * @return 是否创建成功
     */
    Boolean createCard(Card card);
}
