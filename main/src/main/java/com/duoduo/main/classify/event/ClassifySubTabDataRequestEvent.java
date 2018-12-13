package com.duoduo.main.classify.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.classify.data.ClassifySubTabEntity;

/**
 * 分类Tab数据请求事件
 */
public class ClassifySubTabDataRequestEvent extends BaseEvent<ClassifySubTabEntity, Exception> {
    public static final int EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_SUCCESS = 2;
    public static final int EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_ERROR = 3;
    public static final int EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_FINISH = 4;

}
