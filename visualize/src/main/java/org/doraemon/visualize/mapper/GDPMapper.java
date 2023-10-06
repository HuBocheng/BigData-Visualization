package org.doraemon.visualize.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.doraemon.visualize.entity.GDP;

import java.util.List;

@Mapper
public interface GDPMapper {
    @Select("select * from financeshow where country=#{country}")
    public List<GDP> findByCountry(String country);

    @Select("select * from financeshow")
    public List<GDP> findAllFinance();
}
