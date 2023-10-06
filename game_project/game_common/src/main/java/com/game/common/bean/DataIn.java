package com.game.common.bean;


import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName:DataIn
 * @description：数据输入
 * @author:BochengHu
 * @date 2023-07-11  14:50
 */

public interface DataIn extends Closeable {
    //输入路径
    void setPath(String path);
    //读取数据
    Object read() throws IOException;
    <T extends Data> List<T> read(Class<T> clazz)throws IOException;
}
