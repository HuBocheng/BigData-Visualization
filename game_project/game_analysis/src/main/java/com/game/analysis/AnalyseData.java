package com.game.analysis;

import com.game.analysis.tool.AnalysisTextTool;
import com.game.analysis.tool.AnalysisTop100Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @ClassName:AnalyseData
 * @description：分析数据主方法类
 * @author:BochengHu
 * @date 2023-07-13  23:34
 */
public class AnalyseData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new AnalysisTextTool(), args);
    }
}
