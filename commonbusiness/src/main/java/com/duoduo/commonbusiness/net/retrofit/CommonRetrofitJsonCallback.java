package com.duoduo.commonbusiness.net.retrofit;

import com.duoduo.commonbusiness.net.common.IRequestCallback;
import com.duoduo.commonbusiness.net.okhttp.CommonOKHttpException;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonRetrofitJsonCallback implements Callback<ResponseBody> {

    /*
     * status=1:处理成功；
     */
    public static final int STATUS_SUCCESS = 1;

    private IRequestCallback<JSONObject> callback;

    public CommonRetrofitJsonCallback(IRequestCallback<JSONObject> callback) {
        this.callback = callback;
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
//                Response newResponse = response.newBuilder()
//                        .body(ResponseBody.create(response.body().contentType(), responseString))
//                        .build();
                callback.onSuccess(responseJSONObject);
            } else {
                int errorCode = result.optInt("errorcode");
                String msg = result.optString("msg");
                CommonOKHttpException commonOKHttpException = new CommonOKHttpException(msg);
                commonOKHttpException.setStatus(status);
                commonOKHttpException.setErrorCode(errorCode);
                callback.onFailure(commonOKHttpException);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(new IOException("The response data is not the json data"));
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        if (callback == null) {
            return;
        }
        Exception exception = new Exception(t.getMessage());
        callback.onFailure(exception);
    }
}
