package com.duoduo.main.classify.consts;

/**
 * 分类常量
 */
public interface IClassifyConsts {

    /**
     * 服务器接口Funid
     */
    public interface FunId {
        //分类Tab数据
        public int CLASSIFY_SUB_TAB_DATA = 30328;
    }

    /**
     * 模块类型
     */
    interface ModuleType {
        //banner 非通屏
        public int BANNER_SMALL = 1;
        //banner 非通屏
        public int BANNER_SMALL_TWO = 29;
        //通用网格（3个）
        public int COMMON_GRID_THREE = 5;
        //过去一小时卖爆了
        public int HOT_SELL = 32;
        //750 * 270 尺寸的banner
        public int BANNER_LAYGE_750_270 = 48;
    }
}
