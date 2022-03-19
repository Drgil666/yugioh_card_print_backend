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
}
