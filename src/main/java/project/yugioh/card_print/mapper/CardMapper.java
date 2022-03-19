package project.yugioh.card_print.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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
            "ocg_limit, tcg_limit,brief_description) VALUES (#{card.code},#{card.nwbbsName},#{card.cnocgName}," +
            "#{card.jpCode},#{card.jpName},#{card.enName},#{card.type},#{card.description}," +
            "#{card.pendulumDescription},#{card.pendulumLeftScale},#{card.pendulumRightScale}," +
            "#{card.atk},#{card.def},#{card.level},#{card.race},#{card.attribute},#{card.img}," +
            "#{card.ocgLimit},#{card.tcgLimit},#{card.briefDescription})")
    Boolean createCard(@Param("card") Card card);
}
