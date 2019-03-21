package com.duoduo.commonbusiness.net.okhttp;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 通用回调处理
 */
public class CommonOKHttpCallback implements Callback {

    /*
     * status=1:处理成功；
     */
    public static final int STATUS_SUCCESS = 1;

    private Callback callback;

    public CommonOKHttpCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if (callback == null) {
            return;
        }
        callback.onFailure(call, e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (callback == null) {
            return;
        }
        try {
            String responseString = response.body().string();
            JSONObject responseJSONObject = new JSONObject(responseString);
            //这里对成功和失败增加业务上的判断
            JSONObject result = responseJSONObject.getJSONObject("result");
            int status = result.optInt("status");
            if (status == STATUS_SUCCESS) {
                Response newResponse = response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(), responseString))
                        .build();
                callback.onResponse(call, newResponse);
            } else {
                int errorCode = result.optInt("errorcode");
                String msg = result.optString("msg");
                CommonOKHttpException commonOKHttpException = new CommonOKHttpException(msg);
                commonOKHttpException.setStatus(status);
                commonOKHttpException.setErrorCode(errorCode);
                callback.onFailure(call, commonOKHttpException);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(call, new IOException("The response data is not the json data"));
        }
    }
}
