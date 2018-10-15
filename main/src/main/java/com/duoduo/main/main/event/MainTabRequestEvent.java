package com.duoduo.main.main.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.main.data.MainTabDataBean;

/**
 * 首页Tab请求事件
 */
public class MainTabRequestEvent extends BaseEvent<MainTabDataBean, Exception> {
    public static final int EVENT_NAME_REQUEST_START = 1;
    public static final int EVENT_NAME_REQUEST_FINISH = 2;
    public static final int EVENT_NAME_REQUEST_ERROR = 3;
}
