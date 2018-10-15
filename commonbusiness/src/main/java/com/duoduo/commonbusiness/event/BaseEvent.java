package com.duoduo.commonbusiness.event;

import java.util.HashMap;

/**
 * 基础事件类
 */
public abstract class BaseEvent<T, K> {

    /**
     * 事件名称
     */
    protected String eventName;

    protected int arg1;

    protected int arg2;

    protected T arg3;

    protected K arg4;

    protected HashMap<String, Object> args;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public T getArg3() {
        return arg3;
    }

    public void setArg3(T arg3) {
        this.arg3 = arg3;
    }

    public K getArg4() {
        return arg4;
    }

    public void setArg4(K arg4) {
        this.arg4 = arg4;
    }

    public HashMap<String, Object> getArgs() {
        return args;
    }

    public void setArgs(HashMap<String, Object> args) {
        this.args = args;
    }
}
