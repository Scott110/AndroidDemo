package com.scott.lib.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by scott_he on 16/10/17.
 */

public class CacheUtils {

    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    public  static File getRxCacheFile(Context context){
        String path=getDiskCacheDir(context)+File.separator+"RxCache";
        File file=new File(path);
        return file;
    }

    public  static File getOkHttpFile(Context context){
        String path=getDiskCacheDir(context)+File.separator+"okhttp";
        File file=new File(path);
        return file;
    }
}
