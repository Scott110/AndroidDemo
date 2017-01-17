package com.scott.lib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scott.lib.R;

import butterknife.ButterKnife;

/**
 * author: heshantao
 * data: 2017/1/13.
 */

public abstract class EasyViewHolder<T> extends RecyclerView.ViewHolder {

    public EasyViewHolder(Context context, ViewGroup parent, int layoutId) {
        this(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    public EasyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bindTo(int position, T value);


}
