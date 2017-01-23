package com.scott.libhttp.manager;

import com.scott.libhttp.exception.ApiExecption;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * author: heshantao
 * data: 2017/1/17.
 * 异常管理类
 */

public class ExecptionManager {
    private final String TAG = this.getClass().getSimpleName();
    private volatile static ExecptionManager INSTANCE;

    //构造方法私有
    private ExecptionManager() {
    }

    //获取单例
    public static ExecptionManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ExecptionManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ExecptionManager();
                }
            }
        }
        return INSTANCE;
    }

    //是否是网络异常
    public boolean isNetException(Throwable throwable) {
        if (throwable instanceof ConnectException) {
            return true;
        }

        if (throwable instanceof ConnectTimeoutException) {
            return true;
        }

        if (throwable instanceof SocketTimeoutException) {
            return true;
        }

        if (throwable instanceof UnknownHostException) {
            return true;
        }
        return false;
    }


    //是否是服务器异常
    public  boolean isApiException(Throwable throwable) {
        if (throwable instanceof ApiExecption) {
            return true;
        }
        return false;
    }

    //是Http 请求异常
    public  boolean isHttpException(Throwable throwable) {
        if (throwable instanceof HttpException)
            return true;
        return false;
    }

}
