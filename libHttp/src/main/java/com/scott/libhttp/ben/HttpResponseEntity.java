package com.scott.libhttp.ben;

/**
 * author: heshantao
 * data: 2017/1/17.
 * http 返回实体封装
 */

public class HttpResponseEntity<T> {
    //提示信息
    private String message;
    //返回码
    private int code;
    //实体类
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
