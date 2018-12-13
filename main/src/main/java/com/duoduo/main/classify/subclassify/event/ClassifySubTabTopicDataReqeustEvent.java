package com.duoduo.main.classify.subclassify.event;

import com.duoduo.commonbusiness.event.BaseEvent;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabTopicDataEntity;

public class ClassifySubTabTopicDataReqeustEvent extends BaseEvent<ClassifySubTabTopicDataEntity, Exception> {
    public static final int EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_SUCCESS = 2;
    public static final int EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_ERROR = 3;
    public static final int EVENT_CLASSIFY_SUB_TAB_TOPIC_DATA_REQUEST_FINISH = 4;

}
