package com.game.common.bean;

import java.io.Closeable;

/**
 * @ClassName:DataOut
 * @description：Todo
 * @author:BochengHu
 * @date 2023-07-11  14:51
 */

public interface DataOut extends Closeable {
    //输出路径
    void setPath(String path);
    void write(Object obj)throws Exception;
    void write(String obj)throws Exception;
}
