package com.game.common.constant;

import com.game.common.bean.Val;

/**
 * 命名枚举类
 */
public enum Names implements Val {
    TOPIC("game"),//主题
    NAMESPACE("game"),//命名空间
    CF_INFO("info"),//info列簇
    TABLE("game:athlete"),//表名
    GAME_INFO("athlete")

    ;
    private String name;
    private Names(String name){
        this.name=name;
    }
    @Override
    public void setValue(Object val) {
        this.name=(String)val;
    }

    @Override
    public String getValue() {
        return name;
    }
}
