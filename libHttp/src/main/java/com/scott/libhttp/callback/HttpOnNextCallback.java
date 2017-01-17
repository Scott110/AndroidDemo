package com.scott.libhttp.callback;

/**
 * author: heshantao
 * data: 2017/1/17.
 * 成功回调处理
 */

public abstract class HttpOnNextCallback<T> {
    /*
    *成功回调
    *@param t
    */
    public abstract void onNext(T t);

    /**
     * 緩存回調結果
     *
     * @param string
     */
    public void onCacheNext(String string) {

    }

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     *
     * @param e
     */
    public void onError(Throwable e) {

    }

    /**
     * 取消回調
     */
    public void onCancel() {

    }

}
