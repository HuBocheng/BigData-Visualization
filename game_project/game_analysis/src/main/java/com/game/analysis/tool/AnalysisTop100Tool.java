package com.game.analysis.tool;

import com.game.analysis.io.MySQLTextOutputFormat;
import com.game.analysis.io.MySQLTop100OutputFormat;
import com.game.analysis.mr.AnalysisTextAthlete;
import com.game.analysis.mr.AnalysisTop100;
import com.game.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

/**
 * @ClassName:AnalysisTop100Tool
 * @description：Todo
 * @author:BochengHu
 * @date 2023-07-14  13:47
 */
public class AnalysisTop100Tool implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        //获取job
        Job job= Job.getInstance();
        //设置jar位置
        job.setJarByClass(AnalysisTop100Tool.class);
        //扫描列族
        Scan scan=new Scan();
        scan.addFamily(Bytes.toBytes(Names.GAME_INFO.getValue()));
        //设置mapper
        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),
                scan,
                AnalysisTop100.AnalysisTop100Mapper.class,
                Text.class,
                Text.class,
                job
        );
        //设置reducer
        job.setReducerClass(AnalysisTop100.AnalysisTop100Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //设置输出
        job.setOutputFormatClass(MySQLTop100OutputFormat.class);
        //提交
        boolean flag = job.waitForCompletion(true);
        if(flag){
            return JobStatus.State.SUCCEEDED.getValue();
        }else{
            return JobStatus.State.FAILED.getValue();
        }
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
