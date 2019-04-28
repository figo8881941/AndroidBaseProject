package com.duoduo.main.base.data;

import java.io.Serializable;

/**
 * 网络请求结果数据单元
 */
public class ResultEntity implements Serializable {

    /**
     * status : 1
     */

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
