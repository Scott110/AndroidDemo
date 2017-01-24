package com.scott.lib.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: heshantao
 * data: 2017/1/24.
 * 网络请求加载对话框
 */

public class LoadingProgressDialog extends AppCompatDialogFragment {
    private static final String TAG = LoadingProgressDialog.class.getSimpleName();

    public static LoadingProgressDialog newInstance() {
        LoadingProgressDialog instance = new LoadingProgressDialog();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.scott.libstyle.R.layout.default_loading_progress_dialog, container, false);
        return view;
    }
}
