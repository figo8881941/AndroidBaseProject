package com.duoduo.commonbase.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * IO工具类
 */
public class IOUtils {

    /**
     * 解压unzip获取String的方法
     *
     * @param data
     * @return
     */
    public static String getUnZipString(byte[] data) {
        byte[] h = new byte[2];
        h[0] = (data)[0];
        h[1] = (data)[1];
        int head = (int) ((h[0] << 8) | h[1] & 0xFF);
        boolean t = head == 0x1f8b;
        InputStream in = null;
        BufferedReader r = null;
        StringBuilder sb = new StringBuilder();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            if (t) {
                in = new GZIPInputStream(bis);
            } else {
                in = bis;
            }
            r = new BufferedReader(new InputStreamReader(in,
                    "utf-8"), 1000);
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSafely(in);
        }
        return sb.toString();
    }

    private static void closeSafely(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
