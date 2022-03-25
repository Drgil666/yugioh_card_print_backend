package project.yugioh.card_print.mapper;

import org.apache.ibatis.annotations.*;
import project.yugioh.card_print.pojo.Card;

/**
 * @author Gilbert
 * @date 2022/3/19 15:01
 */
@Mapper
public interface CardMapper {
    /**
     * 创建卡片
     *
     * @param card 被创建的卡片
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into card(code, nwbbs_name, cnocg_name, jp_code, jp_name, " +
            "en_name, type, description, pendulum_description, pendulum_left_scale, " +
            "pendulum_right_scale, atk, def, level, race, attribute, img, " +
            "ocg_limit, tcg_limit,brief_description,link_mark) VALUES (#{card.code},#{card.nwbbsName},#{card.cnocgName}," +
            "#{card.jpCode},#{card.jpName},#{card.enName},#{card.type},#{card.description}," +
            "#{card.pendulumDescription},#{card.pendulumLeftScale},#{card.pendulumRightScale}," +
            "#{card.atk},#{card.def},#{card.level},#{card.race},#{card.attribute},#{card.img}," +
            "#{card.ocgLimit},#{card.tcgLimit},#{card.briefDescription},#{card.linkMark})")
    Boolean createCard(@Param("card") Card card);

    /**
     * 更新卡片图源
     *
     * @param cardId  卡片id
     * @param mongoId 卡图mongoId
     * @return 影响行数
     */
    @Update("update card set img=#{mongoId} where id=#{cardId}")
    Long updateCard(@Param("cardId") Integer cardId, @Param("mongoId") String mongoId);

    /**
     * 根据卡片id获取卡片
     *
     * @param cardId 卡片id
     * @return 卡片信息
     */
    @Select("select * from card where id=#{cardId}")
    Card getCard(@Param("cardId") Integer cardId);

    /**
     * 根据卡片密码获取卡片
     *
     * @param cardCode 卡片密码
     * @return 卡片信息
     */
    @Select("select * from card where code=#{code}")
    Card getCardByCode(@Param("code") Integer cardCode);
}
