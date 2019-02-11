package com.duoduo.commonbusiness.web;

/**
 * Web模块相关的常量
 */
public interface IWebConsts {

    public interface Key {

        /**
         *  phead的键值
         */
        public String KEY_PHEAD = "phead";

        /**
         *  data的键值
         */
        public String KEY_DATA = "data";
    }

    public interface JS {
        String METHOD_GET_H5_DATA = "getDataFromH5";
        public String METHOD_REFRESH = "javascript:refresh()";
        public String METHOD_ON_BACKPRESSED = "javascript:onBackPressed()";
        public String METHOD_ON_RESUME = "javascript:onResume()";
        public String METHOD_ON_PAUSE = "javascript:onPause()";
        public String METHOD_HANDLE_EVENT = "javascript:handleEvent()";
        public String METHOD_PAY_CALLBACK = "javascript:onAppPayResult";
        public String METHOD_CLOSEAD = "javascript:onCloseAd";
    }
}
