package com.game.br.dao;

import com.game.br.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:CountryRecordDao
 * @description：国家奖牌榜数据访问对象
 * @author:BochengHu
 * @date 2023-07-15  10:13
 */

public interface CountryRecordDao {
    List<AthleteRecord> findAll(Map<String,String> map);
    List<CountryMedalOverview> findCountryMedalOverview(Map<String,String> map);
    List<WorldwideTop500Athletes> findTop500(Map<String,String> map);

    List<WorldwideMedalOverview> findWorldwideMedalOverview();
    List<FinanceRecord> findAllFinanceRecord();
    List<AccMedalData> findAccMedalData();
}
