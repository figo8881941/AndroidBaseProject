package com.duoduo.main.main.event;

import com.duoduo.commonbase.event.BaseEvent;
import com.duoduo.main.main.data.MainTabEntity;

/**
 * 首页Tab请求事件
 */
public class MainTabRequestEvent extends BaseEvent<MainTabEntity, Exception> {
    public static final int EVENT_NAME_REQUEST_START = 1;
    public static final int EVENT_NAME_REQUEST_SUCCESS = 2;
    public static final int EVENT_NAME_REQUEST_ERROR = 3;
    public static final int EVENT_NAME_REQUEST_FINISH = 4;

}
