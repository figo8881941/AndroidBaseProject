package com.duoduo.commonbusiness.net;


import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * 网络请求服务器处理错误
 * 
 * @author wangzhuobin
 *
 */
public class CommonServerError extends VolleyError {

	/*
	 * status=1:处理成功;
	 */
	private int status;
	private int errorCode;
	private String message;

	public CommonServerError() {
		super();
	}

	public CommonServerError(NetworkResponse response) {
		super(response);
	}

	public CommonServerError(String exceptionMessage, Throwable reason) {
		super(exceptionMessage, reason);
	}

	public CommonServerError(String exceptionMessage) {
		super(exceptionMessage);
	}

	public CommonServerError(Throwable cause) {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
