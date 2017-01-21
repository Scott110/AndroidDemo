package com.scott.lib.db;

import android.content.Context;

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

/**
 * author: heshantao
 * data: 2017/1/18.
 * 数据库操作
 */

public class DbHelper {
    private final String TAG = this.getClass().getSimpleName();
    private volatile static DbHelper INSTANCE;
    Realm mRealm;
    RealmAsyncTask mRealmAsyncTask;

    //构造方法私有
    private DbHelper() {
        mRealm = Realm.getDefaultInstance();
    }

    //获取单例
    public static DbHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (DbHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DbHelper();
                }
            }
        }
        return INSTANCE;
    }


    //Realm 配置
    public RealmConfiguration getRealmConfiguration(Context cxt) {
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .name(Constants.DB_NAME)
                .encryptionKey(new byte[64])
                .migration(new CustomerMigration())
                .schemaVersion(AppUtils.getVersionCode(cxt))
                .build();
        return configuration;
    }


    /**
     * 添加指定类到数据库Object中存在主键
     *
     * @param object
     */
    public void insertRealmObjectWithPrivateKey(final RealmObject object) {
        Realm realm = getRealm();
        if (realm == null) return;
        mRealmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(object);
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
     * 批量插入数据 Object中存在主键
     *
     * @param objects
     */
    public void insertRealmObjectsWithPrivateKey(final List<? extends RealmObject> objects) {
        Realm realm = getRealm();
        if (realm == null) return;
        mRealmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(objects);
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
     * 添加指定类到数据库
     *
     * @param object
     */
    public void insertRealmObject(final RealmObject object) {
        Realm realm = getRealm();
        if (realm == null) return;
        mRealmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(object);
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
     * 批量插入数据
     *
     * @param objects
     */
    public void insertRealmObjects(final List<? extends RealmObject> objects) {
        Realm realm = getRealm();
        if (realm == null) return;
        mRealmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(objects);
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
     * 查询指定类的全部存储信息
     *
     * @param clazz
     * @return
     */
    public void queryRealmObjects(final Class<? extends RealmObject> clazz, final DbCallback callback) {
        Realm realm = getRealm();
        if (realm == null) return;

        mRealmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<? extends RealmObject> realmResults = realm.where(clazz).findAllAsync();
                if (realmResults.isLoaded()) {
                    List<? extends RealmObject> arrayList = realm.copyFromRealm(realmResults);
                    callback.findAll(arrayList);
                }
            }
        });
    }


    /**
     * 删除指定类的全部数据库信息
     */
    public void deleteRealmObjects(final Class<? extends RealmObject> clazz) {
        Realm realm = getRealm();
        if (realm == null) return;

        mRealmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(clazz);
            }
        });
    }


    public Realm getRealm() {
        return mRealm;
    }

    public RealmAsyncTask getRealmAsyncTask() {
        return mRealmAsyncTask;
    }

    public void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }


}
