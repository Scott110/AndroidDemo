package com.scott.demo;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.scott.demo.adapter.CustomerAdapter;
import com.scott.demo.bean.Animal;
import com.scott.demo.bean.Item;
import com.scott.demo.bean.Person;
import com.scott.demo.bean.Student;
import com.scott.demo.databinding.FragmentSeconderBinding;
import com.scott.demo.databinding.ItemRedTxtBinding;
import com.scott.libstyle.EventHandler;
import com.scott.libstyle.DbindingEventCallback;
import com.scott.lib.dBinding.adapter.BaseItemViewSelector;
import com.scott.lib.dBinding.adapter.BindingRecyclerViewAdapter;
import com.scott.lib.dBinding.adapter.ItemView;
import com.scott.lib.dBinding.adapter.ItemViewArg;
import com.scott.lib.manager.LayoutManagers;
import com.scott.lib.ui.BaseRlvFragment;
import com.scott.lib.widget.recyclerView.XRecyclerView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author: heshantao
 * data: 2017/1/9.
 */

public class SeconderFragment extends BaseRlvFragment implements DbindingEventCallback<Person> {
    private static final String TAG = "SeconderFragment";
    FragmentSeconderBinding binding;
    Student student;
    Item item;
    List<Student> students;
    //List<Person> persons;
    ObservableArrayList persons;

    RecyclerView recyclerView;
    XRecyclerView xRecyclerView;

    public static SeconderFragment newInstance() {
        return new SeconderFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_seconder;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(_mActivity, getLayoutId());
        ArrayList<String> list = new ArrayList<>();
        list.add("天下");
        list.add("共荣");
        //binding.setList(list);
        binding.setAge(23);
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "scott");
        map.put("sex", "man");
        binding.setMap(map);
        binding.setMapKey("name");
        student = new Student();
        student.setAge(33);
        student.setSname("Scotthe。。。。");
        binding.setStu(student);

        EventHandle handle = new EventHandle(_mActivity);
        binding.setHandle(handle);
        binding.setFragment(this);

        Animal animal = new Animal();
        binding.setAnim(animal);
        animal.category.set("豹子");

        item = new Item();
        item.setQq("很好");
        binding.setItem(item);

        recyclerView = binding.rlv;
        xRecyclerView = binding.xrlv;
        binding.setUrl("http://sys.files.1dabang.cn/upload/sys/images/2016-04-28/fb5870e2-1068-4a86-b9e1-40460a740ba5.jpg");

        initRecyclerView();

    }

    @Override
    public void requestData() {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setAge(i + "");
            person.setName("Scott****：：：：：" + i * 5);
            person.setPicUrl("http://sys.files.1dabang.cn/upload/sys/images/2016-04-28/fb5870e2-1068-4a86-b9e1-40460a740ba5.jpg");
            persons.add(person);
        }
        xRecyclerView.setNoMore(true);
    }


    void initRecyclerView() {
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        students = new ArrayList<Student>();
        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            student.setAge(i);
            student.setSname("何善涛：：：：：" + i);
            students.add(student);
        }


        //persons = new ArrayList<Person>();
        persons = new ObservableArrayList();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setAge(i + "");
            person.setName("Scott1111111111：：：：：" + i);
            person.setPicUrl("http://sys.files.1dabang.cn/upload/sys/images/2016-04-28/fb5870e2-1068-4a86-b9e1-40460a740ba5.jpg");
            persons.add(person);
        }

        //EasyRecyclerAdapter adapter=new EasyRecyclerAdapter()

        RecyclerView.LayoutManager linearLayoutManager = LayoutManagers.linear(LinearLayoutManager.VERTICAL, false).create(recyclerView);

        ItemView itemView = ItemView.of(BR.subStu, R.layout.item_text);

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

        ItemViewArg<Person> arg = ItemViewArg.of(selector);

        //BindingRecyclerViewAdapter<Person> adapter = BindingRecyclerViewAdapterFactory.DEFAULT.create(recyclerView, arg);

        MyAdapter<Person> adapter = new MyAdapter<Person>(arg);
        //adapter.onAttachedToRecyclerView(recyclerView);
        adapter.onAttachedToRecyclerView(xRecyclerView);

        adapter.setItems(persons);
      /*  adapter.setViewHolderFactory(new BindingRecyclerViewAdapter.ViewHolderFactory() {
            @Override
            public RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding) {
                return null;
            }
        });*/


        //recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setAdapter(adapter);

        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);
        setRecyclerView(xRecyclerView);
        setLoadingMore();
    }

    @Override
    public void onViewClick(Person person) {
        Toast.makeText(_mActivity, "在Fragment中处理有参数点击", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewClick() {

    }

    @Override
    public void onViewLongClick(Person person) {

    }

    @Override
    public void onViewLongClick() {

    }


    //自定义Adapter 可以进行点击效果
    class MyAdapter<Person> extends BindingRecyclerViewAdapter<Person> {
        String name = "11111";

        public MyAdapter(@NonNull ItemViewArg<Person> arg) {
            super(arg);
        }

        @Override
        public void onBindBinding(ViewDataBinding binding, int bindingVariable, @LayoutRes int layoutRes, int position, Person item) {
            super.onBindBinding(binding, bindingVariable, layoutRes, position, item);

            EventHandler handle = new EventHandler(_mActivity);
            handle.setEventCallback(SeconderFragment.this);
            if (binding instanceof ItemRedTxtBinding) {
                ((ItemRedTxtBinding) binding).setHandler(handle);
            }


            Log.d(TAG, "bound binding: " + binding + " at position: " + position);

        }


        public String getName() {
            return name;
        }
    }


    public void onBtnClick(View view) {
        // Log.d(TAG, "onClick: hhhhhhTAOTAOTAOTAO");
        //student.getSname("医大帮");
        //item.setQq("11111111111");
        //Person person = new Person();
        //person.setName("何善涛");
        //persons.add(person);
        //persons.get(5).setName("何善涛");
        //persons.add(person);

        //persons.remove(1);
        //Person sub = (Person) (persons.get(5));
        //sub.setName("何善涛11-");

        BindingRecyclerViewAdapter adapter = create("com.scott.demo.adapter.CustomerAdapter");
        if (adapter instanceof CustomerAdapter) {
            String name = ((CustomerAdapter) adapter).getName();
            Toast.makeText(_mActivity, "通过反射获得的用户名" + name, Toast.LENGTH_SHORT).show();
        }
    }


    public BindingRecyclerViewAdapter create(String className) {

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

        ItemViewArg arg = ItemViewArg.of(selector);
        try {
            Class clazz = Class.forName(className);
            Constructor<? extends BindingRecyclerViewAdapter> adapter =
                    clazz.getDeclaredConstructor(ItemViewArg.class);
            return adapter.newInstance(arg);
        } catch (Throwable e) {
            throw new RuntimeException(
                    "Unable to create Adapter for" + className + e.getCause().getMessage(), e);
        }

    }
}
