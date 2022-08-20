package project.yugioh.card_print.service.Impl;

import org.springframework.stereotype.Service;
import project.yugioh.card_print.mapper.CardMapper;
import project.yugioh.card_print.pojo.Card;
import project.yugioh.card_print.service.CardService;

import javax.annotation.Resource;

/**
 * @author Gilbert
 * @date 2022/3/19 15:17
 */
@Service
public class CardServiceImpl implements CardService {
    @Resource
    private CardMapper cardMapper;

    /**
     * 创建卡片
     *
     * @param card 被创建的卡片
     * @return 是否创建成功
     */
    @Override
    public Boolean createCard(Card card) {
        return cardMapper.createCard(card);
    }

    /**
     * 更新卡片
     *
     * @param card 要更新的卡片
     * @return 影响行数
     */
    @Override
    public Long updateCard(Card card) {
        return cardMapper.updateCard(card);
    }

    /**
     * 更新卡片图源
     *
     * @param cardId  卡片id
     * @param mongoId 卡图mongoId
     * @return 影响行数
     */
    @Override
    public Long updateCardMongoId(Integer cardId, String mongoId) {
        return cardMapper.updateCardMongoId(cardId, mongoId);
    }

    /**
     * 根据卡片id获取卡片
     *
     * @param cardId 卡片id
     * @return 卡片信息
     */
    @Override
    public Card getCard(Integer cardId) {
        return cardMapper.getCard(cardId);
    }

    /**
     * 根据卡片密码获取卡片
     *
     * @param cardCode 卡片密码
     * @return 卡片信息
     */
    @Override
    public Card getCardByCode(Integer cardCode) {
        return cardMapper.getCardByCode(cardCode);
    }

    /**
     * 根据卡片名获取卡片
     *
     * @param name 卡片名
     * @return 卡片信息
     */
    @Override
    public Card getCardByNwbbsName(String name) {
        return cardMapper.getCardByNwbbsName(name);
    }
}
