package com.scott.lib.widget.recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.scott.libstyle.EventHandler;
import com.scott.libstyle.loadMore;


/**
 * author: heshantao
 * data: 2017/2/4.
 */

public class LoadingMoreFooter extends FrameLayout {
    private static final String TAG = LoadingMoreFooter.class.getSimpleName();
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    public final static int STATE_LOADINGERRO = 3;
    private String loadingHint;
    private String noMoreHint;
    private String loadingDoneHint;
    private String loadingErroHint;
    Context mCxt;
    TextView hintTxt;
    loadMore bind;


    public LoadingMoreFooter(Context context) {
        super(context);
        mCxt = context;
        initView();
    }

    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCxt = context;
        initView();
    }

    private void initView() {
        setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bind = DataBindingUtil.inflate(LayoutInflater.from(mCxt), com.scott.libstyle.R.layout.default_load_more, this, false);
        hintTxt = bind.loadingMoreTv;
        this.addView(bind.getRoot());
    }

    public void setEventHandler(EventHandler handler) {
        bind.setHandler(handler);
    }

    public void setLoadingDoneHint(String loadingDoneHint) {
        this.loadingDoneHint = loadingDoneHint;
    }

    public void setLoadingErroHint(String loadingErroHint) {
        this.loadingErroHint = loadingErroHint;
    }

    public void setLoadingHint(String loadingHint) {
        this.loadingHint = loadingHint;
    }

    public void setNoMoreHint(String noMoreHint) {
        this.noMoreHint = noMoreHint;
    }


    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
                hintTxt.setText("正在加载中");
                this.setVisibility(VISIBLE);
                break;
            case STATE_COMPLETE:
                hintTxt.setText("加载完成");
                this.setVisibility(GONE);
                break;
            case STATE_NOMORE:
                hintTxt.setText("没有更多数据");
                this.setVisibility(VISIBLE);
                break;
            case STATE_LOADINGERRO:
                hintTxt.setText("加载出错");
                bind.setLoadingErro(true);
                this.setVisibility(VISIBLE);
                break;
            default:
                break;
        }
    }
}
