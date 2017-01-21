package com.scott.lib.callback;

import java.util.List;

/**
 * author: heshantao
 * data: 2017/1/18.
 * 数据库操作回调
 */

public interface DbCallback<T> {

    public void findAll(List<T> list);
}
