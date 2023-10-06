package com.game.br.service;

import com.game.br.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:AthleteRecordService
 * @description：运动员奖牌榜业务处理
 * @author:BochengHu
 * @date 2023-07-15  10:15
 */

public interface CountryRecordService {
    List<AthleteRecord> findAll();
    List<CountryMedalOverview> findCountryMedalOverview(String countrySelect);

    List<WorldwideMedalOverview> findWorldwideMedalOverview();

    List<FinanceRecord> findAllFinanceRecord();

    List<AccMedalData> findAccMedalData();

}
