package com.duoduo.commonbase.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 应用相关的工具类
 */
public class AppUtils {

    /**
     * 判断应用是否安装的方法
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static boolean isAppInstall(Context context, String pkgName) {
        if (context == null || TextUtils.isEmpty(pkgName)) {
            return false;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager()
                    .getPackageInfo(pkgName, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return true;
        }
        return false;
    }

    /**
     * 启动其他程序
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean launchApp(Context context, String packageName) {
        boolean ret = false;
        try {
            PackageManager packageManager = context
                    .getPackageManager();
            Intent startIntent = packageManager
                    .getLaunchIntentForPackage(packageName);
            context.startActivity(startIntent);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 安装APK
     *
     * @param filePath
     * @param context
     */
    public static void installApk(String filePath, Context context) {
        Intent installIntent = new Intent();
        installIntent
                .setAction(Intent.ACTION_VIEW);
        installIntent.setDataAndType(
                Uri.parse("file://" + filePath),
                "application/vnd.android.package-archive");
        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivitySafely(context, installIntent);
    }


    /**
     * 安全启动activity的方法
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean startActivitySafely(Context context, Intent intent) {
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取应用程序版本号的方法
     *
     * @param context
     * @param pkg
     * @return
     */
    public static int getAppVersionCode(Context context, String pkg) {
        int versionCode = 0;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(pkg, 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取应用程序版本名称的方法
     *
     * @param context
     * @param pkg
     * @return
     */
    public static String getAppVersionName(Context context, String pkg) {
        String versionName = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(pkg, 0);
            versionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取当前进程名称的方法
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 获取应用图标的方法
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static Drawable getAppIcon(Context context, String pkgName) {
        if (context == null || TextUtils.isEmpty(pkgName)) {
            return null;
        }

        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(pkgName, 0);
            return applicationInfo.loadIcon(packageManager);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取应用名字的方法
     * @param context
     * @param packName
     * @return
     */
    public static String getAppName(Context context, String packName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo info = packageManager.getApplicationInfo(packName, 0);
            return info.loadLabel(packageManager).toString();
        } catch (Exception e) {
        }

        return null;
    }
}
