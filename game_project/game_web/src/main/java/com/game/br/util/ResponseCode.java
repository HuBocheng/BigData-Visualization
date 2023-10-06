package com.game.br.util;
//定义响应状态码以及信息的枚举类
public enum ResponseCode {
    //成功
    SUCCESS(200,"操作成功"),
    //失败
    FAILURE(500,"操作失败");

    private final int code;//状态码
    private final String message;//携带消息

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResponseCode(int code, String message){
        this.code=code;
        this.message=message;
    }
}
