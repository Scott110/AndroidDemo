package com.scott.lib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.scott.lib.R;
import com.scott.lib.modle.event.AppExitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        init(savedInstanceState);
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


    public abstract void init(Bundle savedInstanceState);


    //权限申请
    /*public void requestPermission() {
        RxPermissions permissions = new RxPermissions(this);
        permissions.requestEach(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe(new Action1<Permission>() {
            @Override
            public void call(Permission permission) {
                if (permission.granted) {
                    //通过授权
                } else {
                    Uri packageURI = Uri.parse("package:" + getPackageName());
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
