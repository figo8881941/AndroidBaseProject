package com.duoduo.main.classify.home.data;

import com.duoduo.main.base.data.ProductInfoEntity;
import com.duoduo.main.base.data.ResultEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 分类首页数据Bean
 */
public class ClassifySubHomeEntity implements Serializable {


    /**
     * moduleDtoList : [{"moduleId":589,"title":"","isMore":false,"type":48,"entranceItemDtoList":[{"id":267034,"title":"双11美妆预售","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11美妆预售&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fmos.m.taobao.com%2Ftaokeapp%2Fwishes%2Fsquare.html%3Fspm%3Da211oj.12109038.7895248590.d00.5cfd3ae0Egtvcx%26pos%3D1%26acm%3Dak-zebra-32461-105832.1003.1.2596533_0%26scm%3D1003.1.ak-zebra-32461-105832.OTHER_1540809276825_2596533%26ali_trackid%3D2%253Amm_132897545_45368612_45024850168%253A1541034144_233_1857615075","img":"https://imgs.gmilesquan.com//bNoRPsah.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267100,"title":"大额券","action":"vipgift://com.xmiles.vipgift/main/legendary/LegendarySecondActivity?isShowRanking=1&tabId=20162&title=大额券","img":"https://imgs.gmilesquan.com//ErlMtlMV.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":22,"redirectId":"20162","activityId":"","limitShowTimes":1,"itemTags":"","type":1,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0,"relationId":""},{"id":267109,"title":"双11全球狂欢节","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11全球狂欢节&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fs.click.taobao.com%2FntGD5Lw","img":"https://imgs.gmilesquan.com//yAibXuiv.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":1,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267101,"title":"双11尖货排行榜","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11尖货排行榜&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fmos.m.taobao.com%2Ftaokeapp%2F20181111yslmappppcjbkgy%3Fpid%3Dmm_132897545_45448502_54235900490","img":"https://imgs.gmilesquan.com//FIIBpVxi.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":1,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267110,"title":"双11天猫超市","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11天猫超市&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fs.click.taobao.com%2FPdIieKw","img":"https://imgs.gmilesquan.com//RHZcuBiK.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267035,"title":"火锅","titleImg":"https://imgs.gmilesquan.com//kkyMlZJF.png","action":"vipgift://com.xmiles.vipgift/mall/MallWelfareActivity?tabValue=20184&title=火锅","img":"https://imgs.gmilesquan.com//KEdAdzAd.png","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":8,"redirectId":"20184","limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0}],"showNumber":4,"isHideFooter":0,"personal":0,"isFlashSale":0,"sorted":0},{"moduleId":588,"title":"","isMore":false,"type":5,"entranceItemDtoList":[{"id":267030,"title":"9.9包邮","titleImg":"","description":"云南三七牙膏2只送牙刷","action":"vipgift://com.xmiles.vipgift/main/legendary/LegendarySecondActivity?isShowRanking=1&tabId=20161&title=9.9包邮","img":"https://imgs.gmilesquan.com//lhPRXXJp.png","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","titleColor":"#FF2A2A","redirectType":22,"redirectId":"20161","limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"movieImg":"https://imgs.gmilesquan.com//WnwVcwYe.gif","newTagList":[{"name":"健康口腔￥9.9","position":1}],"redirectSecondPage":0},{"id":267031,"title":"大额券","titleImg":"","description":"V7美白遮瑕素颜面霜","action":"vipgift://com.xmiles.vipgift/main/legendary/LegendarySecondActivity?isShowRanking=1&tabId=20162&title=大额券","img":"https://imgs.gmilesquan.com//xmrmysfV.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","titleColor":"#2ABCFF","redirectType":22,"redirectId":"20162","limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[{"name":"50元券￥9.9","position":1},{"name":"","position":2}],"redirectSecondPage":0},{"id":267032,"title":"秋冬女装","titleImg":"","description":"百搭bf风牛仔外套","action":"vipgift://com.xmiles.vipgift/main/classify/ClassifyPageActivity?classifyId=1&title=女装","img":"https://imgs.gmilesquan.com//HItxWeDw.png","subTitleColor":"","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","titleColor":"#FF992A","bgColor":"","redirectType":38,"limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[{"name":"领券减100","position":1}],"redirectSecondPage":0}],"showNumber":3,"isHideFooter":0,"personal":0,"isFlashSale":0,"sorted":2,"colorType":0},{"moduleId":596,"title":"今日排行榜","titleImg":"https://imgs.gmilesquan.com//pWbTTSxo.png","isMore":true,"moreAction":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","type":32,"showNumber":5,"bgColor":"#333333","titleBgColor":"","isHideFooter":0,"personal":0,"isFlashSale":0,"sorted":4,"productInfoList":[{"id":126734,"title":"氨基酸无硅油控油洗发水","couponEndTime":"2018-11-07 00:00:00","couponStartTime":"2018-11-01 00:00:00","couponTotalCount":50000,"couponRemainCoun":48962,"tkRate":"30.00","sourceId":"561203843768","sourceType":"天猫","img":"https://img.alicdn.com/imgextra/i2/1051474214/TB2e7aJfborBKNjSZFjXXc_SpXa_!!1051474214.jpg_300x300q90.jpg_.webp","finalPrice":29.9,"reservePrice":69.9,"couponInfo":"满29元减20元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=1zgD1Yj06nwGQASttHIRqXwnLXgMjE%2BCbAT0piMRwlvl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEFZrtTOLgXkcjDppvlX%2Bob%2F%2BTosbFmliBzQxd0%2F7s8Sy8%2B1UQ0U%2BCWuQzbQRBC%2BhroQBivGEg%2FCfgb4q2aFXgxA%3D&traceId=0b0b3f1715415609597154285e&union_lens=lensId:0b0838c1_0bff_166ec3182f7_acc7","clickUrl":"https://item.taobao.com/item.htm?id=561203843768","createtime":"2018-06-22 16:55:55","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 11:04:12","tagName":"20元券,返¥0.30","operator":"扣扣群入库","sourceStatus":1,"status":1,"fanliAmount":3,"mallRebateIntegral":0,"mallRebateMoney":0.3,"rebatePrecent":3,"mallType":0,"category":"家居百货","categoryLeaf":"纸品清洁","sellAmounts":"540","couponFinalPrice":9.9,"tagList":[{"name":"20元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9.9,"categoryId":12,"categoryLeafId":96,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":1,"sortedType":2,"belong":"2044","useTlj":0},{"id":2970307,"title":"【史低价】充电防爆热水袋+布绒袋","couponEndTime":"2018-11-08 00:00:00","couponStartTime":"2018-11-06 00:00:00","couponTotalCount":50000,"couponRemainCoun":42000,"tkRate":"30.00","sourceId":"558552003131","sourceType":"天猫","img":"https://img.alicdn.com/imgextra/i2/723890556/O1CN016uC8RX1FyggdKcw6h_!!723890556.jpg_300x300q90.jpg_.webp","finalPrice":39.9,"reservePrice":39.9,"couponInfo":"满39元减30元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=eLHevGmEfRoGQASttHIRqSE55keehXWTcOHP2A9Dwmfl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEXTuIOwhScUx&traceId=0b082eff15415609382267978e&union_lens=lensId:0b14d6f2_0c2c_166ec312f04_931b","clickUrl":"https://item.taobao.com/item.htm?id=558552003131","createtime":"2018-11-07 04:40:51","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 10:30:22","tagName":"30元券,返¥0.30","operator":"好单库实时省钱","sourceStatus":1,"status":1,"fanliAmount":3,"mallRebateIntegral":0,"mallRebateMoney":0.3,"rebatePrecent":3,"mallType":0,"category":"家居百货","categoryLeaf":"居家日用","sellAmounts":"650","couponFinalPrice":9.9,"tagList":[{"name":"30元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9.9,"categoryId":12,"categoryLeafId":101,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":2,"sortedType":2,"belong":"2044","useTlj":0},{"id":2970227,"title":"玫瑰印色持久唇釉哑光豆沙枫叶口红","couponEndTime":"2018-11-08 00:00:00","couponStartTime":"2018-11-07 00:00:00","couponTotalCount":20000,"couponRemainCoun":19400,"tkRate":"30.00","sourceId":"578884062558","sourceType":"天猫","img":"https://img.alicdn.com/imgextra/i1/2090178268/O1CN012Awn96YYPonVUgv_!!2090178268.jpg_300x300q90.jpg_.webp","finalPrice":59.9,"reservePrice":59.9,"couponInfo":"满59元减50元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=BnN%2BuydL9HEGQASttHIRqQzCm%2BVOl1ZR1MsfTWR8ur7l8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEUPHrG8mGX3T&traceId=0baf517715415609390737923e&union_lens=lensId:0b093c83_0bd2_166ec31325b_4805","clickUrl":"https://item.taobao.com/item.htm?id=578884062558","createtime":"2018-11-07 04:40:50","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 10:28:08","tagName":"50元券,返¥0.30","operator":"好单库实时省钱","sourceStatus":1,"status":1,"fanliAmount":3,"mallRebateIntegral":0,"mallRebateMoney":0.3,"rebatePrecent":3,"mallType":0,"category":"美妆个护","categoryLeaf":"彩妆","sellAmounts":"221","couponFinalPrice":9.9,"tagList":[{"name":"50元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9.9,"categoryId":5,"categoryLeafId":53,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":3,"sortedType":2,"belong":"2044","useTlj":0},{"id":2949766,"title":"佰草世家素颜面霜2瓶保湿补水裸妆遮瑕","couponEndTime":"2018-11-08 00:00:00","couponStartTime":"2018-11-06 00:00:00","couponTotalCount":100000,"couponRemainCoun":50000,"tkRate":"40.00","sourceId":"578243377318","sourceType":"天猫","img":"http://img.alicdn.com/imgextra/i1/1055530397/TB2nDtqdIbpK1RjSZFyXXX_qFXa_!!1055530397.jpg_300x300q90.jpg_.webp","finalPrice":39,"reservePrice":56,"couponInfo":"满31元减30元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=i9oSptOUfiEGQASttHIRqW0HsXXBoLpMtzp4Egzjs8Hl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEZ038nTy7Qo1&traceId=0b0fad6715415609553552025e&union_lens=lensId:0b0b9f56_0bf6_166ec3171ef_8a15","clickUrl":"https://item.taobao.com/item.htm?id=578243377318","createtime":"2018-11-06 09:23:59","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 10:37:27","tagName":"30元券,返¥0.36","operator":"扣扣群入库","sourceStatus":1,"status":1,"fanliAmount":3.6,"mallRebateIntegral":0,"mallRebateMoney":0.36,"rebatePrecent":4,"mallType":0,"category":"美妆个护","categoryLeaf":"美容美体","sellAmounts":"34384","couponFinalPrice":9,"tagList":[{"name":"30元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9,"categoryId":5,"categoryLeafId":54,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":4,"sortedType":2,"belong":"2044","useTlj":0},{"id":2970408,"title":"【36包！】竹浆本色抽纸整箱装","couponEndTime":"2018-11-09 00:00:00","couponStartTime":"2018-11-07 00:00:00","couponTotalCount":30000,"couponRemainCoun":28800,"tkRate":"20.00","sourceId":"580951050258","sourceType":"天猫","favoritesTitle":"","img":"http://img.alicdn.com/imgextra/i2/3246773875/O1CN011eUnE4dwZxY9Zk4_!!3246773875.jpg_300x300q90.jpg_.webp","finalPrice":39.99,"reservePrice":59.9,"couponInfo":"满11元减10元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=eT%2BKV6SlpqoGQASttHIRqdpDJcs%2FWRVTUp%2B74%2Bf0HPPl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEXTuIOwhScUx&traceId=0b0ff54615415609388028836e&union_lens=lensId:0b093c83_0bd2_166ec313145_4759","clickUrl":"https://item.taobao.com/item.htm?id=580951050258","shortUrl":"","createtime":"2018-11-07 04:40:52","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 09:44:23","tagName":"10元券,返¥0.60","aWordDesc":"","operator":"扣扣群入库","sourceStatus":1,"status":1,"fanliAmount":6,"mallRebateIntegral":0,"mallRebateMoney":0.6,"rebatePrecent":2,"mallType":0,"category":"家居百货","categoryLeaf":"纸品清洁","sellAmounts":"41","couponFinalPrice":29.99,"tagList":[{"name":"10元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":29.99,"categoryId":12,"categoryLeafId":96,"productDesc":"","isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":5,"sortedType":2,"belong":"2044","useTlj":0}]},{"moduleId":506,"title":"双11红包","isMore":true,"moreAction":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11红包&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fs.click.taobao.com%2FntGD5Lw","type":1,"entranceItemDtoList":[{"id":231868,"title":"双11红包开抢","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11红包开抢&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fs.click.taobao.com%2FntGD5Lw","img":"https://imgs.gmilesquan.com//QuhTSVXr.gif","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":1,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0}],"showNumber":1,"isHideFooter":0,"personal":0,"isFlashSale":0,"sorted":8}]
     * topicModuleDto : {"moduleId":520,"title":"首页新版信息流","titleImg":"https://imgs.gmilesquan.com//CoVuwAcQ.png","titleBgColor":"#f6f6f6","topicPageId":10,"topicStyle":2,"topicInfoList":[{"id":10,"title":"首页新版信息流","subtitle":"首页新版信息流","remark":"首页新版信息流","isCategory":0,"status":1,"activityType":0}]}
     * currentTime : 2018-11-07 11:43:51
     * isFlashSalePage : 0
     * isNewUser : 0
     * result : {"status":1}
     * costTime : 5
     */

    private TopicModuleDtoEntity topicModuleDto;
    private String currentTime;
    private int isFlashSalePage;
    private int isNewUser;
    private ResultEntity result;
    private int costTime;
    private List<ModuleDtoListEntity> moduleDtoList;

    public TopicModuleDtoEntity getTopicModuleDto() {
        return topicModuleDto;
    }

    public void setTopicModuleDto(TopicModuleDtoEntity topicModuleDto) {
        this.topicModuleDto = topicModuleDto;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getIsFlashSalePage() {
        return isFlashSalePage;
    }

    public void setIsFlashSalePage(int isFlashSalePage) {
        this.isFlashSalePage = isFlashSalePage;
    }

    public int getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(int isNewUser) {
        this.isNewUser = isNewUser;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public int getCostTime() {
        return costTime;
    }

    public void setCostTime(int costTime) {
        this.costTime = costTime;
    }

    public List<ModuleDtoListEntity> getModuleDtoList() {
        return moduleDtoList;
    }

    public void setModuleDtoList(List<ModuleDtoListEntity> moduleDtoList) {
        this.moduleDtoList = moduleDtoList;
    }

    public static class TopicModuleDtoEntity implements Serializable {
        /**
         * moduleId : 520
         * title : 首页新版信息流
         * titleImg : https://imgs.gmilesquan.com//CoVuwAcQ.png
         * titleBgColor : #f6f6f6
         * topicPageId : 10
         * topicStyle : 2
         * topicInfoList : [{"id":10,"title":"首页新版信息流","subtitle":"首页新版信息流","remark":"首页新版信息流","isCategory":0,"status":1,"activityType":0}]
         */

        private int moduleId;
        private String title;
        private String titleImg;
        private String titleBgColor;
        private int topicPageId;
        private int topicStyle;
        private List<TopicInfoListEntity> topicInfoList;

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleImg() {
            return titleImg;
        }

        public void setTitleImg(String titleImg) {
            this.titleImg = titleImg;
        }

        public String getTitleBgColor() {
            return titleBgColor;
        }

        public void setTitleBgColor(String titleBgColor) {
            this.titleBgColor = titleBgColor;
        }

        public int getTopicPageId() {
            return topicPageId;
        }

        public void setTopicPageId(int topicPageId) {
            this.topicPageId = topicPageId;
        }

        public int getTopicStyle() {
            return topicStyle;
        }

        public void setTopicStyle(int topicStyle) {
            this.topicStyle = topicStyle;
        }

        public List<TopicInfoListEntity> getTopicInfoList() {
            return topicInfoList;
        }

        public void setTopicInfoList(List<TopicInfoListEntity> topicInfoList) {
            this.topicInfoList = topicInfoList;
        }

        public static class TopicInfoListEntity {
            /**
             * id : 10
             * title : 首页新版信息流
             * subtitle : 首页新版信息流
             * remark : 首页新版信息流
             * isCategory : 0
             * status : 1
             * activityType : 0
             */

            private int id;
            private String title;
            private String subtitle;
            private String remark;
            private int isCategory;
            private int status;
            private int activityType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getIsCategory() {
                return isCategory;
            }

            public void setIsCategory(int isCategory) {
                this.isCategory = isCategory;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getActivityType() {
                return activityType;
            }

            public void setActivityType(int activityType) {
                this.activityType = activityType;
            }
        }
    }

    public static class ModuleDtoListEntity implements Serializable {
        /**
         * moduleId : 589
         * title :
         * isMore : false
         * type : 48
         * entranceItemDtoList : [{"id":267034,"title":"双11美妆预售","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11美妆预售&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fmos.m.taobao.com%2Ftaokeapp%2Fwishes%2Fsquare.html%3Fspm%3Da211oj.12109038.7895248590.d00.5cfd3ae0Egtvcx%26pos%3D1%26acm%3Dak-zebra-32461-105832.1003.1.2596533_0%26scm%3D1003.1.ak-zebra-32461-105832.OTHER_1540809276825_2596533%26ali_trackid%3D2%253Amm_132897545_45368612_45024850168%253A1541034144_233_1857615075","img":"https://imgs.gmilesquan.com//bNoRPsah.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267100,"title":"大额券","action":"vipgift://com.xmiles.vipgift/main/legendary/LegendarySecondActivity?isShowRanking=1&tabId=20162&title=大额券","img":"https://imgs.gmilesquan.com//ErlMtlMV.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":22,"redirectId":"20162","activityId":"","limitShowTimes":1,"itemTags":"","type":1,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0,"relationId":""},{"id":267109,"title":"双11全球狂欢节","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11全球狂欢节&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fs.click.taobao.com%2FntGD5Lw","img":"https://imgs.gmilesquan.com//yAibXuiv.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":1,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267101,"title":"双11尖货排行榜","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11尖货排行榜&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fmos.m.taobao.com%2Ftaokeapp%2F20181111yslmappppcjbkgy%3Fpid%3Dmm_132897545_45448502_54235900490","img":"https://imgs.gmilesquan.com//FIIBpVxi.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":1,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267110,"title":"双11天猫超市","action":"vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11天猫超市&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fs.click.taobao.com%2FPdIieKw","img":"https://imgs.gmilesquan.com//RHZcuBiK.jpg","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":15,"limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0},{"id":267035,"title":"火锅","titleImg":"https://imgs.gmilesquan.com//kkyMlZJF.png","action":"vipgift://com.xmiles.vipgift/mall/MallWelfareActivity?tabValue=20184&title=火锅","img":"https://imgs.gmilesquan.com//KEdAdzAd.png","isHint":0,"dailyShowTimes":1,"disappearTime":"2058-01-01 00:00:00","redirectType":8,"redirectId":"20184","limitShowTimes":1,"itemTags":"","type":4,"rebateMoney":0,"newUserShare":0,"oldUserShare":0,"newTagList":[],"redirectSecondPage":0}]
         * showNumber : 4
         * isHideFooter : 0
         * personal : 0
         * isFlashSale : 0
         * sorted : 0
         * colorType : 0
         * titleImg : https://imgs.gmilesquan.com//pWbTTSxo.png
         * moreAction : vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true
         * bgColor : #333333
         * titleBgColor :
         * productInfoList : [{"id":126734,"title":"氨基酸无硅油控油洗发水","couponEndTime":"2018-11-07 00:00:00","couponStartTime":"2018-11-01 00:00:00","couponTotalCount":50000,"couponRemainCoun":48962,"tkRate":"30.00","sourceId":"561203843768","sourceType":"天猫","img":"https://img.alicdn.com/imgextra/i2/1051474214/TB2e7aJfborBKNjSZFjXXc_SpXa_!!1051474214.jpg_300x300q90.jpg_.webp","finalPrice":29.9,"reservePrice":69.9,"couponInfo":"满29元减20元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=1zgD1Yj06nwGQASttHIRqXwnLXgMjE%2BCbAT0piMRwlvl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEFZrtTOLgXkcjDppvlX%2Bob%2F%2BTosbFmliBzQxd0%2F7s8Sy8%2B1UQ0U%2BCWuQzbQRBC%2BhroQBivGEg%2FCfgb4q2aFXgxA%3D&traceId=0b0b3f1715415609597154285e&union_lens=lensId:0b0838c1_0bff_166ec3182f7_acc7","clickUrl":"https://item.taobao.com/item.htm?id=561203843768","createtime":"2018-06-22 16:55:55","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 11:04:12","tagName":"20元券,返¥0.30","operator":"扣扣群入库","sourceStatus":1,"status":1,"fanliAmount":3,"mallRebateIntegral":0,"mallRebateMoney":0.3,"rebatePrecent":3,"mallType":0,"category":"家居百货","categoryLeaf":"纸品清洁","sellAmounts":"540","couponFinalPrice":9.9,"tagList":[{"name":"20元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9.9,"categoryId":12,"categoryLeafId":96,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":1,"sortedType":2,"belong":"2044","useTlj":0},{"id":2970307,"title":"【史低价】充电防爆热水袋+布绒袋","couponEndTime":"2018-11-08 00:00:00","couponStartTime":"2018-11-06 00:00:00","couponTotalCount":50000,"couponRemainCoun":42000,"tkRate":"30.00","sourceId":"558552003131","sourceType":"天猫","img":"https://img.alicdn.com/imgextra/i2/723890556/O1CN016uC8RX1FyggdKcw6h_!!723890556.jpg_300x300q90.jpg_.webp","finalPrice":39.9,"reservePrice":39.9,"couponInfo":"满39元减30元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=eLHevGmEfRoGQASttHIRqSE55keehXWTcOHP2A9Dwmfl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEXTuIOwhScUx&traceId=0b082eff15415609382267978e&union_lens=lensId:0b14d6f2_0c2c_166ec312f04_931b","clickUrl":"https://item.taobao.com/item.htm?id=558552003131","createtime":"2018-11-07 04:40:51","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 10:30:22","tagName":"30元券,返¥0.30","operator":"好单库实时省钱","sourceStatus":1,"status":1,"fanliAmount":3,"mallRebateIntegral":0,"mallRebateMoney":0.3,"rebatePrecent":3,"mallType":0,"category":"家居百货","categoryLeaf":"居家日用","sellAmounts":"650","couponFinalPrice":9.9,"tagList":[{"name":"30元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9.9,"categoryId":12,"categoryLeafId":101,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":2,"sortedType":2,"belong":"2044","useTlj":0},{"id":2970227,"title":"玫瑰印色持久唇釉哑光豆沙枫叶口红","couponEndTime":"2018-11-08 00:00:00","couponStartTime":"2018-11-07 00:00:00","couponTotalCount":20000,"couponRemainCoun":19400,"tkRate":"30.00","sourceId":"578884062558","sourceType":"天猫","img":"https://img.alicdn.com/imgextra/i1/2090178268/O1CN012Awn96YYPonVUgv_!!2090178268.jpg_300x300q90.jpg_.webp","finalPrice":59.9,"reservePrice":59.9,"couponInfo":"满59元减50元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=BnN%2BuydL9HEGQASttHIRqQzCm%2BVOl1ZR1MsfTWR8ur7l8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEUPHrG8mGX3T&traceId=0baf517715415609390737923e&union_lens=lensId:0b093c83_0bd2_166ec31325b_4805","clickUrl":"https://item.taobao.com/item.htm?id=578884062558","createtime":"2018-11-07 04:40:50","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 10:28:08","tagName":"50元券,返¥0.30","operator":"好单库实时省钱","sourceStatus":1,"status":1,"fanliAmount":3,"mallRebateIntegral":0,"mallRebateMoney":0.3,"rebatePrecent":3,"mallType":0,"category":"美妆个护","categoryLeaf":"彩妆","sellAmounts":"221","couponFinalPrice":9.9,"tagList":[{"name":"50元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9.9,"categoryId":5,"categoryLeafId":53,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":3,"sortedType":2,"belong":"2044","useTlj":0},{"id":2949766,"title":"佰草世家素颜面霜2瓶保湿补水裸妆遮瑕","couponEndTime":"2018-11-08 00:00:00","couponStartTime":"2018-11-06 00:00:00","couponTotalCount":100000,"couponRemainCoun":50000,"tkRate":"40.00","sourceId":"578243377318","sourceType":"天猫","img":"http://img.alicdn.com/imgextra/i1/1055530397/TB2nDtqdIbpK1RjSZFyXXX_qFXa_!!1055530397.jpg_300x300q90.jpg_.webp","finalPrice":39,"reservePrice":56,"couponInfo":"满31元减30元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=i9oSptOUfiEGQASttHIRqW0HsXXBoLpMtzp4Egzjs8Hl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEZ038nTy7Qo1&traceId=0b0fad6715415609553552025e&union_lens=lensId:0b0b9f56_0bf6_166ec3171ef_8a15","clickUrl":"https://item.taobao.com/item.htm?id=578243377318","createtime":"2018-11-06 09:23:59","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 10:37:27","tagName":"30元券,返¥0.36","operator":"扣扣群入库","sourceStatus":1,"status":1,"fanliAmount":3.6,"mallRebateIntegral":0,"mallRebateMoney":0.36,"rebatePrecent":4,"mallType":0,"category":"美妆个护","categoryLeaf":"美容美体","sellAmounts":"34384","couponFinalPrice":9,"tagList":[{"name":"30元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":9,"categoryId":5,"categoryLeafId":54,"isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":4,"sortedType":2,"belong":"2044","useTlj":0},{"id":2970408,"title":"【36包！】竹浆本色抽纸整箱装","couponEndTime":"2018-11-09 00:00:00","couponStartTime":"2018-11-07 00:00:00","couponTotalCount":30000,"couponRemainCoun":28800,"tkRate":"20.00","sourceId":"580951050258","sourceType":"天猫","favoritesTitle":"","img":"http://img.alicdn.com/imgextra/i2/3246773875/O1CN011eUnE4dwZxY9Zk4_!!3246773875.jpg_300x300q90.jpg_.webp","finalPrice":39.99,"reservePrice":59.9,"couponInfo":"满11元减10元","couponClickUrl":"https://uland.taobao.com/coupon/edetail?e=eT%2BKV6SlpqoGQASttHIRqdpDJcs%2FWRVTUp%2B74%2Bf0HPPl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwRxlHqDXtYNEXTuIOwhScUx&traceId=0b0ff54615415609388028836e&union_lens=lensId:0b093c83_0bd2_166ec313145_4759","clickUrl":"https://item.taobao.com/item.htm?id=580951050258","shortUrl":"","createtime":"2018-11-07 04:40:52","updatetime":"2018-11-07 11:25:08","optime":"2018-11-07 09:44:23","tagName":"10元券,返¥0.60","aWordDesc":"","operator":"扣扣群入库","sourceStatus":1,"status":1,"fanliAmount":6,"mallRebateIntegral":0,"mallRebateMoney":0.6,"rebatePrecent":2,"mallType":0,"category":"家居百货","categoryLeaf":"纸品清洁","sellAmounts":"41","couponFinalPrice":29.99,"tagList":[{"name":"10元券","productPosition":3}],"action":"vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true","handPrice":29.99,"categoryId":12,"categoryLeafId":96,"productDesc":"","isJuhuasuan":0,"isTaoqianggou":0,"isHaitao":0,"superRebateMoney":0,"superRebateStart":"2018-06-01 01:00:00","superRebateEnd":"2058-06-01 01:00:00","isShare":1,"isPayTribute":1,"sorted":5,"sortedType":2,"belong":"2044","useTlj":0}]
         */

        private int moduleId;
        private String title;
        private boolean isMore;
        private int type;
        private int showNumber;
        private int isHideFooter;
        private int personal;
        private int isFlashSale;
        private int sorted;
        private int colorType;
        private String titleImg;
        private String moreAction;
        private String bgColor;
        private String titleBgColor;
        private List<EntranceItemDtoListEntity> entranceItemDtoList;
        private List<ProductInfoEntity> productInfoList;

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isIsMore() {
            return isMore;
        }

        public void setIsMore(boolean isMore) {
            this.isMore = isMore;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getShowNumber() {
            return showNumber;
        }

        public void setShowNumber(int showNumber) {
            this.showNumber = showNumber;
        }

        public int getIsHideFooter() {
            return isHideFooter;
        }

        public void setIsHideFooter(int isHideFooter) {
            this.isHideFooter = isHideFooter;
        }

        public int getPersonal() {
            return personal;
        }

        public void setPersonal(int personal) {
            this.personal = personal;
        }

        public int getIsFlashSale() {
            return isFlashSale;
        }

        public void setIsFlashSale(int isFlashSale) {
            this.isFlashSale = isFlashSale;
        }

        public int getSorted() {
            return sorted;
        }

        public void setSorted(int sorted) {
            this.sorted = sorted;
        }

        public int getColorType() {
            return colorType;
        }

        public void setColorType(int colorType) {
            this.colorType = colorType;
        }

        public String getTitleImg() {
            return titleImg;
        }

        public void setTitleImg(String titleImg) {
            this.titleImg = titleImg;
        }

        public String getMoreAction() {
            return moreAction;
        }

        public void setMoreAction(String moreAction) {
            this.moreAction = moreAction;
        }

        public String getBgColor() {
            return bgColor;
        }

        public void setBgColor(String bgColor) {
            this.bgColor = bgColor;
        }

        public String getTitleBgColor() {
            return titleBgColor;
        }

        public void setTitleBgColor(String titleBgColor) {
            this.titleBgColor = titleBgColor;
        }

        public List<EntranceItemDtoListEntity> getEntranceItemDtoList() {
            return entranceItemDtoList;
        }

        public void setEntranceItemDtoList(List<EntranceItemDtoListEntity> entranceItemDtoList) {
            this.entranceItemDtoList = entranceItemDtoList;
        }

        public List<ProductInfoEntity> getProductInfoList() {
            return productInfoList;
        }

        public void setProductInfoList(List<ProductInfoEntity> productInfoList) {
            this.productInfoList = productInfoList;
        }

        public static class EntranceItemDtoListEntity implements Serializable {

            /**
             * id : 267030
             * title : 9.9包邮
             * titleImg :
             * description : 云南三七牙膏2只送牙刷
             * action : vipgift://com.xmiles.vipgift/main/legendary/LegendarySecondActivity?isShowRanking=1&tabId=20161&title=9.9包邮
             * img : https://imgs.gmilesquan.com//lhPRXXJp.png
             * isHint : 0
             * dailyShowTimes : 1
             * disappearTime : 2058-01-01 00:00:00
             * titleColor : #FF2A2A
             * redirectType : 22
             * redirectId : 20161
             * limitShowTimes : 1
             * itemTags :
             * type : 4
             * rebateMoney : 0
             * newUserShare : 0
             * oldUserShare : 0
             * movieImg : https://imgs.gmilesquan.com//WnwVcwYe.gif
             * newTagList : [{"name":"健康口腔￥9.9","position":1}]
             * redirectSecondPage : 0
             */

            private int id;
            private String title;
            private String titleImg;
            private String description;
            private String action;
            private String img;
            private int isHint;
            private int dailyShowTimes;
            private String disappearTime;
            private String titleColor;
            private int redirectType;
            private String redirectId;
            private int limitShowTimes;
            private String itemTags;
            private int type;
            private int rebateMoney;
            private int newUserShare;
            private int oldUserShare;
            private String movieImg;
            private int redirectSecondPage;
            private List<NewTagListEntity> newTagList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitleImg() {
                return titleImg;
            }

            public void setTitleImg(String titleImg) {
                this.titleImg = titleImg;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getIsHint() {
                return isHint;
            }

            public void setIsHint(int isHint) {
                this.isHint = isHint;
            }

            public int getDailyShowTimes() {
                return dailyShowTimes;
            }

            public void setDailyShowTimes(int dailyShowTimes) {
                this.dailyShowTimes = dailyShowTimes;
            }

            public String getDisappearTime() {
                return disappearTime;
            }

            public void setDisappearTime(String disappearTime) {
                this.disappearTime = disappearTime;
            }

            public String getTitleColor() {
                return titleColor;
            }

            public void setTitleColor(String titleColor) {
                this.titleColor = titleColor;
            }

            public int getRedirectType() {
                return redirectType;
            }

            public void setRedirectType(int redirectType) {
                this.redirectType = redirectType;
            }

            public String getRedirectId() {
                return redirectId;
            }

            public void setRedirectId(String redirectId) {
                this.redirectId = redirectId;
            }

            public int getLimitShowTimes() {
                return limitShowTimes;
            }

            public void setLimitShowTimes(int limitShowTimes) {
                this.limitShowTimes = limitShowTimes;
            }

            public String getItemTags() {
                return itemTags;
            }

            public void setItemTags(String itemTags) {
                this.itemTags = itemTags;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getRebateMoney() {
                return rebateMoney;
            }

            public void setRebateMoney(int rebateMoney) {
                this.rebateMoney = rebateMoney;
            }

            public int getNewUserShare() {
                return newUserShare;
            }

            public void setNewUserShare(int newUserShare) {
                this.newUserShare = newUserShare;
            }

            public int getOldUserShare() {
                return oldUserShare;
            }

            public void setOldUserShare(int oldUserShare) {
                this.oldUserShare = oldUserShare;
            }

            public String getMovieImg() {
                return movieImg;
            }

            public void setMovieImg(String movieImg) {
                this.movieImg = movieImg;
            }

            public int getRedirectSecondPage() {
                return redirectSecondPage;
            }

            public void setRedirectSecondPage(int redirectSecondPage) {
                this.redirectSecondPage = redirectSecondPage;
            }

            public List<NewTagListEntity> getNewTagList() {
                return newTagList;
            }

            public void setNewTagList(List<NewTagListEntity> newTagList) {
                this.newTagList = newTagList;
            }

            public static class NewTagListEntity {
                /**
                 * name : 健康口腔￥9.9
                 * position : 1
                 */

                private String name;
                private int position;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPosition() {
                    return position;
                }

                public void setPosition(int position) {
                    this.position = position;
                }
            }
        }
    }
}
