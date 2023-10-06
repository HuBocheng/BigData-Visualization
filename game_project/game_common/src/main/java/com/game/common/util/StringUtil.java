package com.game.common.util;

/**
 * @ClassName:StringUtil
 * @description：字符串检查工具类
 * @author:BochengHu
 * @date 2023-07-14  0:28
 */
public class StringUtil {
    public static String removeQuotationMarks(String str){
        return str.replace("\'", "");
    }
}
