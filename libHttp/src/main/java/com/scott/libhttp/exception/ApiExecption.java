package com.scott.libhttp.exception;

/**
 * author: heshantao
 * data: 2017/1/17.
 * API 调用异常
 */

public class ApiExecption extends Exception {
    private int code;
    private String msg;

    public ApiExecption(int code, String message) {
        msg = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public int getCode() {
        return code;
    }


}
