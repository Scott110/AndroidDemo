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
    public RecyclerView.ItemAnimator DEFAULT() {
        LandingAnimator animator = new LandingAnimator();
        return animator;
    }

}
