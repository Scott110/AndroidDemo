package com.scott.lib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author: heshantao
 * data: 2017/1/13.
 * 基础适配器
 */

public class EasyRecyclerAdapter extends RecyclerView.Adapter<EasyViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private List<Object> dataList = new ArrayList<>();
    private BaseEasyViewHolderFactory viewHolderFactory;

    public EasyRecyclerAdapter(Context context, Class valueClass,
                               Class<? extends EasyViewHolder> easyViewHolderClass) {
        this(context);
        bind(valueClass, easyViewHolderClass);
    }

    public EasyRecyclerAdapter(Context context) {
        this(new BaseEasyViewHolderFactory(context));
    }

    public EasyRecyclerAdapter(BaseEasyViewHolderFactory easyViewHolderFactory, Class valueClass,
                               Class<? extends EasyViewHolder> easyViewHolderClass) {
        this(easyViewHolderFactory);
        bind(valueClass, easyViewHolderClass);
    }

    public EasyRecyclerAdapter(BaseEasyViewHolderFactory easyViewHolderFactory) {
        this.viewHolderFactory = easyViewHolderFactory;
    }


    public void bind(Class valueClass, Class<? extends EasyViewHolder> viewHolder) {
        viewHolderFactory.bind(valueClass, viewHolder);
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EasyViewHolder easyViewHolder = viewHolderFactory.create(viewType, parent);
        return easyViewHolder;
    }

    @Override
    public void onBindViewHolder(EasyViewHolder holder, int position) {
        holder.bindTo(position, dataList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return viewHolderFactory.itemViewType(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //特定位置添加数据
    public void add(Object object, int position) {
        dataList.add(position, object);
        notifyItemInserted(position);
    }


    public void add(Object object) {
        dataList.add(object);
        notifyItemInserted(getIndex(object));
    }

    //添加所有
    public void addAll(List<?> objects) {
        dataList.clear();
        appendAll(objects);
    }

    public void appendAll(List<?> objects) {
        if (objects == null) {
            throw new IllegalArgumentException("objects can not be null");
        }
        List<?> toAppends = filter(objects);
        final int toAppendSize = toAppends.size();
        if (toAppendSize <= 0) {
            return;
        }
        int prevSize = this.dataList.size();
        List<Object> data = new ArrayList<>(prevSize + toAppendSize);
        data.addAll(dataList);
        data.addAll(toAppends);
        dataList = data;
        notifyItemRangeInserted(prevSize, toAppends.size());
    }

    /**
     * 去掉重复数据
     */
    private List<?> filter(List<?> data) {
        List<Object> returnData = new ArrayList<>();
        List<?> localDataList = this.dataList;
        for (Object o : data) {
            if (!localDataList.contains(o)) {
                returnData.add(o);
            }
        }
        return returnData;
    }

    public boolean update(Object data, int position) {
        Object oldData = dataList.set(position, data);
        if (oldData != null) {
            notifyItemChanged(position);
        }
        return oldData != null;
    }

    public boolean remove(Object data) {
        return dataList.contains(data) && remove(getIndex(data));
    }

    public boolean remove(int position) {
        boolean validIndex = isValidIndex(position);
        if (validIndex) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
        return validIndex;
    }

    //清除所有
    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public List<Object> getItems() {
        return dataList;
    }

    public Object get(int position) {
        return dataList.get(position);
    }

    public int getIndex(Object item) {
        return dataList.indexOf(item);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    private boolean isValidIndex(int position) {
        return position >= 0 && position < getItemCount();
    }
}
