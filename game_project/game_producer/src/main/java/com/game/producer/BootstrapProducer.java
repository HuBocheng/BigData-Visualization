package com.game.producer;

import com.game.common.bean.Producer;
import com.game.producer.bean.LocalFileProducer;
import com.game.producer.io.LocalFileDataIn;
import com.game.producer.io.LocalFileDataOut;

import java.io.IOException;

/**
 * @ClassName:BootstrapProducer
 * @description：启动对象
 * @author:BochengHu
 * @date 2023-07-11  16:03
 */
public class BootstrapProducer {
    public static void main(String[] args) throws IOException {
        if(args.length<2){
            System.out.println("输入格式不正确");
            System.exit(1);
        }
        //构建生产者对象
        Producer producer=new LocalFileProducer();
        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));
        //生产数据
        producer.produce();
        //关闭生产者对象
        producer.close();
    }
}
