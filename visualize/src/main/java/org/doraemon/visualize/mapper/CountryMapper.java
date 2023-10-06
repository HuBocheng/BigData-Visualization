package org.doraemon.visualize.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.doraemon.visualize.entity.Athlete;
import org.doraemon.visualize.entity.Country;

import java.util.List;

/**
 * @author : Jasmine Xie
 * @version : 1.0.0
 * @description : 实现对国家数据的获取
 * @class : org.doraemon.visualize.mapper.CountryMapper
 * @createTime : 2023/07/16
 */
@Mapper
public interface CountryMapper {
    @Select("select distinct country from countryshow")
    List<String> findAllCountry();

    @Select("select distinct sport from countryshow where country=#{country}")
    List<String> findAllSports(String country);

    @Select("select totalNum from countryshow where country=#{country} and year=#{year}")
    List<String> findByYear(String country,String year);

    @Select("select * from countryshow where country=#{country}")
    List<Country> findCountryInfo(String country);

    @Select("select * from countryshow where country=#{country} and sport=#{sport}")
    List<Country> findCountrySport(String country, String sport);
}
