package com.game.producer.io;

import com.game.common.bean.DataOut;

import java.io.IOException;
import java.io.*;

/**
 * @ClassName:LocalFileDataOut
 * @description：本地文件数据输出
 * @author:BochengHu
 * @date 2023-07-11  15:12
 */
public class LocalFileDataOut implements DataOut {
    private PrintWriter writer=null;//向文件中传输数据
    public LocalFileDataOut(String path) {
        setPath(path);
    }//构造函数用setPath函数构造writer
    @Override
    public void setPath(String path) {
        try {
            writer=new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));//利用文件输出流，根据path定义一个向指定路径写数据的对象
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void write(Object obj) throws Exception {
        write(obj.toString());
    }

    @Override
    public void write(String data) throws Exception {
        writer.println(data);//字符串数据放入文件输出流
        writer.flush();//刷新输出流，把流中的文件放到文件中
    }



    @Override
    public void close() throws IOException {
        if(writer!=null){
            writer.close();
        }
    }
}
