package com.game.br.util;


import java.io.Serializable;

//定义响应数据的实体类
public class ServerResponse<T> implements Serializable {
    private int code;//状态码
    private String msg;//提示消息
    private T data;//返回数据
    //private Integer total;//返回数据集元素的个数

    @Override
    public String toString() {
        return "ServerResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //成功操作，携带默认信息
    public static<T> ServerResponse<T>success(){
        return success(ResponseCode.SUCCESS.getMessage());
    }
    //成功操作，携带成功状态码、自定义消息、数据
    public static<T> ServerResponse<T>success(String message,T data){
        return success(ResponseCode.SUCCESS.getCode(),message,data);
    }
    //成功操作，携带成功状态码、自定义消息
    public static<T> ServerResponse<T>success(String message){
        return success(message,null);
    }
    //成功操作，携带成功状态码、默认消息、数据
    public static<T> ServerResponse<T>success(T data){
        return success(ResponseCode.SUCCESS.getMessage(),data);
    }
    //成功操作，携带自定义成功状态码、自定义消息和数据
    public static<T> ServerResponse<T>success(int code,String message){
        return success(code,message,null);
    }
    //成功操作,携带⾃定义状态码、消息、数据和数据集中元素
    public static<T> ServerResponse<T>success(int code,String message,T data){
        ServerResponse<T> result=new ServerResponse<>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }
    //失败操作,携带默认数据
    public static<T> ServerResponse<T> failure(){
        return failure(ResponseCode.FAILURE.getMessage());
    }
    //失败操作,携带失败状态码、自定义消息
    public static <T> ServerResponse<T> failure(String message) {
        return failure(message,null);
    }
    //失败操作,携带失败状态码、自定义消息、数据
    public static <T> ServerResponse<T> failure(String message, T data) {
        return failure(ResponseCode.FAILURE.getCode(),message,data);
    }
    //失败操作，携带自定义状态码、自定义消息
    public static <T> ServerResponse<T> failure(int code, String message) {
        return failure(code,message,null);
    }
    //失败操作，携带自定义状态码、消息、数据
    public static<T> ServerResponse<T> failure(int code, String message, T data){
        ServerResponse<T> result=new ServerResponse<>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }
}