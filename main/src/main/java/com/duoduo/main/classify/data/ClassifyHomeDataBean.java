package com.duoduo.main.classify.data;

import java.util.List;

/**
 * 分类首页数据Bean
 */
public class ClassifyHomeDataBean {

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

    public static class TopicModuleDtoEntity {
        /**
         * moduleId : 520
         * title : 首页新版信息流
         * titleImg : https://imgs.gmilesquan.com//CoVuwAcQ.png
         * titleBgColor : #f6f6f6
         * topicPageId : 10
         * topicStyle : 2
         */

        private int moduleId;
        private String title;
        private String titleImg;
        private String titleBgColor;
        private int topicPageId;
        private int topicStyle;

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
    }

    public static class ResultEntity {
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

    public static class ModuleDtoListEntity {

        private int moduleId;
        private boolean isMore;
        private int type;
        private int showNumber;
        private String bgImg;
        private int isHideFooter;
        private int personal;
        private int isFlashSale;
        private int sorted;
        private int colorType;
        private String title;
        private String titleImg;
        private String moreAction;
        private String flashSaleStartTime;
        private String flashSaleEndTime;
        private List<EntranceItemDtoListEntity> entranceItemDtoList;
        private List<ProductInfoListEntity> productInfoList;

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
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

        public String getBgImg() {
            return bgImg;
        }

        public void setBgImg(String bgImg) {
            this.bgImg = bgImg;
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

        public String getMoreAction() {
            return moreAction;
        }

        public void setMoreAction(String moreAction) {
            this.moreAction = moreAction;
        }

        public String getFlashSaleStartTime() {
            return flashSaleStartTime;
        }

        public void setFlashSaleStartTime(String flashSaleStartTime) {
            this.flashSaleStartTime = flashSaleStartTime;
        }

        public String getFlashSaleEndTime() {
            return flashSaleEndTime;
        }

        public void setFlashSaleEndTime(String flashSaleEndTime) {
            this.flashSaleEndTime = flashSaleEndTime;
        }

        public List<EntranceItemDtoListEntity> getEntranceItemDtoList() {
            return entranceItemDtoList;
        }

        public void setEntranceItemDtoList(List<EntranceItemDtoListEntity> entranceItemDtoList) {
            this.entranceItemDtoList = entranceItemDtoList;
        }

        public List<ProductInfoListEntity> getProductInfoList() {
            return productInfoList;
        }

        public void setProductInfoList(List<ProductInfoListEntity> productInfoList) {
            this.productInfoList = productInfoList;
        }

        public static class EntranceItemDtoListEntity {
            /**
             * id : 267103
             * title : 双11零食预售
             * action : vipgift://com.xmiles.vipgift/web/CommonWebViewActivity?title=双11零食预售&withHead=true&showTitle=true&usePost=false&htmlUrl=https%3A%2F%2Fs.click.taobao.com%2FsKRg1Lw
             * img : https://imgs.gmilesquan.com//irTQMLEe.jpg
             * isHint : 0
             * dailyShowTimes : 1
             * disappearTime : 2058-01-01 00:00:00
             * redirectType : 15
             * limitShowTimes : 1
             * itemTags :
             * type : 1
             * rebateMoney : 0
             * newUserShare : 0
             * oldUserShare : 0
             * newTagList : []
             * redirectSecondPage : 0
             * redirectId : 20185
             * description : 无忧轻松贷
             */

            private int id;
            private String title;
            private String action;
            private String img;
            private int isHint;
            private int dailyShowTimes;
            private String disappearTime;
            private int redirectType;
            private int limitShowTimes;
            private String itemTags;
            private int type;
            private int rebateMoney;
            private int newUserShare;
            private int oldUserShare;
            private int redirectSecondPage;
            private String redirectId;
            private String description;
            private List<?> newTagList;

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

            public int getRedirectType() {
                return redirectType;
            }

            public void setRedirectType(int redirectType) {
                this.redirectType = redirectType;
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

            public int getRedirectSecondPage() {
                return redirectSecondPage;
            }

            public void setRedirectSecondPage(int redirectSecondPage) {
                this.redirectSecondPage = redirectSecondPage;
            }

            public String getRedirectId() {
                return redirectId;
            }

            public void setRedirectId(String redirectId) {
                this.redirectId = redirectId;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<?> getNewTagList() {
                return newTagList;
            }

            public void setNewTagList(List<?> newTagList) {
                this.newTagList = newTagList;
            }
        }

        public static class ProductInfoListEntity {
            /**
             * id : 13874
             * title : 阅客武夷岩大红袍茶叶罐100g装
             * couponEndTime : 2018-10-27 00:00:00
             * couponStartTime : 2018-10-26 00:00:00
             * couponTotalCount : 30000
             * couponRemainCoun : 24900
             * tkRate : 40.00
             * sourceId : 537126670638
             * sourceType : 天猫
             * img : https://img.alicdn.com/imgextra/i4/850040068/O1CN011CNBNpxjdXlKKeZ_!!850040068.jpg_300x300q90.jpg_.webp
             * finalPrice : 66.9
             * reservePrice : 66.9
             * couponInfo : 满66元减60元
             * couponClickUrl : https://uland.taobao.com/coupon/edetail?e=iC16bAWLdyoGQASttHIRqURMQ6ORIgXAUVCA5Pn1dBXl8TyjTR1o2CHO2FjjH5sluE9t7%2FQPwGcvc742eFIdw8WKxy1EFPIV%2BGoSNEAjokSwbCChdx8LEBemP0hpIIPvjDppvlX%2Bob8NlNJBuapvQ2MDg9t1zp0R8pjV3C9qcwS9c%2FAE6eorHaxpXdTTUedq&traceId=0b151a1015405456674328241e&union_lens=lensId:0b0fc866_0c39_166afad6178_20fd
             * clickUrl : https://item.taobao.com/item.htm?id=537126670638
             * createtime : 2018-07-01 00:00:00
             * updatetime : 2018-10-26 17:25:15
             * optime : 2018-10-26 03:16:34
             * tagName : 60元券,返¥0.28
             * operator : 大淘客
             * sourceStatus : 1
             * status : 1
             * fanliAmount : 2.8
             * mallRebateIntegral : 0
             * mallRebateMoney : 0.28
             * rebatePrecent : 4
             * mallType : 0
             * category : 食品酒饮
             * categoryLeaf : 茶
             * sellAmounts : 1014
             * couponFinalPrice : 6.9
             * action : vipgift://com.xmiles.vipgift/main/buying/PanicBuyingActivity?tabId=60003&title=限时抢购
             * handPrice : 6.9
             * categoryId : 11
             * categoryLeafId : 94
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
             * endProgress : 0.13
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
            private double fanliAmount;
            private int mallRebateIntegral;
            private double mallRebateMoney;
            private int rebatePrecent;
            private int mallType;
            private String category;
            private String categoryLeaf;
            private String sellAmounts;
            private double couponFinalPrice;
            private String action;
            private double handPrice;
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
            private String endProgress;
            private String favoritesTitle;
            private String shortUrl;
            private String aWordDesc;
            private String productDesc;

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

            public double getFanliAmount() {
                return fanliAmount;
            }

            public void setFanliAmount(double fanliAmount) {
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

            public String getEndProgress() {
                return endProgress;
            }

            public void setEndProgress(String endProgress) {
                this.endProgress = endProgress;
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
        }
    }
}
