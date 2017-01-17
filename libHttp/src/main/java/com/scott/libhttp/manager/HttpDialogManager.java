package com.scott.libhttp.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * author: heshantao
 * data: 2017/1/17.
 */

public class HttpDialogManager {

    private static final String HTTP_DIALOG_LOADING_TAG = "loading";

    FragmentManager mFragmentManager;

    public HttpDialogManager(FragmentManager manager) {
        this.mFragmentManager = manager;
    }

    //提交任务时候加载框
    public void showLoadingDialog(AppCompatDialogFragment dialogFragment) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentManager.findFragmentByTag(HTTP_DIALOG_LOADING_TAG);
        if (null != fragment) {
            ft.remove(fragment).commit();
        }
        dialogFragment.show(mFragmentManager, HTTP_DIALOG_LOADING_TAG);
        mFragmentManager.executePendingTransactions();
    }


    //隐藏加载框
    public void dissLoadingDialog() {
        Fragment fragment = mFragmentManager.findFragmentByTag(HTTP_DIALOG_LOADING_TAG);
        if (null != fragment) {
            ((AppCompatDialogFragment) fragment).dismiss();
            mFragmentManager.beginTransaction().remove(fragment).commit();
        }
    }
}
