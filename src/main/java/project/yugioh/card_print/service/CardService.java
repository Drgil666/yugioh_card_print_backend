package project.yugioh.card_print.service;

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

    /**
     * 更新卡片
     * @param card 要更新的卡片
     * @return 影响行数
     */
    Long updateCard(Card card);
    /**
     * 更新卡片图源
     *
     * @param cardId  卡片id
     * @param mongoId 卡图mongoId
     * @return 影响行数
     */
    Long updateCardMongoId(Integer cardId, String mongoId);

    /**
     * 根据卡片id获取卡片
     *
     * @param cardId 卡片id
     * @return 卡片信息
     */
    Card getCard(Integer cardId);

    /**
     * 根据卡片密码获取卡片
     *
     * @param cardCode 卡片密码
     * @return 卡片信息
     */
    Card getCardByCode(Integer cardCode);

    /**
     * 根据卡片名获取卡片
     * @param name 卡片名
     * @return 卡片信息
     */
    Card getCardByNwbbsName(String name);
}
