package com.duoduo.main.classify.subclassify.event;

import com.duoduo.commonbase.event.BaseEvent;
import com.duoduo.main.classify.subclassify.data.ClassifySubTabProductDataEntity;

public class ClassifySubTabProductDataReqeustEvent extends BaseEvent<ClassifySubTabProductDataEntity, Exception> {
    public static final int EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_START = 1;
    public static final int EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_SUCCESS = 2;
    public static final int EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_ERROR = 3;
    public static final int EVENT_CLASSIFY_SUB_TAB_PRODUCT_DATA_REQUEST_FINISH = 4;
}
