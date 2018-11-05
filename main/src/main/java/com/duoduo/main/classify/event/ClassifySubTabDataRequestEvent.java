package com.duoduo.main.classify.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.classify.data.ClassifySubTabDataBean;

/**
 * 分类Tab数据请求事件
 */
public class ClassifySubTabDataRequestEvent extends BaseEvent<ClassifySubTabDataBean, Exception> {
    public static final int EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_FINISH = 2;
    public static final int EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_ERROR = 3;
}
