package com.duoduo.commonbusiness.net;

import android.content.Context;
import android.os.Build;

import com.duoduo.commonbase.utils.AppUtils;
import com.duoduo.commonbase.utils.DeviceUtils;
import com.duoduo.commonbase.utils.TimeUtils;
import com.duoduo.commonbusiness.channel.ChannelUtils;
import com.duoduo.commonbusiness.config.GlobalBuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络请求数据工具类
 */
public class NetDataUtils {

    /**
     * 功能简述:获取URL地址
     * 根据GlobalBuildConfig的值
     *
     * @return
     */
    public static String getUrlWithGlobalBuildConfig(int funId, String servername) {
        GlobalBuildConfig config = GlobalBuildConfig.getInstance();
        return getUrl(funId, servername, getHostFromGlobalBuildConfig());
    }

    /**
     * 功能简述:获取URL地址
     * 功能详细描述:funid=x&rd=1234;其中rd是系统时间毫秒数，防止网关cache.
     *
     * @return
     */
    public static String getUrl(int funId, String servername, String host) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(host);
        buffer.append(servername);
        buffer.append("/common?");
        buffer.append("funid=");
        buffer.append(funId);
        buffer.append("&shandle=");
        buffer.append(0);
        buffer.append("&handle=");
        buffer.append(0);
        buffer.append("&rd=");
        buffer.append(System.currentTimeMillis());
        return buffer.toString();
    }

    /**
     * 根据GlobalBuildConfig的配置
     * 获取host值
     *
     * @return
     */
    public static String getHostFromGlobalBuildConfig() {
        GlobalBuildConfig config = GlobalBuildConfig.getInstance();
        String host = config.getNormalServerHost();
        if (config.isDebugMode()) {
            host = config.getTestServerHost();
        }
        return host;
    }

    /**
     * 获取phead的json结构
     * 根据GlobalBuildConfig的值
     *
     * @param context
     * @return
     */
    public static JSONObject getPheadJsonWithGlobalBuildConfig(Context context) {
        GlobalBuildConfig config = GlobalBuildConfig.getInstance();
        return getPheadJson(context, config.getPVersion(), config.getDefaultChannel(), config.getPrdid());
    }

    /**
     * 获取phead的json结构
     *
     * @param context
     * @param pversion
     * @param defaultChannel
     * @param prdid
     * @return
     */
    public static JSONObject getPheadJson(Context context, int pversion, int defaultChannel, String prdid) {
        JSONObject pheadJson = new JSONObject();
        if (context != null) {
            try {
                pheadJson.put("pversion", pversion); // 协议版本
                pheadJson.put("phoneid", DeviceUtils.getAndroidId(context)); // androidid
                pheadJson.put("phone", Build.MODEL);
                pheadJson.put("imei", DeviceUtils.getIMEI(context)); // imei
                pheadJson.put("cversion", AppUtils.getAppVersionCode(context, context.getPackageName()));
                pheadJson.put("cversionname", AppUtils.getAppVersionName(context, context.getPackageName())); // 版本号
                pheadJson.put("channel", ChannelUtils.getChannelFromApk(context, defaultChannel)); // 当前渠道号
                pheadJson.put("lang", DeviceUtils.getLanguage(context)); // 所在国家的语言
                pheadJson.put("sdk", Build.VERSION.SDK_INT);  // 系统sdk版本
                pheadJson.put("imsi", DeviceUtils.getSimOperator(context)); // 运营商编号
                pheadJson.put("sys", Build.VERSION.RELEASE); // 系统版本
                pheadJson.put("lng", -1); // 经度
                pheadJson.put("lat", -1); // 纬度
                pheadJson.put("cityid", -1); // 城市编码，业务id
                pheadJson.put("gcityid", -1); // 真实的坐标
                pheadJson.put("platform", "android"); // 平台：android，ios，后台
                pheadJson.put("prdid", prdid); // 产品id
                pheadJson.put("time_zone", TimeUtils.getCurrentTimeZone());
                pheadJson.put("timezoneid", TimeUtils.getCurrentTimeZoneID());
                pheadJson.put("dpi", DeviceUtils.getDisplay(context));

                pheadJson.put("access_token", null);
                pheadJson.put("net", DeviceUtils.getNetworkState(context));
                pheadJson.put("mac", DeviceUtils.getMacAddressFromIp());
                pheadJson.put("vendor", Build.MANUFACTURER);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return pheadJson;
    }

    /**
     * 包装完整请求数据结构
     *
     * @param data
     * @return
     */
    public static JSONObject getParamJsonObject(JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("handle", 0);
            jsonObject.put("shandle", 0);
            jsonObject.put("data", data);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject = data;
        }
        return jsonObject;
    }

    /**
     * 获取data结构
     * 根据GlobalBuildConfig的值
     *
     * @param context
     * @return
     */
    public static JSONObject getPostDataWithPheadFromGlobalBuildConfig(Context context) {
        GlobalBuildConfig config = GlobalBuildConfig.getInstance();
        return getPostDataWithPhead(context, config.getPVersion(), config.getDefaultChannel(), config.getPrdid());
    }


    /**
     * 获取data结构
     *
     * @param context
     * @param pversion
     * @param defaultChannel
     * @param prdid
     * @return
     */
    public static JSONObject getPostDataWithPhead(Context context, int pversion, int defaultChannel, String prdid) {
        JSONObject data = new JSONObject();
        try {
            data.put("phead", getPheadJson(context, pversion, defaultChannel, prdid));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }


}
