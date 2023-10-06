package com.game.analysis.io;

import com.game.common.util.JdbcUtil;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitterFactory;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName:MySQLTextOutputFormat
 * @description：设置reduce产生的数据以什么方式存入数据库
 * @author:BochengHu
 * @date 2023-07-13  23:30
 */
public class MySQLTextOutputFormat extends OutputFormat<Text, Text> {
    //连接到数据库
    protected static class MySQLRecordWriter extends RecordWriter<Text,Text>{
        private Connection connection=null;
        public MySQLRecordWriter(){
            //获取资源
            connection= JdbcUtil.getConnection();
        }

        @Override
        public void write(Text key, Text value) throws IOException, InterruptedException {
            String[] values=value.toString().split("_");
            String[] keys=key.toString().split("_");

            //找到插入的表
            String tableName=keys[0];

            //按插入表不同写不同SQL
            //先预定义SQL
            String insertSQL1="insert into athleteShow(name,country,sport,goldNum,silverNum,bronzeNum,score) values(?,?,?,?,?,?,?)";
            String insertSQL2="insert into countryShow(country,year,sport,goldNum,silverNum,bronzeNum,totalNum) values(?,?,?,?,?,?,?)";


            //再准备预执行sql语句对象
            PreparedStatement ps=null;

            //按插入表不同写不同给占位符赋值
            if(tableName.equals("forAthleteTable")){
                String name=keys[1];
                String country=keys[2];
                String sport=keys[3];

                String countGold=values[0];
                String countSilver=values[1];
                String countBronze=values[2];
                String score=values[3];

                try {
                    ps = connection.prepareStatement(insertSQL1);
                    ps.setString(1,name);
                    ps.setString(2,country );
                    ps.setString(3,sport);
                    ps.setInt(4,Integer.parseInt(countGold));
                    ps.setInt(5,Integer.parseInt(countSilver));
                    ps.setInt(6,Integer.parseInt(countBronze));
                    ps.setInt(7,Integer.parseInt(score));
                    ps.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            else if (tableName.equals("forCountryTable")) {
                String country=keys[1];
                String year=keys[2];
                String sport=keys[3];

                String countGold=values[0];
                String countSilver=values[1];
                String countBronze=values[2];
                String totalNum=values[3];

                try {
                    ps = connection.prepareStatement(insertSQL2);
                    ps.setString(1,country);
                    ps.setString(2, year);
                    ps.setString(3,sport);
                    ps.setInt(4,Integer.parseInt(countGold));
                    ps.setInt(5,Integer.parseInt(countSilver));
                    ps.setInt(6,Integer.parseInt(countBronze));
                    ps.setInt(7,Integer.parseInt(totalNum));
                    ps.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else if (tableName.equals("forFinanceTable")) {

            }
        }

        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            //释放资源
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new MySQLRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }
    private PathOutputCommitter committer = null;
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null: new Path(name);
    }
    //提交mapreduce给hadoop

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = PathOutputCommitterFactory.getCommitterFactory(
                    output,
                    context.getConfiguration()).createOutputCommitter(output, context);
        }
        return committer;
    }
}
