package com.game.br.controller;

import com.game.br.entity.*;
import com.game.br.service.CountryRecordService;
import com.game.br.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:CountryController
 * @description：国家奖牌表控制对象
 * @author:BochengHu
 * @date 2023-07-16  12:20
 */

@RestController
@CrossOrigin
@RequestMapping("country")
public class CountryController {
    @Autowired

    private CountryRecordService countryRecordService;

    //查询某国家在各年份获得奖牌情况
    @RequestMapping(value = "findCountryMedalOverview",method = RequestMethod.POST)
    public ServerResponse<Map<String,Object>> findCountryMedalOverview(@RequestParam("countrySelect")String countrySelect){
        List<CountryMedalOverview> countryMedalOverviewList = countryRecordService.findCountryMedalOverview(countrySelect);
        //写返回的map集合
        Map<String,Object> map=new HashMap<>();
        map.put("countryMedalOverviewList",countryMedalOverviewList);
        return ServerResponse.success(map);
    }

    //查询所有国家在1984年之后获得奖牌情况
    @RequestMapping(value = "findWorldwideMedalOverview",method = RequestMethod.POST)
    public ServerResponse<Map<String,Object>> findWorldwideMedalOverview(){
        List<WorldwideMedalOverview> WorldwideMedalOverviewList=countryRecordService.findWorldwideMedalOverview();
        //写返回的map集合
        Map<String,Object> map=new HashMap<>();
        map.put("WorldwideMedalOverviewList",WorldwideMedalOverviewList);
        return ServerResponse.success(map);
    }

    //查询所有国家在1984年之后累计获得奖牌情况
    @RequestMapping(value = "findAccMedalData",method = RequestMethod.POST)
    public ServerResponse<Map<String,Object>> findAccMedalData(){
        List<AccMedalData> AccMedalDataList=countryRecordService.findAccMedalData();
        for (AccMedalData accMedalData : AccMedalDataList) {
            System.out.println(accMedalData);
        }
        //写返回的map集合
        Map<String,Object> map=new HashMap<>();
        map.put("AccMedalData",AccMedalDataList);
        return ServerResponse.success(map);
    }

    //查询所有financeshow表数据
    @RequestMapping(value = "findAllFinanceRecord",method = RequestMethod.POST)
    public ServerResponse<Map<String,Object>> findAllFinanceRecord(){
        List<FinanceRecord> AllFinanceRecordList=countryRecordService.findAllFinanceRecord();
        //写返回的map集合
        Map<String,Object> map=new HashMap<>();
        map.put("findAllFinanceRecordList",AllFinanceRecordList);
        return ServerResponse.success(map);
    }

}
