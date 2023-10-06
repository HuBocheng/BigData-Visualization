package org.doraemon.visualize.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.doraemon.visualize.entity.Athlete;

import java.util.List;

/**
 * @author : Jasmine Xie
 * @version : 1.0.0
 * @description : 实现对运动员数据的获取
 * @class : org.doraemon.visualize.mapper.AthleteMapper
 * @createTime : 2023/07/16
 */
@Mapper
public interface AthleteMapper {
    /**
     * @param country country
     * @param sport   sport
     * @return {@link List }<{@link Athlete }>
     * @description : 找到最优的五个运动员
     * @author : Jasmine Xie
     */
    @Select("select * from athleteshow where country=#{country} and sport=#{sport} order by score desc limit 5")
    List<Athlete> findBySport(String country,String sport);

    @Select("select distinct name from athleteshow where country=#{country}")
    List<String> findByCountry(String country);
}
