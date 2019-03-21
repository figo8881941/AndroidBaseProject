package com.duoduo.commonbusiness.net.volley;


import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * 网络请求服务器处理错误
 * 
 * @author wangzhuobin
 *
 */
public class CommonVolleyServerError extends VolleyError {

	/*
	 * status=1:处理成功;
	 */
	private int status;
	private int errorCode;

	public CommonVolleyServerError() {
		super();
	}

	public CommonVolleyServerError(NetworkResponse response) {
		super(response);
	}

	public CommonVolleyServerError(String exceptionMessage, Throwable reason) {
		super(exceptionMessage, reason);
	}

	public CommonVolleyServerError(String exceptionMessage) {
		super(exceptionMessage);
	}

	public CommonVolleyServerError(Throwable cause) {
		super(cause);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
