package com.duoduo.main.base.data;

import java.util.List;

/**
 * 商品信息数据单元
 */
public class ProductInfoEntity {

    /**
     * id : 126734
     * title : 氨基酸无硅油控油洗发水
     * couponEndTime : 2018-11-07 00:00:00
     * couponStartTime : 2018-11-01 00:00:00
     * couponTotalCount : 50000
     * couponRemainCoun : 48962
     * tkRate : 30.00
     * sourceId : 561203843768
     * sourceType : 天猫
     * img : https://img.alicdn.com/imgextra/i2/1051474214/TB2e7aJfborBKNjSZFjXXc_SpXa_!!1051474214.jpg_300x300q90.jpg_.webp
     * finalPrice : 29.9
     * reservePrice : 69.9
     * couponInfo : 满29元减20元
     * couponClickUrl : https://uland.taobao.com/coupon/edetail?e=1zgD1Yj06nwGQASttHIRqXwnLXgMjE%2BCbAT0piMRwlvl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEFZrtTOLgXkcjDppvlX%2Bob%2F%2BTosbFmliBzQxd0%2F7s8Sy8%2B1UQ0U%2BCWuQzbQRBC%2BhroQBivGEg%2FCfgb4q2aFXgxA%3D&traceId=0b0b3f1715415609597154285e&union_lens=lensId:0b0838c1_0bff_166ec3182f7_acc7
     * clickUrl : https://item.taobao.com/item.htm?id=561203843768
     * createtime : 2018-06-22 16:55:55
     * updatetime : 2018-11-07 11:25:08
     * optime : 2018-11-07 11:04:12
     * tagName : 20元券,返¥0.30
     * operator : 扣扣群入库
     * sourceStatus : 1
     * status : 1
     * fanliAmount : 3
     * mallRebateIntegral : 0
     * mallRebateMoney : 0.3
     * rebatePrecent : 3
     * mallType : 0
     * category : 家居百货
     * categoryLeaf : 纸品清洁
     * sellAmounts : 540
     * couponFinalPrice : 9.9
     * tagList : [{"name":"20元券","productPosition":3}]
     * action : vipgift://com.xmiles.vipgift/main/categoryRanking/categoryRankingActivity?title=每日排行榜&topicId=2044&showTotal=true
     * handPrice : 9.9
     * categoryId : 12
     * categoryLeafId : 96
     * isJuhuasuan : 0
     * isTaoqianggou : 0
     * isHaitao : 0
     * superRebateMoney : 0
     * superRebateStart : 2018-06-01 01:00:00
     * superRebateEnd : 2058-06-01 01:00:00
     * isShare : 1
     * isPayTribute : 1
     * sorted : 1
     * sortedType : 2
     * belong : 2044
     * useTlj : 0
     * favoritesTitle :
     * shortUrl :
     * aWordDesc :
     * productDesc :
     */

    private int id;
    private String title;
    private String couponEndTime;
    private String couponStartTime;
    private int couponTotalCount;
    private int couponRemainCoun;
    private String tkRate;
    private String sourceId;
    private String sourceType;
    private String img;
    private double finalPrice;
    private String finalPriceString;
    private double reservePrice;
    private String couponInfo;
    private String couponClickUrl;
    private String clickUrl;
    private String createtime;
    private String updatetime;
    private String optime;
    private String tagName;
    private String operator;
    private int sourceStatus;
    private int status;
    private int fanliAmount;
    private int mallRebateIntegral;
    private double mallRebateMoney;
    private int rebatePrecent;
    private int mallType;
    private String category;
    private String categoryLeaf;
    private String sellAmounts;
    private String sellAmountsString;
    private double couponFinalPrice;
    private String action;
    private double handPrice;
    private String handPriceString;
    private int categoryId;
    private int categoryLeafId;
    private int isJuhuasuan;
    private int isTaoqianggou;
    private int isHaitao;
    private int superRebateMoney;
    private String superRebateStart;
    private String superRebateEnd;
    private int isShare;
    private int isPayTribute;
    private int sorted;
    private int sortedType;
    private String belong;
    private int useTlj;
    private String favoritesTitle;
    private String shortUrl;
    private String aWordDesc;
    private String productDesc;
    private List<TagListEntity> tagList;

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

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public int getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponTotalCount(int couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public int getCouponRemainCoun() {
        return couponRemainCoun;
    }

    public void setCouponRemainCoun(int couponRemainCoun) {
        this.couponRemainCoun = couponRemainCoun;
    }

    public String getTkRate() {
        return tkRate;
    }

    public void setTkRate(String tkRate) {
        this.tkRate = tkRate;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getFinalPriceString() {
        return finalPriceString;
    }

    public void setFinalPriceString(String finalPriceString) {
        this.finalPriceString = finalPriceString;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getCouponClickUrl() {
        return couponClickUrl;
    }

    public void setCouponClickUrl(String couponClickUrl) {
        this.couponClickUrl = couponClickUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(int sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFanliAmount() {
        return fanliAmount;
    }

    public void setFanliAmount(int fanliAmount) {
        this.fanliAmount = fanliAmount;
    }

    public int getMallRebateIntegral() {
        return mallRebateIntegral;
    }

    public void setMallRebateIntegral(int mallRebateIntegral) {
        this.mallRebateIntegral = mallRebateIntegral;
    }

    public double getMallRebateMoney() {
        return mallRebateMoney;
    }

    public void setMallRebateMoney(double mallRebateMoney) {
        this.mallRebateMoney = mallRebateMoney;
    }

    public int getRebatePrecent() {
        return rebatePrecent;
    }

    public void setRebatePrecent(int rebatePrecent) {
        this.rebatePrecent = rebatePrecent;
    }

    public int getMallType() {
        return mallType;
    }

    public void setMallType(int mallType) {
        this.mallType = mallType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryLeaf() {
        return categoryLeaf;
    }

    public void setCategoryLeaf(String categoryLeaf) {
        this.categoryLeaf = categoryLeaf;
    }

    public String getSellAmounts() {
        return sellAmounts;
    }

    public void setSellAmounts(String sellAmounts) {
        this.sellAmounts = sellAmounts;
    }

    public String getSellAmountsString() {
        return sellAmountsString;
    }

    public void setSellAmountsString(String sellAmountsString) {
        this.sellAmountsString = sellAmountsString;
    }

    public double getCouponFinalPrice() {
        return couponFinalPrice;
    }

    public void setCouponFinalPrice(double couponFinalPrice) {
        this.couponFinalPrice = couponFinalPrice;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getHandPrice() {
        return handPrice;
    }

    public void setHandPrice(double handPrice) {
        this.handPrice = handPrice;
    }

    public String getHandPriceString() {
        return handPriceString;
    }

    public void setHandPriceString(String handPriceString) {
        this.handPriceString = handPriceString;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryLeafId() {
        return categoryLeafId;
    }

    public void setCategoryLeafId(int categoryLeafId) {
        this.categoryLeafId = categoryLeafId;
    }

    public int getIsJuhuasuan() {
        return isJuhuasuan;
    }

    public void setIsJuhuasuan(int isJuhuasuan) {
        this.isJuhuasuan = isJuhuasuan;
    }

    public int getIsTaoqianggou() {
        return isTaoqianggou;
    }

    public void setIsTaoqianggou(int isTaoqianggou) {
        this.isTaoqianggou = isTaoqianggou;
    }

    public int getIsHaitao() {
        return isHaitao;
    }

    public void setIsHaitao(int isHaitao) {
        this.isHaitao = isHaitao;
    }

    public int getSuperRebateMoney() {
        return superRebateMoney;
    }

    public void setSuperRebateMoney(int superRebateMoney) {
        this.superRebateMoney = superRebateMoney;
    }

    public String getSuperRebateStart() {
        return superRebateStart;
    }

    public void setSuperRebateStart(String superRebateStart) {
        this.superRebateStart = superRebateStart;
    }

    public String getSuperRebateEnd() {
        return superRebateEnd;
    }

    public void setSuperRebateEnd(String superRebateEnd) {
        this.superRebateEnd = superRebateEnd;
    }

    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public int getIsPayTribute() {
        return isPayTribute;
    }

    public void setIsPayTribute(int isPayTribute) {
        this.isPayTribute = isPayTribute;
    }

    public int getSorted() {
        return sorted;
    }

    public void setSorted(int sorted) {
        this.sorted = sorted;
    }

    public int getSortedType() {
        return sortedType;
    }

    public void setSortedType(int sortedType) {
        this.sortedType = sortedType;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public int getUseTlj() {
        return useTlj;
    }

    public void setUseTlj(int useTlj) {
        this.useTlj = useTlj;
    }

    public String getFavoritesTitle() {
        return favoritesTitle;
    }

    public void setFavoritesTitle(String favoritesTitle) {
        this.favoritesTitle = favoritesTitle;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getAWordDesc() {
        return aWordDesc;
    }

    public void setAWordDesc(String aWordDesc) {
        this.aWordDesc = aWordDesc;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public List<TagListEntity> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagListEntity> tagList) {
        this.tagList = tagList;
    }

    public static class TagListEntity {
        /**
         * name : 20元券
         * productPosition : 3
         */

        private String name;
        private int productPosition;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProductPosition() {
            return productPosition;
        }

        public void setProductPosition(int productPosition) {
            this.productPosition = productPosition;
        }
    }
}
