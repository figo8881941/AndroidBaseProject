package com.duoduo.main.main.consts;

/**
 * 主界面相关常量
 */
public interface IMainConsts {

    /**
     * 主界面特定tab id常量值
     */
    public interface MainTabIdValue {
        public int HOME_TAB = 1000;
        public int SHOP_TAB = 1001;
        //我的Tab
        public int MINE_TAB = 1002;
        public int RECOMMEND_TAB = 1005;
        public int SEARCH_TAB = 1007;
        public int FINANCE_TAB = 2000;
        public int CLASSIFY_TAB = 1999;
        public int LEGENDARY_COUPON_TAB = 1998;
        public int SAVE_MONEY_LEADERBOARD_PAGE = 33331;
        public int FOUR_GIFT_TAB = 8;
        //大额券Tab
        public int LARGE_AMOUNT_COUPON_TAB = 1008;
        //包邮Tab
        public int SHIPPING_TAB = 3000;
        //分类类目汇总
        public int CATEGORY_TAB = 2010;
        //收藏列表
        public int COLLECTION_TAB = 2011;
    }

    /**
     * 主界面特定tab类型常量值
     */
    public interface MainTabTypeValue {
        //H5类型
        public int TYPE_H5 = 2;
        //分类类型
        public int TYPE_CLASSIFY = 4;
    }

    /**
     * 服务器接口Funid
     */
    public interface FunId {
        //首页Tab
        public int MAIN_TAB = 30000;
        //首页数据
        public int MAIN_PAGE_DATA = 30001;
    }
}
