package com.scott.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by scott_he on 2016/11/9.
 */

public class DeviceUtils {

    //获得SDK版本号
    public static String getOsVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    //获得设备Id
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    //获得手机型号
    public static String getModel() {
        return Build.MODEL;
    }

    //获得手机品牌
    public static String getBrand() {
        return Build.BRAND;
    }

    //设备类型
    public static String getDeviceType() {
        return "android";
    }


    //获得uuid
    public static String getUUID(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        final String deviceId = telephonyManager.getDeviceId();
        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String uuid = "";
        if (deviceId != null) {
            uuid = deviceId;
        } else {
            if (!"9774d56d682e549c".equals(androidId)) {
                uuid = androidId;
            } else {
                uuid = UUID.randomUUID().toString();
            }
        }
        return uuid;
    }
}
