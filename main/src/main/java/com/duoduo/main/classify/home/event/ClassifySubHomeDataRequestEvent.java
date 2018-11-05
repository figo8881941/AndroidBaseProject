package com.duoduo.main.classify.home.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.classify.data.ClassifySubHomeDataBean;

/**
 * 分类首页数据请求事件
 */
public class ClassifySubHomeDataRequestEvent extends BaseEvent<ClassifySubHomeDataBean, Exception> {
    public static final int EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_FINISH = 2;
    public static final int EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR = 3;
}
