package com.scott.lib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.scott.lib.R;
import com.scott.lib.data.event.AppExitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by scott_he on 16/10/17.RxFragment.java
 */

public abstract class BaseActivity extends RxActivity {
    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, getRootFragment());
        }

        EventBus.getDefault().register(this);
        initData();
        initView();
    }


    public void sendExitEvent() {
        EventBus.getDefault().post(new AppExitEvent());
    }


    //彻底退出APP
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAppExitEvent(AppExitEvent event) {
        finish();
    }


    //获得容器布局文件
    public abstract int getLayoutId();

    //获得添加到根目录的Fragment
    public abstract SupportFragment getRootFragment();

    //初始化数据
    public abstract void initData();

    //初始化界面
    public abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
