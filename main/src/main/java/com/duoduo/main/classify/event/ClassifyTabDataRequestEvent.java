package com.duoduo.main.classify.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.classify.data.ClassifyTabDataBean;

/**
 * 分类Tab数据请求事件
 */
public class ClassifyTabDataRequestEvent extends BaseEvent<ClassifyTabDataBean, Exception> {
    public static final int EVENT_CLASSIFY_TAB_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_TAB_DATA_REQUEST_FINISH = 2;
    public static final int EVENT_CLASSIFY_TAB_DATA_REQUEST_ERROR = 3;
}