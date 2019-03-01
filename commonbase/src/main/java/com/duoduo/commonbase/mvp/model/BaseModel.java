package com.duoduo.commonbase.mvp.model;

import android.content.Context;

public abstract class BaseModel implements IBaseModel{

    protected Context context;

    public BaseModel(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void destroy() {
        context = null;
    }
}
