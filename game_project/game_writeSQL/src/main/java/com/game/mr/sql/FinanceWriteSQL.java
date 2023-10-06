package com.game.mr.sql;

import com.game.mr.entity.FinanceData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.StringTokenizer;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @ClassName:FinanceWriteSQL
 * @description：Todo
 * @author:BochengHu
 * @date 2023-07-16  16:48
 */
public class FinanceWriteSQL {
    public static class FinanceWriteSQLMapper extends Mapper<LongWritable, Text, Text, FinanceData> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FinanceData>.Context context)
                throws IOException, InterruptedException {
            String line=new String(value.getBytes(),0,value.getLength(),"GBK");
            //分割
            String []sti=line.split(",",-1);
            String country=sti[1];
            for (int i = 0; i < 62; i++) {
                int year= 2021-i;
                String totalGDP=sti[i+2];
                String avgGDP=sti[i+2+1+62];
                FinanceData v=new FinanceData();
                v.setCountry(country);
                v.setYear(year);
                if(!totalGDP.equals("")){
                    v.setTotalGDP(Float.parseFloat(totalGDP));
                }
                if(!avgGDP.equals("")){
                    v.setAverageGDP(Float.parseFloat(avgGDP));
                }

                //输出
                context.write(new Text(country + "_" + year), v);
            }
            System.out.println("一个国家信息全部Mapper");
        }
    }

    public static class FinanceWriteSQLReducer extends Reducer<Text,FinanceData,FinanceData, NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<FinanceData> values,
                              Reducer<Text, FinanceData, FinanceData, NullWritable>.Context context)
                throws IOException, InterruptedException {
            FinanceData bean=values.iterator().next();
            FinanceData k=new FinanceData(bean.getYear(),bean.getCountry(),bean.getTotalGDP(),bean.getAverageGDP());
            context.write(bean,NullWritable.get());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        args=new String[]{"datas/financeData.csv"};
        Configuration conf=new Configuration();
        DBConfiguration.configureDB(conf,"com.mysql.jdbc.Driver",
                "jdbc:mysql://hadoop101:3306/game_project?characterEncoding=utf-8",
                "root","root");
        //获取job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(FinanceWriteSQL.class);
        //关联mapper和reducer
        job.setMapperClass(FinanceWriteSQLMapper.class);
        job.setReducerClass(FinanceWriteSQLReducer.class);
        //设置mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FinanceData.class);
        //设置最终输出类型
        job.setOutputKeyClass(FinanceData.class);
        job.setOutputValueClass(NullWritable.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        String[] fields={"country","year","totalGDP","averageGDP"};
        DBOutputFormat.setOutput(job,"financeShow",fields);
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
