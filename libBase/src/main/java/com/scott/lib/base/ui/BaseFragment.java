package com.scott.lib.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by scott_he on 16/10/17.
 */

public abstract class BaseFragment extends SupportFragment {
    public final String TAG = this.getClass().getSimpleName();
    Unbinder unBinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }

    //获得容器布局文件
    public abstract int getLayoutId();

    //初始化
    public abstract void init(Bundle savedInstanceState);

    //懒加载时候调用
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unBinder = null;
    }
}
