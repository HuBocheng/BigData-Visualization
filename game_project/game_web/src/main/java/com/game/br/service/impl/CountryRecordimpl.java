package com.game.br.service.impl;



import com.game.br.dao.CountryRecordDao;
import com.game.br.entity.*;
import com.game.br.service.CountryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:AthleteRecordimpl
 * @description：运动员奖牌榜业务处理实现
 * @author:BochengHu
 * @date 2023-07-15  10:41
 */

@Service //业务层
@Transactional //支持事务
public class CountryRecordimpl implements CountryRecordService {
    @Autowired
    private CountryRecordDao athleteDao;

    @Override
    public List<AthleteRecord> findAll() {
        return null;
    }

    @Override
    public List<CountryMedalOverview> findCountryMedalOverview(String countrySelect) {
        //返回的集合
        List<CountryMedalOverview> listReturn=new ArrayList<>();
        //传入dao层sql语句的逻辑映射
        Map<String,String> map=new HashMap<>();

        //hashMap映射替换占位变量
        //先要做输入判断


        map.put("countrySelect",countrySelect);
        //调用dao层方法执行sql查询
        listReturn=athleteDao.findCountryMedalOverview(map);
        return listReturn;
    }

    @Override
    public List<WorldwideMedalOverview> findWorldwideMedalOverview() {
        //返回的集合
        List<WorldwideMedalOverview> listReturn=new ArrayList<>();
        //不需要占位符映射
        //调用dao层方法执行sql查询
        listReturn=athleteDao.findWorldwideMedalOverview();
        return listReturn;
    }

    @Override
    public List<FinanceRecord> findAllFinanceRecord() {
        //返回的集合
        List<FinanceRecord> listReturn=new ArrayList<>();
        //不需要占位符映射
        //调用dao层方法执行sql查询
        listReturn=athleteDao.findAllFinanceRecord();
        return listReturn;
    }

    @Override
    public List<AccMedalData> findAccMedalData() {
        //返回的集合
        List<AccMedalData> listReturn=new ArrayList<>();
        //不需要占位符映射
        //调用dao层方法执行sql查询
        listReturn=athleteDao.findAccMedalData();
        return listReturn;
    }
}
