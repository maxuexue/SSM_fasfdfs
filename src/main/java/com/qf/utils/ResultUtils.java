package com.qf.utils;

import java.util.HashMap;

public class ResultUtils extends HashMap {

    //状态码  0:操作成功 1:操作失败
    private int code;
    private String message;
    private Object data;

    public ResultUtils() {
    }

    public ResultUtils(int code) {
        super.put("code", code);
    }

    public ResultUtils(int code, String message) {
        super.put("code", code);
        super.put("message", message);
    }

    public ResultUtils(int code, String message, Object data) {
        super.put("code", code);
        super.put("message", message);
        super.put("data", data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //设置静态方法方便直接调用,成功时调用
    public static ResultUtils success(){
        return new ResultUtils(0);
    }

    public static ResultUtils success(String message){
        return new ResultUtils(0, message);
    }

    //设置静态方法方便直接调用,失败时调用
    public static ResultUtils error(){
        return new ResultUtils(1);
    }

    public static ResultUtils error(String message){
        return new ResultUtils(1, message);
    }

    public ResultUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
