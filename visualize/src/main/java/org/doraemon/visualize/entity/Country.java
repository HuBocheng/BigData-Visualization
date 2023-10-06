package org.doraemon.visualize.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Jasmine Xie
 * @version : 1.0.0
 * @description : 国家实体类
 * @class : org.doraemon.visualize.entity.Country
 * @createTime : 2023/07/16
 */
@Data
@AllArgsConstructor
@TableName("countryshow")
public class Country {
    private Long id;
    private String country;
    private int year;
    private String sport;
    @TableField("goldNum")
    private int goldNum;
    @TableField("silverNum")
    private int silverNum;
    @TableField("bronzeNum")
    private int bronzeNum;
    @TableField("totalNum")
    private int totalNum;
}
