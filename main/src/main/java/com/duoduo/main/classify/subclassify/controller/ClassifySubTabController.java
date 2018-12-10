package com.duoduo.main.classify.subclassify.controller;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duoduo.main.classify.subclassify.model.ClassifySubTabNetModel;

import org.json.JSONObject;

public class ClassifySubTabController {

    private ClassifySubTabNetModel netModel;

    private Context context;

    public ClassifySubTabController(Context context) {
        this.context = context.getApplicationContext();
        netModel = new ClassifySubTabNetModel(this.context);
    }

    /**
     * 请求子分类数据的方法
     *
     * @param categoryId
     */
    public void requestSubTabData(int categoryId) {
        try {
            netModel.requestSubTabData(categoryId, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
