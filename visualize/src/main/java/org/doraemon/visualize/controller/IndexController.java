package org.doraemon.visualize.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Jasmine Xie
 * @version : 1.0.0
 * @description : 页面跳转
 * @class : org.doraemon.visualize.controller.IndexController
 * @createTime : 2023/07/15
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }


    @RequestMapping("/finance")
    public String getCountry()
    {
        return "finance";
    }
}
