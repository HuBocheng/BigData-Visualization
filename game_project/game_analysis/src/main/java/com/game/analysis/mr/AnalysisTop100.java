package com.game.analysis.mr;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @ClassName:AnalysisTop100
 * @description：统计给出top100运动员
 * @author:BochengHu
 * @date 2023-07-14  10:43
 */
public class AnalysisTop100 {
    public static class AnalysisTop100Mapper extends TableMapper<Text,Text>{
        @Override
        protected void map(ImmutableBytesWritable key, Result value, Mapper<ImmutableBytesWritable, Result, Text, Text>.Context context) throws IOException, InterruptedException {
            String rowKey= Bytes.toString(key.get());
            String[] values = rowKey.split("_");
            String name=values[2];
            String medal=values[12];
            String country=values[7];


            //写出：XX运动员，获得金/银/铜牌
            if(!medal.equals("NA")){
                context.write(new Text(name+"_"+country),new Text(medal));
            }
        }
    }

    public static class AnalysisTop100Reducer extends Reducer<Text,Text,Text, Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
            int countGold=0;
            int countSilver=0;
            int countBronze=0;
            for (Text value : values) {
                if(value.toString().equals("Gold")){
                    countGold++;
                }
                if(value.toString().equals("Silver")){
                    countSilver++;
                }
                if(value.toString().equals("Bronze")){
                    countBronze++;
                }
            }
            int score=countGold*5+countSilver*2+countBronze*1;
            if(countGold>0||countSilver>0||countBronze>0){
                context.write(key,new Text(countGold + "_" + countSilver + "_" + countBronze+"_"+score));
            }
        }
    }
}
