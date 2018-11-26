package com.duoduo.main.classify.home.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.classify.home.data.ClassifyTopicEntity;

/**
 * 主题数据请求事件
 */
public class ClassifyTopicDataRequestEvent extends BaseEvent<ClassifyTopicEntity, Exception> {
    public static final int EVENT_CLASSIFY_TOPIC_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_TOPIC_DATA_REQUEST_FINISH = 2;
    public static final int EVENT_CLASSIFY_TOPIC_DATA_REQUEST_ERROR = 3;
}
