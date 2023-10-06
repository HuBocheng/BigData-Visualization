package com.game.consumer;

import com.game.common.bean.Consumer;
import com.game.consumer.bean.RecordConsumer;

import java.io.IOException;

/**
 * @ClassName:BootStrapConsumer
 * @description：启动消费者
 * @author:BochengHu
 * @date 2023-07-12  15:31
 */
public class BootStrapConsumer {
    public static void main(String[] args) throws IOException {
        //创建消费者
        Consumer consumer = new RecordConsumer();
        //消费数据
        consumer.consumer();
        //关闭资源
        consumer.close();
    }
}
