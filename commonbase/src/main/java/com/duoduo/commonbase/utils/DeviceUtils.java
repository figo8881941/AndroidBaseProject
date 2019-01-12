package com.duoduo.commonbase.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

/**
 * 设备工具类
 */

public class DeviceUtils {

    public static final int DEVICE_TYPE_OTHER = 1;
    public static final int DEVICE_TYPE_XIAOMI = 2;
    public static final int DEVICE_TYPE_HUAWEI = 3;
    public static final int DEVICE_TYPE_MEIZU = 4;

    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    private static final String KEY_FLYME_ID_FALG_KEY = "ro.build.display.id";
    private static final String KEY_FLYME_ID_FALG_VALUE_KEYWORD = "Flyme";
    private static final String KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme";
    private static final String KEY_FLYME_PUBLISH_FALG = "ro.flyme.published";

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    /**
     * 判断当前手机是否小米,华为,魅族其中一种
     */
    public static int getDeviceType() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) { // 8.0 没有权限，读取不到文件，改用反射
            if (!TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_CODE, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_NAME, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_INTERNAL_STORAGE, ""))) {
                return DEVICE_TYPE_XIAOMI;
            } else if (!TextUtils.isEmpty(getSystemProperty(KEY_EMUI_API_LEVEL, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_VERSION, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, ""))) {
                return DEVICE_TYPE_HUAWEI;
            } else if ((!TextUtils.isEmpty(getSystemProperty(KEY_FLYME_ID_FALG_KEY, "")) && getSystemProperty(KEY_FLYME_ID_FALG_KEY, "").contains(KEY_FLYME_ID_FALG_VALUE_KEYWORD))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_FLYME_ICON_FALG, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_FLYME_SETUP_FALG, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_FLYME_PUBLISH_FALG, ""))) {
                return DEVICE_TYPE_OTHER; //DEVICE_TYPE_MEIZU 接入魅族再改回来
            }
            return DEVICE_TYPE_OTHER;
        } else {
            FileInputStream fis = null;
            Properties prop = null;
            try {
                fis = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                prop = new Properties();
                prop.load(fis);
                if (prop.getProperty(KEY_EMUI_API_LEVEL) != null || prop.getProperty(KEY_EMUI_VERSION) != null || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION) != null) {
                    return DEVICE_TYPE_HUAWEI;
                } else if ((prop.getProperty(KEY_FLYME_ID_FALG_KEY) != null && prop.getProperty(KEY_FLYME_ID_FALG_KEY).contains(KEY_FLYME_ID_FALG_VALUE_KEYWORD)) || prop.getProperty(KEY_FLYME_ICON_FALG) != null || prop.getProperty(KEY_FLYME_SETUP_FALG) != null || prop.getProperty(KEY_FLYME_PUBLISH_FALG) != null) {
                    return DEVICE_TYPE_OTHER;//DEVICE_TYPE_MEIZU 接入魅族再改回来
                } else if (prop.getProperty(KEY_MIUI_VERSION_CODE) != null || prop.getProperty(KEY_MIUI_VERSION_NAME) != null || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE) != null) {
                    return DEVICE_TYPE_XIAOMI;
                } else {
                    return DEVICE_TYPE_OTHER;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return DEVICE_TYPE_OTHER;
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (prop != null) {
                        prop.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取系统属性值的方法
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    /**
     * 获取导航栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int resourceId;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        } else
            return 0;
    }

    /**
     * 判断当前网络是否可以使用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkOK(Context context) {
        boolean result = false;
        if (context != null) {
            try {
                ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                if (cm != null) {
                    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        result = true;
                    }
                }
            } catch (NoSuchFieldError e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String imei = null;
        int state = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (state == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telephonyManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
        }
        return imei;
    }

    /**
     * 获取AndroidID
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String androidId = null;
        if (context != null) {
            androidId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        return androidId;
    }

    /**
     * 获取语言和国家地区的方法 格式: SIM卡方式：cn 系统语言方式：zh-CN
     *
     * @return
     */
    public static String getLanguage(Context context) {

        String ret = null;
        // 根据桌面语言设置请求的语言信息
        Locale locale = Locale.getDefault();
        try {
            TelephonyManager telManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (telManager != null) {
                ret = telManager.getSimCountryIso();
                if (ret != null && !ret.equals("")) {
                    ret = String.format("%s_%s", locale.getLanguage().toLowerCase(),
                            ret.toLowerCase());
                }
            }
        } catch (Throwable ignored) {
        }

        if (ret == null || ret.equals("")) {
            ret = String.format("%s_%s", locale.getLanguage().toLowerCase(), locale.getCountry()
                    .toLowerCase());
        }
        return null == ret ? "error" : ret;
    }

    /**
     * 获取国家
     *
     * @param context
     * @return
     */
    public static String getCountry(Context context) {
        String ret = null;

        try {
            TelephonyManager telManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (telManager != null) {
                ret = telManager.getSimCountryIso().toLowerCase();
            }
        } catch (Throwable e) {
            //			 e.printStackTrace();
        }
        if (ret == null || ret.equals("")) {
            ret = Locale.getDefault().getCountry().toLowerCase();
        }
        return ret;
    }

    /**
     * 获取用户运营商代码
     *
     * @param context
     * @return
     */
    public static String getSimOperator(Context context) {
        String simOperator = "000";
        try {
            if (context != null) {
                // 从系统服务上获取了当前网络的MCC(移动国家号)，进而确定所处的国家和地区
                TelephonyManager manager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                simOperator = manager.getSimOperator();
            }
        } catch (Throwable e) {
            // TODO: handle exception
        }
        return simOperator;
    }

    /**
     * 获取当前网络状态，wifi，GPRS，3G，4G
     *
     * @param context
     * @return
     */
    public static String getNetworkState(Context context) {
        // build Network conditions
        String ret = "";
        try {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo = manager.getActiveNetworkInfo();

            if (networkinfo == null) {
                return ret;
            }

            if (networkinfo.getType() == ConnectivityManager.TYPE_WIFI) {
                ret = "WIFI";
            } else if (networkinfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                int subtype = networkinfo.getSubtype();
                switch (subtype) {
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        // 2G
                        ret = "2G" /*+ "(typeid = " + networkinfo.getType() + "  typename = "
                                    + networkinfo.getTypeName() + "  subtypeid = "
									+ networkinfo.getSubtype() + "  subtypename = "
									+ networkinfo.getSubtypeName() + ")"*/;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        // 3G,4G
                        ret = "3G/4G" /*+ "(typeid = " + networkinfo.getType() + "  typename = "
                                        + networkinfo.getTypeName() + "  subtypeid = "
										+ networkinfo.getSubtype() + "  subtypename = "
										+ networkinfo.getSubtypeName() + ")"*/;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    default:
                        // unknow
                        ret = "UNKNOW" /*+ "(typeid = " + networkinfo.getType() + "  typename = "
                                        + networkinfo.getTypeName() + "  subtypeid = "
										+ networkinfo.getSubtype() + "  subtypename = "
										+ networkinfo.getSubtypeName() + ")"*/;
                        break;
                }
            } else {
                ret = "UNKNOW" /*+ "(typeid = " + networkinfo.getType() + "  typename = "
                                + networkinfo.getTypeName() + ")"*/;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * 检测手机WIFI有没有打开的方法
     *
     * @param context
     * @return
     */
    public static boolean isWifiEnable(Context context) {
        boolean result = false;
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager != null) {
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()
                            && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取设备分辨率
     *
     * @param context
     * @return
     */
    public static String getDisplay(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wMgr.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return width + "*" + height;
    }


    private static int sStatusBarHeight = -1;

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        if (sStatusBarHeight != -1) {
            return sStatusBarHeight;
        }

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sStatusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sStatusBarHeight;
    }

    /**
     * 根据IP地址获取MAC地址
     *
     * @return
     */
    public static String getMacAddressFromIp() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getIPAdress();
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {

        }

        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return
     */
    public static InetAddress getIPAdress() {
        InetAddress ip = null;
        try {
            //列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                NetworkInterface ni = en_netInterface.nextElement();//得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }

                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {

            e.printStackTrace();
        }
        return ip;
    }
}
