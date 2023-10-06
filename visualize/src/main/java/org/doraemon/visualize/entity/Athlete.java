package org.doraemon.visualize.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Jasmine Xie
 * @version : 1.0.0
 * @description : 运动员实体类
 * @class : org.doraemon.visualize.entity.Athlete
 * @createTime : 2023/07/16
 */
@Data
@AllArgsConstructor
@TableName("athleteshow")
public class Athlete {
    private Long id;
    private String name;
    private String country;
    private String sport;
    @TableField("goldNum")
    private int goldNum;
    @TableField("silverNum")
    private int silverNum;
    @TableField("bronzeNum")
    private int bronzeNum;
    private int score;
}
