package com.scott.demo;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scott.demo.api.PersonApi;
import com.scott.demo.bean.Person;
import com.scott.demo.databinding.FragmentThirdBinding;
import com.scott.lib.config.RecyclerViewConfiguration;
import com.scott.lib.dBinding.adapter.BaseItemViewSelector;
import com.scott.lib.dBinding.adapter.ItemView;
import com.scott.lib.manager.RlvConfigManager;
import com.scott.lib.ui.BaseRlvFragment;
import com.scott.lib.widget.dialog.LoadingProgressDialog;
import com.scott.libhttp.callback.HttpOnNextCallback;
import com.scott.libhttp.manager.HttpManager;

import java.util.List;

/**
 * author: heshantao
 * data: 2017/1/22.
 */


public class ThirdFragment extends BaseRlvFragment {
    FragmentThirdBinding binding;
    RecyclerView recyclerView;
    ObservableArrayList persons;
    ViewPager vp;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third;
    }


    @Override
    public void init(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(_mActivity, getLayoutId());
        binding.setFragment(this);
        recyclerView = binding.rlv;
        initRecyclerView();

        //initViewPage();
        initVaryView(recyclerView);

        //requestList();
    }

    @Override
    public void requestData() {

    }


    void requestList() {
        //PersonApi api = new PersonApi(this);
        //api.setId("23");
        //api.setCallback(callback);
        //api.setShowVaryLoadingView(true);
        //api.setmView(this);
        //api.setShowProgressDialog(true);
        LoadingProgressDialog dialogFragment = LoadingProgressDialog.newInstance();
        //api.setProgressDialog(dialogFragment);
        //HttpManager.doHttpDeal(api, _mActivity);
    }


    HttpOnNextCallback<List<Person>> callback = new HttpOnNextCallback<List<Person>>() {
        @Override
        public void onNext(List list) {
            //Toast.makeText(_mActivity, "用户名" + person.getName(), Toast.LENGTH_SHORT).show();
            persons.addAll(list);

        }

    };


    void initRecyclerView() {
        persons = new ObservableArrayList();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setAge(i + "");
            person.setName("Scott：：：：：" + i);
            person.setPicUrl("http://sys.files.1dabang.cn/upload/sys/images/2016-04-28/fb5870e2-1068-4a86-b9e1-40460a740ba5.jpg");
            //persons.add(person);
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
        /*RecyclerViewConfiguration configManager = RlvConfigManager.getInstance()
                .mutileRecyclerConfig(recyclerView, selector, persons, adapterName);
        configRecyclerView(configManager);
*/
    }

    public void onBtnClick(View view) {
       /* ObservableArrayList<Person> addList = new ObservableArrayList();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setAge(i + "");
            person.setName("何善涛" + i);
            person.setPicUrl("http://sys.files.1dabang.cn/upload/sys/images/2016-04-28/fb5870e2-1068-4a86-b9e1-40460a740ba5.jpg");
            persons.add(person);
        }

        persons.addAll(addList);*/
        requestList();

    }
}
