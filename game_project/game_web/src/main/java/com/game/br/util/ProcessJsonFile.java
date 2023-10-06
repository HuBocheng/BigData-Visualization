package com.game.br.util;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.*;

/**
 * @ClassName:ProcessJsonFile
 * @description：解析并规整json文件
 * @author:BochengHu
 * @date 2023-07-17  19:23
 */
public class ProcessJsonFile {

    public static String removeLines(String input, int numLines) {
        String[] lines = input.split("\\r?\\n");

        if (numLines >= lines.length) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = numLines; i < lines.length; i++) {
            sb.append(lines[i]);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public static String  addLinebreak(String input) {
        String output=input.replace("}", "}\n")
                .replace("{","{\n")
                .replace(",",",\n");
        return output;
    }
    public static String[] splitString(String input, String regex) {
        return input.split(regex);
    }
    public static void totalProcess(String inputPath, String outputPath){
        try (FileReader reader = new FileReader(inputPath);
             FileWriter writer = new FileWriter(outputPath)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser parser = new JsonParser();

            // 解析JSON文件
            Object obj = parser.parse(reader);

            // 将解析后的JSON重新格式化并添加换行符
            String prettyJson = gson.toJson(obj);
            //prettyJson=addLinebreak(prettyJson);

            //删除前4行数据
            prettyJson=removeLines(prettyJson,4);

            //删除指定字符
            prettyJson=prettyJson.replace("{","[").replace("}","]");

            prettyJson=prettyJson.replace("\"country\": ","");
            prettyJson=prettyJson.replace("\"totalBronzeNum\": ","");
            prettyJson=prettyJson.replace("\"totalGoldNum\": ","");
            prettyJson=prettyJson.replace("\"totalNum\": ","");
            prettyJson=prettyJson.replace("\"totalSilverNum\": ","");
            prettyJson=prettyJson.replace("\"year\": ","");
            prettyJson=prettyJson.replace(" ","");
            //划分数据
            //String []split=splitString(prettyJson,"[:,]\\s");
            String []split=splitString(prettyJson,"[:,]");

            String output="["+"\n"+"[\"coutry\",\"totalBronzeNum\",\"totalGoldNum\",\"totalNum\",\"totalSilverNum\",\"year\"],\n"
                    +prettyJson
                    +"]";

            output=output+"]";


            //国家名的map
            output=NOCMapper.mapStringPlus(output);

            // 将结果写入输出文件
            writer.write(output);

            System.out.println("处理完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void totalProcess2(String inputPath, String outputPath){
        try (FileReader reader = new FileReader(inputPath);
             FileWriter writer = new FileWriter(outputPath)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser parser = new JsonParser();

            // 解析JSON文件
            Object obj = parser.parse(reader);

            // 将解析后的JSON重新格式化并添加换行符
            String prettyJson = gson.toJson(obj);
            //prettyJson=addLinebreak(prettyJson);

            //删除前4行数据
            prettyJson=removeLines(prettyJson,4);

            //删除指定字符
            prettyJson=prettyJson.replace("{","[").replace("}","]");

            prettyJson=prettyJson.replace("\"averageGDP\": ","");
            prettyJson=prettyJson.replace("\"country\": ","");
            prettyJson=prettyJson.replace("\"totalGDP\": ","");
            prettyJson=prettyJson.replace("\"year\": ","");
            prettyJson=prettyJson.replace(" ","");
            //划分数据
            //String []split=splitString(prettyJson,"[:,]\\s");
            String []split=splitString(prettyJson,"[:,]");

            String output="["+"\n"+"[\"averageGDP\",\"country\",\"totalGDP\",\"year\"],\n"
                    +prettyJson
                    +"]";

            output=output+"]";

            //国家名的map
            output=NOCMapper.mapStringPlus(output);

            //
            output=output.replace("-1","0");

            // 将结果写入输出文件
            writer.write(output);

            System.out.println("处理完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void totalProcess3(String inputPath, String outputPath) throws IOException {
        Path path = Paths.get(inputPath);
        byte[] bytes = Files.readAllBytes(path);
        String str= new String(bytes);

        //删除指定字符
        str=str.replace("{","[").replace("}","]");
        str=str.replace("\'","\"");

        str=str.replace("country=","");
        str=str.replace("year=","");
        str=str.replace("accGoldNum=","");
        str=str.replace("accSilverNum=","");
        str=str.replace("accBronzeNum=","");
        str=str.replace("acctotalNum=","");
        str=str.replace("AccMedalData","");
        str=str.replace(" ","");
        //划分数据
        //String []split=splitString(prettyJson,"[:,]\\s");
        //String []split=splitString(prettyJson,"[:,]");

        //String output="["+"\n"+"[\"averageGDP\",\"country\",\"totalGDP\",\"year\"],\n"
        //        +prettyJson
        //        +"]";

        //output=output+"]";

        //国家名的map
        //output=NOCMapper.mapStringPlus(output);
        //
        //output=output.replace("-1","0");


        // 将结果写入输出文件
        //writer.write(output);

        str=NOCMapper.mapStringPlus(str);

        Path path2 = Paths.get(outputPath);
        Files.write(path2, str.getBytes());

        System.out.println("处理完成！");

    }

    public static void main(String[] args) throws IOException {
        totalProcess3("datas/response3.txt","datas/response3_2.json");

    }
}
