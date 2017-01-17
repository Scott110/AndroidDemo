package com.scott.libhttp.manager;

import com.scott.libhttp.exception.ApiExecption;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * author: heshantao
 * data: 2017/1/17.
 * 各种异常处理
 */

public class ExecptionManager {
    private final String TAG = this.getClass().getSimpleName();

    public static void doExecption(Throwable throwable) {

        if (throwable instanceof HttpException) {//网络异常

        } else if (throwable instanceof ApiExecption) {//服务器返回异常

        } else if (throwable instanceof SocketTimeoutException) {//链接超时

        } else if (throwable instanceof UnknownHostException) {//服务异常

        } else {//网络异常

        }

    }
}
