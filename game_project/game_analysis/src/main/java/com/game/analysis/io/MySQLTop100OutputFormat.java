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
 * @ClassName:MySQLTop100OutputFormat
 * @description：Todo
 * @author:BochengHu
 * @date 2023-07-14  14:03
 */
public class MySQLTop100OutputFormat extends OutputFormat<Text, Text>{
    protected static class MySQLRecordWriter extends RecordWriter<Text,Text> {
        private Connection connection = null;

        public MySQLRecordWriter() {
            //获取资源
            connection = JdbcUtil.getConnection();
        }

        @Override
        public void write(Text key, Text value) throws IOException, InterruptedException {
            String[] values = value.toString().split("_");
            String countGold = values[0];
            String countSilver = values[1];
            String countBronze = values[2];
            String score = values[3];

            String[] keys = key.toString().split("_");
            String country = keys[1];
            String name = keys[0];
            //直接写sql
            String insertSQL = "insert into Top100Athletes(name,country,goldNum,silverNum,bronzeNum,score) values(?,?,?,?,?,?)";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(insertSQL);
                ps.setString(1, name);
                ps.setString(2, country);
                ps.setInt(3, Integer.parseInt(countGold));
                ps.setInt(4, Integer.parseInt(countSilver));
                ps.setInt(5, Integer.parseInt(countBronze));
                ps.setInt(6, Integer.parseInt(score));
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            //释放资源
            if (connection != null) {
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
