package org.doraemon.visualize.controller;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.doraemon.visualize.entity.Athlete;
import org.doraemon.visualize.entity.Country;
import org.doraemon.visualize.entity.GDP;
import org.doraemon.visualize.mapper.AthleteMapper;
import org.doraemon.visualize.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @description : 获得数据
 * @class : org.doraemon.visualize.controller.DataController
 * @createTime : 2023/07/16
 */
@Slf4j
@RestController
public class CountryDataController {
    @Schema(name = "地图国家名映射",description = "作为静态对象，对地图上的国家名和数据库的国家名进行映射")
    private static Map<String,String> mapFromAbbr;
    private static Map<String,String> mapToAbbr;
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
    private CountryMapper countryMapper;
    @GetMapping("/Info/{country}/{sport}")
    public void countrySport(HttpServletResponse response,@PathVariable String country,@PathVariable String sport) throws IOException {
        country = mapToAbbr.get(country);
        List<Country> countryList = countryMapper.findCountrySport(country,sport);
        Gson gson = new Gson();
        String allYearSport = gson.toJson(countryList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(allYearSport);
        writer.flush();
        writer.close();
    }

    @GetMapping("/initial/country")
    public void initCountry(HttpServletResponse response) throws IOException {
        log.info("init all countries");
        List<String> countryList = countryMapper.findAllCountry();
        List<String> mapCountries = new ArrayList<>();
        countryList.forEach(item->{
            if(mapFromAbbr.containsKey(item)) mapCountries.add(mapFromAbbr.get(item));
        });
        Gson gson = new Gson();
        String allCountryInfo = gson.toJson(mapCountries);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(allCountryInfo);
        writer.flush();
        writer.close();
    }

    @GetMapping("/initial/country/{country}")
    public void getCountry(HttpServletResponse response,@PathVariable String country) throws IOException {
        List<String> sportList = countryMapper.findAllSports(mapToAbbr.get(country));
        Gson gson = new Gson();
        String allSportsInfo = gson.toJson(sportList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(allSportsInfo);
        writer.flush();
        writer.close();
    }

}
