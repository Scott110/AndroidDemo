package com.scott.demo.simple.db;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.scott.lib.db.DbHelper;

import java.io.File;

import io.realm.Realm;

/**
 * author: heshantao
 * data: 2017/2/6.
 */

public class DbFileUtil {

    //将realm 数据库拷贝出来用邮件当附件发送出来这样不需要adb 命令方便 Realm brower 查看
    public static void exportDatabase(Context context) {

        // init realm
        Realm realm = new DbHelper().getRealm();

        File exportRealmFile = null;
        try {
            // get or create an "export.realm" file
            exportRealmFile = new File(context.getExternalCacheDir(), "scott.realm");

            // if "export.realm" already exists, delete
            exportRealmFile.delete();

            // copy current realm to "export.realm"
            realm.writeCopyTo(exportRealmFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        new DbHelper().closeRealm();

        // init email intent and add export.realm as attachment
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "heshtjs@163.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "数据库导出文件");
        intent.putExtra(Intent.EXTRA_TEXT, "Realm 文件");
        Uri u = Uri.fromFile(exportRealmFile);
        intent.putExtra(Intent.EXTRA_STREAM, u);

        // start email intent
        context.startActivity(Intent.createChooser(intent, "选择文件"));
    }
}
