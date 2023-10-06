package com.game.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @ClassName:Producer
 * @description：生产者接口
 * @author:BochengHu
 * @date 2023-07-11  14:54
 */

public interface Producer extends Closeable {
    //数据输入
    void setIn(DataIn in);
    //数据输出
    void setOut(DataOut out);
    //生产数据
    void produce() throws IOException;
}
