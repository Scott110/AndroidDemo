package com.scott.demo.simple.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scott.demo.BR;
import com.scott.demo.MyApplication;
import com.scott.demo.R;
import com.scott.demo.api.PersonApi;
import com.scott.demo.bean.Person;
import com.scott.demo.di.InjectHelper;
import com.scott.demo.di.component.DaggerFragmentComponent;
import com.scott.demo.di.component.FragmentComponent;
import com.scott.demo.di.module.FragmentModule;
import com.scott.demo.di.qualifier.ApplicationContext;
import com.scott.demo.dill;
import com.scott.lib.config.RecyclerViewConfiguration;
import com.scott.lib.dBinding.adapter.BaseItemViewSelector;
import com.scott.lib.dBinding.adapter.ItemView;
import com.scott.lib.manager.RlvConfigManager;
import com.scott.lib.ui.BaseFragment;
import com.scott.lib.ui.BaseRlvFragment;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * author: heshantao
 * data: 2017/2/5.
 * 依赖注入测试
 */

public class DiFragment extends BaseRlvFragment implements DiVMContract.View<Person> {
    FragmentComponent fragmentComponent;
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
    ObservableArrayList persons;

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
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setAge(i + "");
            person.setName("Scott：：：：：" + i);
            person.setPicUrl("http://sys.files.1dabang.cn/upload/sys/images/2016-04-28/fb5870e2-1068-4a86-b9e1-40460a740ba5.jpg");
            persons.add(person);
        }

        BaseItemViewSelector<Person> selector = new BaseItemViewSelector<Person>() {
            @Override
            public void select(ItemView itemView, int position, Person item) {
                if (position % 2 == 0) {
                    itemView.set(BR.person, R.layout.item_text);
                } else {
                    itemView.set(BR.person, R.layout.item_red_txt);
                }
            }
        };

        String adapterName = "com.scott.demo.adapter.CustomerAdapter";
        RecyclerViewConfiguration configManager = rlvConfigManager
                .mutileRecyclerConfig(recyclerView, selector, persons, adapterName);
        configRecyclerView(configManager);

    }


    @OnClick(R.id.api_btn)
    void onClick() {
        api.setId("24");
        viewModule.requestPersonInfo(api);
    }
}
