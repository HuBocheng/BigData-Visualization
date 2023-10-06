package com.game.consumer.bean;

import com.game.common.bean.Consumer;
import com.game.common.constant.Names;
import com.game.consumer.dao.HBaseDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName:RecordConsumer
 * @description：Todo
 * @author:BochengHu
 * @date 2023-07-12  15:21
 */
public class RecordConsumer implements Consumer {
    @Override
    public void consumer() throws IOException {
        try{
            //创建配置对象
            Properties prop=new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
            //获取flume采集的数据
            KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(prop);
            //订阅主题
            consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));
            //创建HBaseDao对象
            HBaseDao hbaseDao = new HBaseDao();
            //初始化
            hbaseDao.init();

            //拉取信息、消费数据
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    //存储到HBase
                    hbaseDao.insertData(record.value());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {

    }
}
