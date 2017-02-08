package com.scott.lib.db;

import android.content.Context;
import android.util.Log;

import com.scott.lib.callback.DbCallback;
import com.scott.lib.constant.Constants;
import com.scott.util.AppUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * author: heshantao
 * data: 2017/1/18.
 * 数据库操作
 */

public class DbHelper<T extends RealmObject> {
    private final String TAG = this.getClass().getSimpleName();
    Realm mRealm;
    RealmAsyncTask mRealmAsyncTask;
    RealmResults<T> results;

    //Realm 配置
    public RealmConfiguration getRealmConfiguration(Context cxt) {
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .name(Constants.DB_NAME)
                .encryptionKey(new byte[64])
                .deleteRealmIfMigrationNeeded()
                .migration(new CustomerMigration())
                .schemaVersion(AppUtils.getVersionCode(cxt))
                .build();
        return configuration;
    }


    /**
     * 添加指定类到数据库Object以主键区别是否为同一个，存在相同就更新，不存在就添加
     *
     * @param t
     */
    public void insertOrUpdateRealmObject(final T t) {
        getRealm();
        if (mRealm == null) return;
        mRealmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: 回调成功");
                //成功回调
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                //错误回调
            }
        });

    }


    /**
     * 批量插入数据 Object以主键区别是否为同一个，存在相同就更新，不存在就添加
     *
     * @param objects
     */
    public void insertOrUpdateRealmObjects(final List<T> objects) {
        getRealm();
        if (mRealm == null) return;
        mRealmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(objects);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //成功回调
                Log.d(TAG, "onSuccess: 插入成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: 插入失败");
                //错误回调
            }
        });
    }

    /**
     * 添加指定类到数据库(不存在主键)
     *
     * @param t
     */
    public void insertRealmObject(final T t) {
        getRealm();
        if (mRealm == null) return;
        mRealmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(t);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //成功回调
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                //错误回调
            }
        });

    }


    /**
     * 批量插入数据(不存在主键)
     *
     * @param objects
     */
    public void insertRealmObjects(final List<T> objects) {
        getRealm();
        if (mRealm == null) return;
        mRealmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(objects);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //成功回调
                Log.d(TAG, "insertRealmObjects onSuccess: 插入成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                //错误回调
                Log.d(TAG, "insertRealmObjects onError: 插入失败");
            }
        });
    }


    /**
     * 查询指定类的全部存储信息
     *
     * @param t
     * @return
     */
    public void queryRealmObjects(Class<T> t, final DbCallback callback) {
        getRealm();
        if (mRealm == null) return;

        results = mRealm.where(t).findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<T>>() {
            @Override
            public void onChange(RealmResults<T> element) {
                List<T> list = mRealm.copyFromRealm(element);
                callback.findAll(list);
                Log.d(TAG, "queryRealmObjects 查询个数" + list.size());
            }
        });

    }


    /**
     * 查询指定类的把结果与RxJava结合
     *
     * @param t
     * @return
     */
    public Observable rxQueryRealmObjects(Class<T> t) {
        getRealm();
        if (mRealm == null) return null;
        return mRealm.where(t).findAllAsync().asObservable()
                .filter(new Func1<RealmResults<T>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<T> ts) {
                        Log.d(TAG, "call: 是否加载完");
                        return ts.isLoaded();
                    }
                }).flatMap(new Func1<RealmResults<T>, Observable<RealmResults<T>>>() {
                    @Override
                    public Observable<RealmResults<T>> call(RealmResults<T> ts) {
                        return Observable.just(ts);
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 根据特定参数查询
     *
     * @param t     类名
     * @param name
     * @param value
     * @return
     */
    public void queryRealmObjectByField(Class<T> t, String name, String value, final DbCallback callback) {
        getRealm();
        if (mRealm == null) return;

        results = mRealm.where(t).equalTo(name, value).findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<T>>() {
            @Override
            public void onChange(RealmResults<T> element) {
                List<T> list = mRealm.copyFromRealm(element);
                callback.findAll(list);

            }
        });

    }


    /**
     * Rx根据特定参数查询
     *
     * @param t     类名
     * @param name
     * @param value
     * @return
     */
    public Observable rxQueryRealmObjectByField(Class<T> t, String name, String value, final DbCallback callback) {
        getRealm();
        if (mRealm == null) return null;
        return mRealm.where(t).equalTo(name, value).findAllAsync().asObservable()
                .filter(new Func1<RealmResults<T>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<T> ts) {
                        Log.d(TAG, "call: 是否加载完");
                        return ts.isLoaded();
                    }
                }).flatMap(new Func1<RealmResults<T>, Observable<RealmResults<T>>>() {
                    @Override
                    public Observable<RealmResults<T>> call(RealmResults<T> ts) {
                        return Observable.just(ts);
                    }
                }).observeOn(AndroidSchedulers.mainThread());

    }


    /**
     * 分页查找
     *
     * @param t        类名
     * @param page     第几页
     * @param pageSize 每页的数量
     * @return
     */
    public void queryRealmObjectPaginate(Class<T> t, final int page, final int pageSize, final DbCallback callback) {
        getRealm();
        if (mRealm == null) return;
        results = mRealm.where(t).findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<T>>() {
            @Override
            public void onChange(RealmResults<T> element) {
                List<T> list = mRealm.copyFromRealm(element);
                list = list.subList(Math.max((page - 1) * pageSize, 0), Math.min(page * pageSize, list.size()));
                callback.findAll(list);

            }
        });
    }


    /**
     * Rx分页查找
     *
     * @param t        类名
     * @param page     第几页
     * @param pageSize 每页的数量
     * @return
     */
    public Observable rxQueryRealmObjectPaginate(Class<T> t, final int page, final int pageSize) {
        getRealm();
        if (mRealm == null) return null;
        return mRealm.where(t).findAllAsync().asObservable()
                .filter(new Func1<RealmResults<T>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<T> ts) {
                        Log.d(TAG, "call: 是否加载完");
                        return ts.isLoaded();
                    }
                }).flatMap(new Func1<RealmResults<T>, Observable<List<T>>>() {
                    @Override
                    public Observable<List<T>> call(RealmResults<T> ts) {
                        List<T> list = ts.subList(Math.max((page - 1) * pageSize, 0), Math.min(page * pageSize, ts.size()));
                        return Observable.just(list);
                    }
                }).observeOn(AndroidSchedulers.mainThread());

    }


    /**
     * 删除指定类的全部数据库信息
     */
    public void deleteRealmObjects(final Class<T> t) {
        getRealm();
        if (mRealm == null) return;
        mRealmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(t);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "deleteRealmObjects onSuccess: ");

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "deleteRealmObjects onError: ");
            }
        });
    }


    public Realm getRealm() {
        if (mRealm == null) {
            mRealm = Realm.getDefaultInstance();
        }
        return mRealm;
    }

    //获得异步任务
    public RealmAsyncTask getRealmAsyncTask() {
        return mRealmAsyncTask;
    }

    //realm 关闭
    public void closeRealm() {
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    //取消异步任务(Activity/Fragment 销毁时候)
    public void closeRealmAsyncTask() {
        if (mRealmAsyncTask != null && !mRealmAsyncTask.isCancelled()) {
            mRealmAsyncTask.cancel();
        }
    }


    //取消监听(Activity/Fragment 销毁时候,每次查询完成后)
    public void removeChangeListener() {
        if (results != null) {
            results.removeChangeListeners();
        }
    }

}
