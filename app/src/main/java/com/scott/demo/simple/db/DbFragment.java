package com.scott.demo.simple.db;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scott.demo.R;
import com.scott.demo.dbtest;
import com.scott.demo.di.InjectHelper;
import com.scott.lib.callback.DbCallback;
import com.scott.lib.db.DbHelper;
import com.scott.lib.db.Repository;
import com.scott.lib.ui.BaseFragment;
import com.scott.libhttp.callback.HttpOnNextCallback;
import com.scott.libhttp.manager.HttpManager;
import com.scott.libstyle.DbindingEventCallback;
import com.scott.libstyle.EventHandler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author: heshantao
 * data: 2017/2/6.
 */

public class DbFragment extends BaseFragment implements DbindingEventCallback, DbCallback<BookBean> {
    private static final String TAG = "DbFragment";
    dbtest binding;
    TextView bookNameTxt;
    BookBean bookBean;
    @Inject
    BookApi api;
    @Inject
    HttpManager manager;
    @Inject
    Repository repository;

    public static DbFragment newInstance() {
        return new DbFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_db;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        InjectHelper.getFragmentComponent(_mActivity, this).inject(this);
        binding = DataBindingUtil.setContentView(_mActivity, getLayoutId());
        binding.setEventHandler(new EventHandler(_mActivity, this));
        bookNameTxt = binding.bookNameTxt;
        //bookBean = new BookBean();
        //bookBean.addChangeListener(RealmDataBindCallback.FACTORY.create());
        //bookBean.set_id(12);
        //bookBean.setBookName("----海明威-----");
        //binding.setBook(bookBean);

    }


    private void addPen() {
        /*
        增加一条
        PenBean penBean = new PenBean();
        penBean.setName("钢笔");
        penBean.setPrice(32.90);
        Toast.makeText(_mActivity, "增加了一条钢笔数据进入数据库", Toast.LENGTH_SHORT).show();
        DbHelper.getInstance().insertOrUpdateRealmObject(penBean);*/
        List<PenBean> pens = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PenBean penBean = new PenBean();
            penBean.setId(i);
            penBean.setName("钢笔**" + i);
            penBean.setPrice(0.7 * (i + 1));
            pens.add(penBean);
        }
        Toast.makeText(_mActivity, "增加了一批钢笔数据进入数据库", Toast.LENGTH_SHORT).show();
        new DbHelper().insertOrUpdateRealmObjects(pens);


    }


    private void delectAllPen() {
        new DbHelper().deleteRealmObjects(PenBean.class);
    }


    private void findPen() {

    }

    private void findAllPen() {
        new DbHelper().queryRealmObjects(PenBean.class, this);
    }


    private void paginateFindPen() {
        new DbHelper().queryRealmObjectPaginate(PenBean.class, 1, 15, this);
    }


    private void updatePen() {

    }


    private void insertBook() {
        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            BookBean bookBean = new BookBean();
            bookBean.set_id(i);
            bookBean.setBookName("----海明威-----" + i);
            list.add(bookBean);
        }

        new DbHelper().insertOrUpdateRealmObjects(list);
    }

    private void findBook() {
        //DbHelper.getInstance().queryRealmObjects(BookBean.class, this);
        //bookBean = new BookBean();
        //Log.d(TAG, "findBook: 是否可用" + bookBean.isManaged());
       /* new DbHelper().rxQueryRealmObjects(BookBean.class).subscribe(new Subscriber<BookBean>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(BookBean book) {
                Log.d(TAG, "onNext: ");

            }
        });*/

        new DbHelper().rxQueryRealmObjectPaginate(BookBean.class, 3, 20).subscribe(new Subscriber<List<BookBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<BookBean> list) {
                Log.d(TAG, "onNext: " + list);
            }
        });
    }


    private void updateBookName(BookBean bean) {
        bookBean = bean;
        binding.setBook(bookBean);
        /*bookBean.addChangeListener(new RealmChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel element) {

            }
        });*/
        //bookBean.set_id(bean.get_id());
        //bookBean.setBookName(bean.getBookName());
        //bookBean.addChangeListener(RealmDataBindCallback.FACTORY.create());
        //binding.setBook(bookBean);

    }


    public void requestBookApi() {
        api.setId("24");
        api.setCallback(new HttpOnNextCallback<List<BookBean>>() {
            @Override
            public void onNext(List<BookBean> bookBeen) {
                Log.d(TAG, "onNext: 调用成功");

            }
        });
        //manager.doHttpDeal(api);
        repository.requestData(BookBean.class, api);
    }


    @Override
    public void onViewClick(View view, Object o) {

    }


    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                addPen();
                //Toast.makeText(_mActivity, "点击了增加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delect_btn:
                delectAllPen();
                //Toast.makeText(_mActivity, "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.find_btn:
                //Toast.makeText(_mActivity, "点击了查找", Toast.LENGTH_SHORT).show();
                findAllPen();
                break;
            case R.id.paginate_find_btn:
                paginateFindPen();
                break;
            case R.id.update_btn:
                Toast.makeText(_mActivity, "点击了修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkout_btn:
                DbFileUtil.exportDatabase(_mActivity);
                break;
            case R.id.insert_book_btn:
                insertBook();
                break;
            case R.id.find_book_btn:
                findBook();
                break;
            case R.id.change_book_btn:
                bookBean.setBookName("我很好，很骄傲");
                break;
            case R.id.request_book_btn:
                requestBookApi();
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewLongClick(View view, Object o) {

    }

    @Override
    public void onViewLongClick(View view) {

    }

    @Override
    public void findAll(List<BookBean> list) {
        //Toast.makeText(_mActivity, "查找完成" + list.size(), Toast.LENGTH_SHORT).show();
        updateBookName(list.get(0));
        //DbHelper.getInstance().removeChangeListener();
    }
}
