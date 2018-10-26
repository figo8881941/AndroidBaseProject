package com.duoduo.main.classify.data;

import java.util.List;

/**
 * 分类Tab数据单元
 */
public class ClassifyTabDataBean {


    /**
     * categoryList : [{"id":1,"categoryName":"女装","words":"女装/女士精品","img":"https://imgs.gmilesquan.com//ndwflerT.png","isDel":0,"updatetime":"2018-10-20 10:20:19","isRecommend":1,"sorted":0,"topicId":2031,"topicTitleImg":"https://imgs.gmilesquan.com//QkxNTVKm.png"}]
     * leafCategoryList : [{"id":22,"categoryName":"卫衣","pid":1,"words":"卫衣/绒衫","img":"https://imgs.gmilesquan.com//IZklmITg.png","isDel":0,"updatetime":"2018-10-10 23:21:52","isRecommend":0,"sorted":1}]
     * recommendFlag : 1
     * currentTime : 2018-10-26 16:59:03
     * isFlashSalePage : 0
     * isNewUser : 0
     * result : {"status":1}
     * costTime : 3
     */

    private int recommendFlag;
    private String currentTime;
    private int isFlashSalePage;
    private int isNewUser;
    private ResultEntity result;
    private int costTime;
    private List<CategoryListEntity> categoryList;
    private List<LeafCategoryListEntity> leafCategoryList;

    public int getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(int recommendFlag) {
        this.recommendFlag = recommendFlag;
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

    public List<CategoryListEntity> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListEntity> categoryList) {
        this.categoryList = categoryList;
    }

    public List<LeafCategoryListEntity> getLeafCategoryList() {
        return leafCategoryList;
    }

    public void setLeafCategoryList(List<LeafCategoryListEntity> leafCategoryList) {
        this.leafCategoryList = leafCategoryList;
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

    public static class CategoryListEntity {
        /**
         * id : 1
         * categoryName : 女装
         * words : 女装/女士精品
         * img : https://imgs.gmilesquan.com//ndwflerT.png
         * isDel : 0
         * updatetime : 2018-10-20 10:20:19
         * isRecommend : 1
         * sorted : 0
         * topicId : 2031
         * topicTitleImg : https://imgs.gmilesquan.com//QkxNTVKm.png
         */

        private int id;
        private String categoryName;
        private String words;
        private String img;
        private int isDel;
        private String updatetime;
        private int isRecommend;
        private int sorted;
        private int topicId;
        private String topicTitleImg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public int getSorted() {
            return sorted;
        }

        public void setSorted(int sorted) {
            this.sorted = sorted;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public String getTopicTitleImg() {
            return topicTitleImg;
        }

        public void setTopicTitleImg(String topicTitleImg) {
            this.topicTitleImg = topicTitleImg;
        }
    }

    public static class LeafCategoryListEntity {
        /**
         * id : 22
         * categoryName : 卫衣
         * pid : 1
         * words : 卫衣/绒衫
         * img : https://imgs.gmilesquan.com//IZklmITg.png
         * isDel : 0
         * updatetime : 2018-10-10 23:21:52
         * isRecommend : 0
         * sorted : 1
         */

        private int id;
        private String categoryName;
        private int pid;
        private String words;
        private String img;
        private int isDel;
        private String updatetime;
        private int isRecommend;
        private int sorted;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public int getSorted() {
            return sorted;
        }

        public void setSorted(int sorted) {
            this.sorted = sorted;
        }
    }
}
