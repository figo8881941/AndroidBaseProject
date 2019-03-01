package com.duoduo.main.classify.home.event;

import com.duoduo.commonbase.event.BaseEvent;
import com.duoduo.main.classify.home.data.ClassifySubHomeEntity;

/**
 * 分类首页数据请求事件
 */
public class ClassifySubHomeDataRequestEvent extends BaseEvent<ClassifySubHomeEntity, Exception> {
    public static final int EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_SUCCESS = 2;
    public static final int EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_ERROR = 3;
    public static final int EVENT_CLASSIFY_SUB_HOME_DATA_REQUEST_FINISH = 4;

}
