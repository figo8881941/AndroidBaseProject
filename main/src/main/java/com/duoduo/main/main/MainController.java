package com.duoduo.main.main;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * 主界面Controller
 */
public class MainController {

    private Context context;

    private MainNetModel mainNetModel;

    public MainController(Context context) {
        context = context.getApplicationContext();
        mainNetModel = new MainNetModel(context);
    }

    public void requestTabData() {
        mainNetModel.requestTabData(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

}