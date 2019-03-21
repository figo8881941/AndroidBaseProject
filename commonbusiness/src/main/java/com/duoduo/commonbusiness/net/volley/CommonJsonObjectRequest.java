package com.duoduo.commonbusiness.net.volley;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.duoduo.commonbase.utils.IOUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 通用的服务器Json请求
 */
public class CommonJsonObjectRequest extends JsonObjectRequest {

    /*
     * status=1:处理成功；
     */
    public static final int STATUS_SUCCESS = 1;

    public CommonJsonObjectRequest(int method, String url,
                                   JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        init();
    }

    public CommonJsonObjectRequest(String url, JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
        init();
    }

    private void init() {
        setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, 0));
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            //这里可以对返回的数据进行处理，比如说解压、解密
            String jsonString = IOUtils.getUnZipString(response.data);
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        try {
            //这里对成功和失败增加业务上的判断
            JSONObject result = response.getJSONObject("result");
            int status = result.optInt("status");
            if (status == STATUS_SUCCESS) {
                super.deliverResponse(response);
            } else {
                int errorCode = result.optInt("errorcode");
                String msg = result.optString("msg");
                CommonVolleyServerError commonVolleyServerError = new CommonVolleyServerError(msg);
                commonVolleyServerError.setStatus(status);
                commonVolleyServerError.setErrorCode(errorCode);
                super.deliverError(commonVolleyServerError);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            super.deliverError(new ParseError(e));
        }
    }

}

