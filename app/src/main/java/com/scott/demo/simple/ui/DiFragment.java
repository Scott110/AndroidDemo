package com.scott.demo.simple.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.scott.demo.BR;
import com.scott.demo.R;
import com.scott.demo.api.PersonApi;
import com.scott.demo.bean.Person;
import com.scott.demo.di.InjectHelper;
import com.scott.demo.di.qualifier.ApplicationContext;
import com.scott.demo.dill;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.ItemBinding;
import com.scott.lib.dBinding.adapter.OnItemBind;
import com.scott.lib.manager.RlvConfigManager;
import com.scott.lib.ui.BaseRlvFragment;
import com.scott.libstyle.DbindingEventCallback;
import com.scott.libstyle.EventHandler;

import javax.inject.Inject;

/**
 * author: heshantao
 * data: 2017/2/5.
 * 依赖注入测试
 */

public class DiFragment extends BaseRlvFragment implements DiVMContract.View<Person>, DbindingEventCallback {
    @Inject
    @ApplicationContext
    Context context;
    @Inject
    DiViewModule viewModule;
    @Inject
    PersonApi api;
    @Inject
    RlvConfigManager rlvConfigManager;
    dill binding;
    RecyclerView recyclerView;
    ObservableArrayList<Person> persons;
    EventHandler handler;

    public static DiFragment newInstance() {
        return new DiFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_di;
    }


    @Override
    public void init(Bundle savedInstanceState) {
        InjectHelper.getFragmentComponent(_mActivity, this).inject(this);
        binding = DataBindingUtil.setContentView(_mActivity, getLayoutId());
        recyclerView = binding.rlv;
        binding.setFragment(this);
        handler = new EventHandler(_mActivity, this);
        String name = context.getResources().getString(R.string.app_name);
        initRecyclerView();

    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {
        Toast.makeText(context, "调用网络数据成功", Toast.LENGTH_SHORT).show();
    }


    void initRecyclerView() {
        persons = new ObservableArrayList();
        for (int i = 0; i < 15; i++) {
            Person person = new Person();
            person.setAge(i + "");
            person.setName("Scott：：：：：" + i);
            person.setPicUrl("http://sys.files.1dabang.cn/upload/sys/images/2016-04-28/fb5870e2-1068-4a86-b9e1-40460a740ba5.jpg");
            persons.add(person);
        }


        OnItemBind<Person> itemBind = new OnItemBind<Person>() {
            @Override
            public void onItemBind(ItemBinding itemBinding, int position, Person item) {
                Log.d(TAG, "onItemBind: " + position);
                if (position % 2 == 0) {
                    itemBinding.set(BR.person, R.layout.item_text);
                } else {
                    itemBinding.set(BR.person, R.layout.item_red_txt);
                    itemBinding.bindExtra(BR.handler, handler);
                }
            }
        };
        ItemBinding itemBinding = ItemBinding.of(itemBind);
        BindingRecyclerViewAdapter adapter = new BindingRecyclerViewAdapter();
        adapter.setItemBinding(itemBinding);
        adapter.setItems(persons);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        adapter.onAttachedToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);


        //String adapterName = "com.scott.demo.adapter.CustomerAdapter";
        //RecyclerViewConfiguration configManager = rlvConfigManager
        //      .mutileRecyclerConfig(recyclerView, selector, persons, adapterName);
        //configRecyclerView(configManager);

    }


   /* @OnClick(R.id.api_btn)
    void onClick() {
        api.setId("24");
        //viewModule.requestPersonInfo(api);
        persons.get(0).setName("我很好呀");
    }*/


    public void onClick1(View view) {
        Log.d(TAG, "onClick1: 1111");
        persons.get(0).setName("我很好呀");
    }

    @Override
    public void onViewClick(View view, Object o) {
        Log.d(TAG, "点击了按钮");

    }

    @Override
    public void onViewClick(View view) {

    }

    @Override
    public void onViewLongClick(View view, Object o) {

    }

    @Override
    public void onViewLongClick(View view) {

    }
}
