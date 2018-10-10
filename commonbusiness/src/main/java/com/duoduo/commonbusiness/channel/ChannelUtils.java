package com.duoduo.commonbusiness.channel;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 渠道相关工具类
 */

public class ChannelUtils {

    private static String sRawChannelString;

    private static final String CHANNEL_KEY = "channel";


    /**
     * 从apk中获取版本信息
     *
     * @param context
     * @return
     */
    public static String getChannelFromApk(Context context, String defaultChannel) {
        if (sRawChannelString == null) {
            init(context);
        }

        String[] split = sRawChannelString.split("_");
        String channel = "";
        if (split.length >= 2) {
            channel = split[1];
        }
        if (TextUtils.isEmpty(channel)) {
            channel = defaultChannel;
        }
        return channel;
    }

    private synchronized static void init(Context context) {
        //从apk包中获取
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        //注意这里：默认放在meta-inf/里， 所以需要再拼接一下
        String channelKey = "META-INF/" + CHANNEL_KEY;
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith(channelKey)) {
                    sRawChannelString = entryName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sRawChannelString == null) {
                sRawChannelString = "";
            }
        }
    }
}
