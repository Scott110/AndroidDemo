package com.scott.libhttp.exception;

/**
 * author: heshantao
 * data: 2017/1/17.
 * API 调用异常
 */

public class ApiExecption extends RuntimeException {
    public ApiExecption(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiExecption(String detailMessage) {
        super(detailMessage);
    }


    private static String getApiExceptionMessage(int resultCode) {
        return "erro";
    }
}
