package com.game.common.bean;

/**
 * @ClassName:Data
 * @description：数据对象
 * @author:BochengHu
 * @date 2023-07-11  14:53
 */
public abstract class Data implements Val{
    public String content;
    @Override
    public void setValue(Object val) {
        content= (String) val;
    }

    @Override
    public String getValue() {
        return content;
    }
}
