package com.duoduo.main.classify.event;

import com.duoduo.commonbusiness.event.BaseEvent;

/**
 * 分类Tab请求事件
 */
public class ClassifyTabRequestEvent extends BaseEvent {
    public static final int EVENT_NAME_REQUEST_START = 1;
    public static final int EVENT_NAME_REQUEST_FINISH = 2;
    public static final int EVENT_NAME_REQUEST_ERROR = 3;
}
