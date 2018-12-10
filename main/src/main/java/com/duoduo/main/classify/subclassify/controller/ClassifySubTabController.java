package com.duoduo.main.classify.subclassify.controller;

import android.content.Context;

import com.duoduo.main.classify.subclassify.model.ClassifySubTabNetModel;

public class ClassifySubTabController {

    private ClassifySubTabNetModel netModel;

    private Context context;

    public ClassifySubTabController(Context context) {
        this.context = context.getApplicationContext();
        netModel = new ClassifySubTabNetModel(this.context);
    }



}
