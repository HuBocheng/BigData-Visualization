package com.game.producer.bean;

import com.game.common.bean.DataIn;
import com.game.common.bean.DataOut;
import com.game.common.bean.Producer;
import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName:LocalFileProducer
 * @description：本地数据生产，包括设置输入输出和生产数据本身
 * @author:BochengHu
 * @date 2023-07-11  15:26
 */
public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataOut out;
    private volatile boolean flag=true;//增强进程可见性，进程共享
    @Override
    public void setIn(DataIn in) {
        this.in=in;
    }

    @Override
    public void setOut(DataOut out) {
        this.out=out;
    }

    @Override
    public void produce() throws IOException {


        List<AthleteRecord> recordList = in.read(AthleteRecord.class);
        /*
        for (AthleteRecord record : recordList) {
            System.out.println(record);
        }
        System.out.println(recordList.size());
         */

        for (AthleteRecord record : recordList) {
            try {
                System.out.println(record);
                out.write(record);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() throws IOException {
        if(in!=null){
            in.close();
        }
        if(out!=null){
            out.close();
        }
    }
}
