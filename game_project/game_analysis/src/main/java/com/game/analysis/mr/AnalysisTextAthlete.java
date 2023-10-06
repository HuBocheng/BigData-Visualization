package com.game.analysis.mr;

import com.game.common.util.NOCMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.TwoDArrayWritable;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;

import static com.game.common.util.NOCMapper.findCountry;

/**
 * @ClassName:AnalysisTextAthlete
 * @description：统计各国在田径领域获得的奖牌数，评估中国在田径领域实力
 * @author:BochengHu
 * @date 2023-07-13  22:30
 */
public class AnalysisTextAthlete {
    public static class AnalysisTextMapper extends TableMapper<Text,Text>{
        HashMap<String ,String >map;

        @Override
        protected void map(ImmutableBytesWritable key, Result value,
                           Mapper<ImmutableBytesWritable, Result, Text, Text>.Context context)
                throws IOException, InterruptedException {
            String rowKey= Bytes.toString(key.get());
            String[] values = rowKey.split("_");
            String name=values[2];
            String sex=values[3];
            String age=values[4];
            String height=values[5];
            String weight=values[6];
            String country=values[7];
            String year=values[8];
            String sport=values[10];
            String medal=values[12];

            if(!medal.equals("NA")&&NOCMapper.findCountry(country)){
                //写出：XX国家YY年份ZZ项目，获得金/银/铜牌
                context.write(new Text("forCountryTable"+"_"+country+"_"+year+"_"+sport),new Text(medal));
                //写出：XX人YY国家ZZ项目，获得金/银/铜牌
                context.write(new Text("forAthleteTable"+"_"+name+"_"+country+"_"+sport),new Text(medal));
                //写出：XX人YY国，性别_年龄_身高_体重
                context.write(new Text("forBasicInfoTable"+"_"+name+"_"+country),new Text(sex+"_"+age+"_"+height+"_"+weight));

            }
        }
    }

    public static class AnalysisTextReducer extends Reducer<Text,Text,Text, Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            //找到key的首字段
            String[] split =key.toString().split("_");
            if(!split[0].equals("forBasicInfoTable")){
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
                System.out.println("金牌数："+countGold+"银牌数："+countSilver+"铜牌数"+countBronze);
                //todo 只统计得奖多的，阈值用py算一下————不用了
                if(split[0].equals("forCountryTable")){
                    //计算总奖牌数
                    int totalMedal=countGold+countSilver+countBronze;
                    context.write(key, new Text(countGold + "_" + countSilver + "_" + countBronze+"_"+totalMedal));
                }
                else if (split[0].equals("forAthleteTable")) {
                    //计算得分
                    int score=countGold*5+countSilver*2+countBronze*1;
                    context.write(key, new Text(countGold + "_" + countSilver + "_" + countBronze+"_"+score));

                }
            }
        }
    }
}
