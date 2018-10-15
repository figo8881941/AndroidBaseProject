package com.duoduo.main.main.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.main.data.MainTabDataBean;

/**
 * 首页Tab请求事件
 */
public class MainTabRequestEvent extends BaseEvent<MainTabDataBean, Exception> {
    public static String EVENT_NAME_REQUEST_START = "EVENT_NAME_REQUEST_START";
    public static String EVENT_NAME_REQUEST_FINISH = "EVENT_NAME_REQUEST_FINISH";
    public static String EVENT_NAME_REQUEST_ERROR = "EVENT_NAME_REQUEST_ERROR";
}
