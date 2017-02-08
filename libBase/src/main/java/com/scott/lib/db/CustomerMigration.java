package com.scott.lib.db;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * author: heshantao
 * data: 2017/1/18.
 * 数据库迁移类
 */

public class CustomerMigration implements RealmMigration {
    private static final String TAG = CustomerMigration.class.getSimpleName();

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        Log.d(TAG, "migrate: " + oldVersion + "____新版本号____" + newVersion);
    }
}
