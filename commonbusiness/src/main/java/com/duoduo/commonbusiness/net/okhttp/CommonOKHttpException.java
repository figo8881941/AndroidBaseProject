package com.duoduo.commonbusiness.net.okhttp;

import java.io.IOException;

/**
 * OKHttp业务Exception
 */
public class CommonOKHttpException extends IOException {

    /*
     * status=1:处理成功;
     */
    private int status;
    private int errorCode;
    private String message;

    public CommonOKHttpException() {
    }

    public CommonOKHttpException(String message) {
        super(message);
    }

    public CommonOKHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonOKHttpException(Throwable cause) {
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
