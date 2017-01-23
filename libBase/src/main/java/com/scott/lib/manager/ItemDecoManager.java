package com.scott.lib.manager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.scott.lib.widget.itemDecoration.HorizontalDividerItemDecoration;
import com.scott.lib.widget.itemDecoration.VerticalDividerItemDecoration;
import com.scott.libstyle.R;

/**
 * author: heshantao
 * data: 2017/1/22.
 * 装饰器管理器
 */

public class ItemDecoManager {
    private static final String TAG = ItemDecoManager.class.getSimpleName();
    private volatile static ItemDecoManager INSTANCE;

    //构造方法私有
    private ItemDecoManager() {
    }

    //获取单例
    public static ItemDecoManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ItemDecoManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemDecoManager();
                }
            }
        }
        return INSTANCE;
    }

    public RecyclerView.ItemDecoration DEFAULT(Context mCxt) {
        HorizontalDividerItemDecoration dec = new HorizontalDividerItemDecoration.Builder(mCxt)
                .color(R.color.default_divider_line_color)
                .sizeResId(R.dimen.default_divider_line_size)
                .build();
        return dec;
    }
}
