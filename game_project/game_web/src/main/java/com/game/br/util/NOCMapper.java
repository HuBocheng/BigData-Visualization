package com.game.br.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:NOCMapper
 * @description：Todo
 * @author:BochengHu
 * @date 2023-07-15  10:56
 */
public class NOCMapper {
    private static final Map<String, String> stringMap = new HashMap<>();

    public static String[] NOCs;
    public static String[] countries;
    public static String[] sports;

    public NOCMapper() {

    }

    static {

        NOCs=new String[]{
                "AUS",
                "BAH",
                "BEL",
                "BLR",
                "BRA",
                "BUL",
                "CAN",
                "CHN",
                "CUB",
                "CZE",
                "ESP",
                "ETH",
                "EUN",
                "FIN",
                "FRA",
                "FRG",
                "GBR",
                "GDR",
                "GER",
                "GRE",
                "HUN",
                "ITA",
                "JAM",
                "JPN",
                "KEN",
                "MAR",
                "MEX",
                "NED",
                "NGR",
                "NOR",
                "NZL",
                "POL",
                "ROU",
                "RSA",
                "RUS",
                "SWE",
                "TCH",
                "TTO",
                "UKR",
                "URS",
                "USA"
        };

        countries=new String[]{
                "澳大利亚（Australia）",
                "巴哈马（The Bahamas）",
                "比利时（Belgium）",
                "白俄罗斯（Belarus）",
                "巴西（Brazil）",
                "保加利亚（Bulgaria）",
                "加拿大（Canada）",
                "中国（China）",
                "古巴（Cuba）",
                "捷克（Czech Republic）",
                "西班牙（Spain）",
                "埃塞俄比亚（Ethiopia）",
                "独联体（Unified Team）",
                "芬兰（Finland）",
                "法国（France）",
                "西德",
                "英国（UK）",
                "东德",
                "德国（Germany）",
                "希腊（Greece）",
                "匈牙利（Hungary）",
                "意大利（Italy）",
                "牙买加（Jamaica）",
                "日本（Japan）",
                "肯尼亚（Kenya）",
                "摩洛哥（Morocco）",
                "墨西哥（Mexico）",
                "荷兰（Netherlands）",
                "尼日利亚（Nigeria）",
                "挪威（Norway）",
                "新西兰（New Zealand）",
                "波兰（Poland）",
                "罗马尼亚（Romania）",
                "南非（South Africa）",
                "俄罗斯（Russia）",
                "瑞典（Sweden）",
                "捷克斯洛伐克",
                "特立尼达和多巴哥",
                "乌克兰（Ukraine）",
                "苏联",
                "美国（USA）",
        };

        /*
        sports=new String[]{

        };

         */

        // NOC映射规则
        for (int i = 0; i < 41; i++) {
            if (NOCs != null) {
                stringMap.put(NOCs[i], countries[i]);
            }
        }
    }

    public static String mapStrings(String input) {
        // 根据输入字符串在映射表中查找对应的映射值
        String mappedString = stringMap.getOrDefault(input, input);
        //mappedString=mappedString.replace("-1","0");
        return mappedString;
    }

    public static String mapStringPlus(String input){
        for (String noc : NOCs) {
            input=input.replace(noc,stringMap.getOrDefault(noc, input));
        }
        return input;
    }

    public static boolean findCountry(String input){
        boolean found=false;
        for (String noc : NOCs) {
            if(noc.equals(input)){
                found=true;
                break;
            }
        }
        return found;
    }

    /*
    public static boolean findSport(String input){
        boolean found=false;
        for (String sport : sports) {
            if(sport.equals(input)){
                found=true;
                break;
            }
        }
        return found;
    }

     */
}
