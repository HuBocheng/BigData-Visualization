package com.game.consumer.dao;

import com.game.common.bean.BaseHBaseDao;
import com.game.common.constant.Names;
import com.game.common.constant.ValueConstant;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName:BaseHBaseDao
 * @description：HBase业务层
 * @author:BochengHu
 * @date 2023-07-12  16:11
 */
public class HBaseDao extends BaseHBaseDao {
    public void init() throws IOException {
        start();//开始
        //创建命名空间
        createNamespaceNX(Names.NAMESPACE.getValue());
        //创建表

        createTableXX(
                Names.TABLE.getValue(),
                ValueConstant.REGION_COUNT,
                Names.GAME_INFO.getValue()
        );


        end();//结束
    }
    public void insertData(String value) throws IOException {
        //将运动员保存到HBase表中
        //1.获取运动员数据
        String[] values=value.split("\t");
        String id=values[0];
        String name=values[1];
        String sex=values[2];
        String age=values[3];
        String height=values[4];
        String weight=values[5];
        String NOC=values[6];
        String year=values[7];
        String season=values[8];
        String sport=values[9];
        String event=values[10];
        String medal=values[11];

        //rowKey
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        String rowKey=randomNumber+"_"+id+"_"+name+"_"+sex+"_"+age+"_"+height+"_"
                +weight+"_"+NOC+"_"+year+"_"+season+"_"+sport+"_"+event+"_"+medal;
        Put athlete_put=new Put(Bytes.toBytes(rowKey));
        //列族
        byte[] athlete_family=Bytes.toBytes(Names.GAME_INFO.getValue());

        //列族增加列
        athlete_put.addColumn(athlete_family,Bytes.toBytes("id"),Bytes.toBytes(id));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("name"),Bytes.toBytes(name));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("sex"),Bytes.toBytes(sex));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("age"),Bytes.toBytes(age));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("height"),Bytes.toBytes(height));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("weight"),Bytes.toBytes(weight));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("NOC"),Bytes.toBytes(NOC));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("year"),Bytes.toBytes(year));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("season"),Bytes.toBytes(season));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("sport"),Bytes.toBytes(sport));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("event"),Bytes.toBytes(event));
        athlete_put.addColumn(athlete_family,Bytes.toBytes("medal"),Bytes.toBytes(medal));

        //保存数据到HBase
        List<Put> puts = new ArrayList<>();
        puts.add(athlete_put);
        putData(Names.TABLE.getValue(), puts);
    }

}
