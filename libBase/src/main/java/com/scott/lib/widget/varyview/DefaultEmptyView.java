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

public class DefaultEmptyView extends FrameLayout {
    public DefaultEmptyView(Context context) {
        super(context);
        initialize(context);
    }

    private void initialize(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.default_empty_view, this, false);
        addView(view);
    }
}
