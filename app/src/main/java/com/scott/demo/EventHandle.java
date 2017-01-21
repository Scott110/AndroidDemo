package com.scott.demo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.scott.demo.bean.Person;

/**
 * author: heshantao
 * data: 2017/1/19.
 * <p>
 * 事件绑定
 */

public class EventHandle {
    Context context;

    public EventHandle(Context context) {
        this.context = context;
    }


    //必须public
    public void onClick(View view) {
        Toast.makeText(context, "测试点击效果", Toast.LENGTH_SHORT).show();
        Log.d("EventHandle", "onClick: hhhhhh");
    }

    //必须public
    public void onBindClick(Person person) {
        Toast.makeText(context, "测试绑定点击效果" + person.getName(), Toast.LENGTH_SHORT).show();
        Log.d("EventHandle", "onClick: hhhhhh");
    }
}
