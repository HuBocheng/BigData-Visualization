package org.doraemon.visualize.controller;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.doraemon.visualize.entity.GDP;
import org.doraemon.visualize.mapper.CountryMapper;
import org.doraemon.visualize.mapper.GDPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author : Jasmine Xie
 * @version : 1.0.0
 * @description : 获取金融部分相关数据
 * @class : org.doraemon.visualize.controller.FinanceController
 * @createTime : 2023/07/17
 */
@RestController
public class FinanceController {
    @Data
    @AllArgsConstructor
    private class Info{
        private String year;
        private String totalGDP;
        private String totalNum;
    }
    @Schema(name = "地图国家名映射", description = "作为静态对象，对地图上的国家名和数据库的国家名进行映射")
    private static Map<String, String> mapFromAbbr;
    private static Map<String, String> mapToAbbr;

    static {
        mapFromAbbr = new HashMap<>();
        mapToAbbr = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/static/names/nameMap"));
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] countryMap = line.split(" - ");
                mapFromAbbr.put(countryMap[0],countryMap[1]);
                mapToAbbr.put(countryMap[1],countryMap[0]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private GDPMapper gdpMapper;
    @Autowired
    private CountryMapper countryMapper;

    @RequestMapping("/finance/{country}")
    public void financy(HttpServletResponse response, @PathVariable String country) throws IOException {
        List<GDP> gdpList = gdpMapper.findByCountry(mapToAbbr.get(country));
        List<Info> infoList = new ArrayList<>();
        for(GDP gdp:gdpList)
        {
            List<String> totalNum = countryMapper.findByYear(mapToAbbr.get(country),String.valueOf(gdp.getYear()));
            if(totalNum.size()!=0)
            {
                int totalCount = 0;
                for(String value:totalNum) totalCount+=Integer.parseInt(value);
                infoList.add(new Info(String.valueOf(gdp.getYear()),String.valueOf(gdp.getTotalGDP()),String.valueOf(totalCount)));
            }
        }
        Gson gson = new Gson();
        String content = gson.toJson(infoList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(content);
        writer.flush();
        writer.close();
    }
    @RequestMapping("/initial/TopFinance/")
    public void topFinance(HttpServletResponse response) throws IOException {
        List<GDP> gdp = gdpMapper.findAllFinance();
        List<GDP> gdpList = new ArrayList<>();
        for (GDP gdp1 : gdp) {
            if(mapFromAbbr.containsKey(gdp1.getCountry()))
                gdpList.add(new GDP(mapFromAbbr.get(gdp1.getCountry()),gdp1.getYear(),gdp1.getTotalGDP(),gdp1.getAverageGDP()));
        }
        Gson gson = new Gson();
        String AllFinanceInfo = gson.toJson(gdpList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(AllFinanceInfo);
        writer.flush();
        writer.close();
    }
}
