package com.game.producer.io;

import com.game.common.bean.Data;
import com.game.common.bean.DataIn;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:LocalFileDataIn
 * @description：本地数据输入
 * @author:BochengHu
 * @date 2023-07-11  15:03
 */
public class LocalFileDataIn implements DataIn {
    private BufferedReader read=null;//字符输入流
    public LocalFileDataIn(String path) {
        setPath(path);
    }

    @Override
    public void setPath(String path) {//根据地址将文件读取的字符串输入流中
        try {
            read=new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read() throws IOException {
        return null;
    }



    @Override
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {//将数据文件每行作为一个对象存在一个列表中
        //写一个集合
        List<T> ts=new ArrayList();
        try {
            //从数据文件中读取所有数据
            String line=null;
            //int i=0;
            while((line=read.readLine())!=null) {//读取数据不为空
                T t = clazz.newInstance();//反射机制构造对象
                t.setValue(line);
                ts.add(t);
                //i+=1;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return ts;
    }

    @Override
    public void close() throws IOException {
        if(read!=null){
            read.close();
        }
    }

}
