package com.duoduo.commonbusiness.net;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.duoduo.commonbase.utils.ThreadUtils;
import com.duoduo.commonbusiness.R;

/**
 * 网络错误的处理类
 *
 * @author wangzhuobin
 */
public class CommonNetErrorHandler {


    /**
     * 网络错误通用的处理方法
     *
     * @param context
     * @param object
     * @return
     */
    public static boolean handleNetError(Context context, Object object) {
        Exception exception = object != null && object instanceof Exception ? (Exception) object : null;
        return handleNetError(context, exception, null);
    }

    /**
     * 网络错误通用的处理方法
     *
     * @param context
     * @param exception
     * @return
     */
    public static boolean handleNetError(Context context, Exception exception) {
        return handleNetError(context, exception, null);
    }

    /**
     * 网络错误通用的处理方法
     *
     * @param context
     * @param exception
     * @param defaultMessage
     * @return
     */
    public static boolean handleNetError(final Context context,
                                         Exception exception, String defaultMessage) {
        boolean handle = false;
        if (context == null) {
            return handle;
        }
        String message = defaultMessage;
        if (exception != null && exception instanceof VolleyError) {
            if (exception instanceof CommonServerError) {
                CommonServerError commonServerError = (CommonServerError) exception;
                message = commonServerError.getMessage();
            } else if (exception instanceof TimeoutError) {
                message = context
                        .getString(R.string.common_business_network_error_timeout_tips);
            } else if (exception instanceof ServerError) {
                message = context
                        .getString(R.string.common_business_network_error_server_error_tips);
            } else if (exception instanceof ParseError) {
                message = context
                        .getString(R.string.common_business_network_error_parse_error_tips);
            } else if (exception instanceof NetworkError) {
                message = context
                        .getString(R.string.common_business_net_work_error);
            } else if (exception instanceof AuthFailureError) {
                message = context
                        .getString(R.string.common_business_net_work_error);
            } else {
                message = context
                        .getString(R.string.common_business_net_work_error);
            }
            handle = true;
        } else {
            handle = false;
        }
        if (message == null || TextUtils.isEmpty(message.trim())) {
            message = context
                    .getString(R.string.common_business_net_work_error);
        }
        final String finalMessage = message;
        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
                if (context != null) {
                    Toast.makeText(context, finalMessage,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return handle;
    }
}
