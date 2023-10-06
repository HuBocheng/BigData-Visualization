package com.game.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @ClassName:Consumer
 * @description：消费者接口
 * @author:BochengHu
 * @date 2023-07-12  15:18
 */

public interface Consumer extends Closeable {
    //消费数据
    void consumer() throws IOException;
}
