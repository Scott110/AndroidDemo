package com.scott.demo;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.scott.lib.ui.BaseActivity;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import me.yokeyword.fragmentation.SupportFragment;
import rx.functions.Action1;

/**
 * Created by scott_he on 16/10/17.
 */

public class TestActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.acitivity_test;
    }

    @Override
    public SupportFragment getRootFragment() {
        return TestFragment.newInstance();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        requestPermission();
    }

    public void requestPermission() {
        RxPermissions permissions = new RxPermissions(this);
        permissions.requestEach(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe(new Action1<Permission>() {
            @Override
            public void call(Permission permission) {
                if (permission.granted) {
                    //通过授权
                } else {
                    DialogFactory factory=new DialogFactory(getSupportFragmentManager());
                    factory.showPremissionSettingDialog("友好提醒","权限获取失败对功能有影响请到设置中设置","好，去设置","取消");

                    //Uri packageURI = Uri.parse("package:" + getPackageName());
                    //Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                    //startActivity(intent);
                    //finish();
                }
            }
        });
    }
}
