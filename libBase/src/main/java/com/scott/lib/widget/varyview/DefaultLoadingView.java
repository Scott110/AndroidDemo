package com.scott.lib.widget.varyview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.scott.lib.R;

/**
 * author: heshantao
 * data: 2017/1/17.
 */

public class DefaultLoadingView extends FrameLayout {
    public DefaultLoadingView(Context context) {
        super(context);
        initialize(context);
    }


    private void initialize(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.default_loading_view, this, false);
        addView(view);
    }
}
