package org.doraemon.visualize.controller;


import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.doraemon.visualize.entity.Athlete;
import org.doraemon.visualize.mapper.AthleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @class : org.doraemon.visualize.controller.AthleteDataController
 * @createTime : 2023/07/16
 */
@Slf4j
@RestController
public class AthleteDataController {
    @Autowired
    private AthleteMapper athleteMapper;
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
    /**
     * @param response response
     * @param country  country
     * @throws IOException ioexception
     * @description : //TODO
     * @author : Jasmine Xie
     */
    @GetMapping("/athlete/{country}/{sport}")
    public void findAthleteByCountrySport(HttpServletResponse response,@PathVariable String country,@PathVariable String sport) throws IOException {
        log.info("find the athlete");
        List<Athlete> athleteList = athleteMapper.findBySport(mapToAbbr.get(country),sport);
        Gson gson = new Gson();
        String allCountryInfo = gson.toJson(athleteList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(allCountryInfo);
        writer.flush();
        writer.close();
    }
}
