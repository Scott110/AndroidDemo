package com.scott.lib.manager;

import android.support.v7.widget.RecyclerView;

import com.scott.lib.anim.itemAnim.LandingAnimator;

/**
 * author: heshantao
 * data: 2017/1/22.
 * RecyclerView  动画管理
 */

public class ItemAnimManager {
    private static final String TAG = ItemAnimManager.class.getSimpleName();
    private volatile static ItemAnimManager INSTANCE;

    //构造方法私有
    private ItemAnimManager() {
    }

    //获取单例
    public static ItemAnimManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ItemAnimManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemAnimManager();
                }
            }
        }
        return INSTANCE;
    }


    public RecyclerView.ItemAnimator DEFAULT() {
        LandingAnimator animator = new LandingAnimator();
        return animator;
    }

}
