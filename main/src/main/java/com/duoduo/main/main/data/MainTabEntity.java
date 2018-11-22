package com.duoduo.main.main.data;

import com.duoduo.main.base.data.ResultEntity;

import java.util.List;

/**
 * 首页Tab数据bean
 */
public class MainTabEntity {


    /**
     * tabListVersion : 540
     * tabList : [{"id":1999,"tabName":"首页","sorted":1,"status":1,"isDel":0,"platformIosVersion":4,"platformAndroidVersion":4,"tabImg":"https://imgs.gmilesquan.com//ZlErpcRl.png","tabSelectedImg":"https://imgs.gmilesquan.com//ZPwHBPgp.png","tabImgV2":"https://imgs.gmilesquan.com//BnIyOBJH.png","tabSelectedImgV2":"https://imgs.gmilesquan.com//eSqujkvW.png","tabImgNewFemale":"https://imgs.gmilesquan.com//glNiWYdR.png","tabSelectedImgNewFemale":"https://imgs.gmilesquan.com//iyZQCULU.png","tabIosImg":"https://imgs.gmilesquan.com//lWwlKySS.png","tabIosSelectedImg":"https://imgs.gmilesquan.com//uDjfLJZJ.png","tabImgNewFemaleIOS":"https://imgs.gmilesquan.com//RLcVRGJQ.png","tabSelectedImgNewFemaleIOS":"https://imgs.gmilesquan.com//zvjXNRQv.png","tabType":4,"createTime":"2018-06-01 11:23:39","isCategoryTab":1,"actionBarTitleColor":"","isFlashSale":0,"redPointShow":0,"rpShowTimes":0,"searchBarBgImg":""},{"id":2010,"tabName":"分类","sorted":1,"status":1,"isDel":0,"platformIosVersion":4,"platformAndroidVersion":4,"tabImg":"https://imgs.gmilesquan.com//BQRFGHGY.png","tabSelectedImg":"https://imgs.gmilesquan.com//vMpBEEEm.png","tabImgV2":"https://imgs.gmilesquan.com//YEMvumUA.png","tabSelectedImgV2":"https://imgs.gmilesquan.com//Ayisqhrp.png","tabImgNewFemale":"https://imgs.gmilesquan.com//GzmXAPUZ.png","tabSelectedImgNewFemale":"https://imgs.gmilesquan.com//lAgrugql.png","tabIosImg":"https://imgs.gmilesquan.com//GvObDUfo.png","tabIosSelectedImg":"https://imgs.gmilesquan.com//puaMHGOC.png","tabImgNewFemaleIOS":"https://imgs.gmilesquan.com//vBLYPyrj.png","tabSelectedImgNewFemaleIOS":"https://imgs.gmilesquan.com//ptPpaWIi.png","tabType":4,"createTime":"2018-06-01 11:23:39","isCategoryTab":0,"versionNotShow":"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34","actionBarTitleColor":"","isFlashSale":0,"redPointShow":0,"rpShowTimes":0,"searchBarBgImg":""},{"id":2011,"tabName":"收藏","sorted":1,"status":1,"isDel":0,"platformIosVersion":4,"platformAndroidVersion":4,"tabImg":"https://imgs.gmilesquan.com//exynTuIW.png","tabSelectedImg":"https://imgs.gmilesquan.com//WOvbdTGC.png","tabImgV2":"https://imgs.gmilesquan.com//sXpLYalm.png","tabSelectedImgV2":"https://imgs.gmilesquan.com//CoyJdUJq.png","tabImgNewFemale":"https://imgs.gmilesquan.com//sSYTtKMP.png","tabSelectedImgNewFemale":"https://imgs.gmilesquan.com//eDqCcWOo.png","tabIosImg":"https://imgs.gmilesquan.com//YqdKRVPK.png","tabIosSelectedImg":"https://imgs.gmilesquan.com//WZtUOEOA.png","tabImgNewFemaleIOS":"https://imgs.gmilesquan.com//LSkCgDqH.png","tabSelectedImgNewFemaleIOS":"https://imgs.gmilesquan.com//fNdCUREF.png","tabType":4,"createTime":"2018-06-01 11:23:39","isCategoryTab":0,"versionNotShow":"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34","actionBarTitleColor":"","isFlashSale":0,"redPointShow":0,"rpShowTimes":0,"searchBarBgImg":""},{"id":1002,"tabName":"我的","sorted":4,"status":1,"isDel":0,"platformIosVersion":0,"platformAndroidVersion":0,"tabImg":"https://imgs.gmilesquan.com//lnfHnuzB.png","tabSelectedImg":"https://imgs.gmilesquan.com//MPSgfQlN.png","tabImgV2":"https://imgs.gmilesquan.com//qBdkBynI.png","tabSelectedImgV2":"https://imgs.gmilesquan.com//Bgakchza.png","tabImgNewFemale":"https://imgs.gmilesquan.com//KzrEfPal.png","tabSelectedImgNewFemale":"https://imgs.gmilesquan.com//aqqDpmkR.png","tabIosImg":"https://imgs.gmilesquan.com//HuXZrIqG.png","tabIosSelectedImg":"https://imgs.gmilesquan.com//SIXqMovK.png","tabImgNewFemaleIOS":"https://imgs.gmilesquan.com//OrxPSExP.png","tabSelectedImgNewFemaleIOS":"https://imgs.gmilesquan.com//FFYgsCxd.png","tabType":1,"isCategoryTab":0,"isFlashSale":0,"redPointShow":0,"rpShowTimes":0}]
     * noLoginIconImg : https://imgs.gmilesquan.com//gXnxabIN.png
     * currentTime : 2018-11-05 13:59:57
     * isFlashSalePage : 0
     * isNewUser : 0
     * result : {"status":1}
     * costTime : 2
     */

    private int tabListVersion;
    private String noLoginIconImg;
    private String currentTime;
    private int isFlashSalePage;
    private int isNewUser;
    private ResultEntity result;
    private int costTime;
    private List<TabListEntity> tabList;

    public int getTabListVersion() {
        return tabListVersion;
    }

    public void setTabListVersion(int tabListVersion) {
        this.tabListVersion = tabListVersion;
    }

    public String getNoLoginIconImg() {
        return noLoginIconImg;
    }

    public void setNoLoginIconImg(String noLoginIconImg) {
        this.noLoginIconImg = noLoginIconImg;
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

    public List<TabListEntity> getTabList() {
        return tabList;
    }

    public void setTabList(List<TabListEntity> tabList) {
        this.tabList = tabList;
    }

    public static class TabListEntity {
        /**
         * id : 1999
         * tabName : 首页
         * sorted : 1
         * status : 1
         * isDel : 0
         * platformIosVersion : 4
         * platformAndroidVersion : 4
         * tabImg : https://imgs.gmilesquan.com//ZlErpcRl.png
         * tabSelectedImg : https://imgs.gmilesquan.com//ZPwHBPgp.png
         * tabImgV2 : https://imgs.gmilesquan.com//BnIyOBJH.png
         * tabSelectedImgV2 : https://imgs.gmilesquan.com//eSqujkvW.png
         * tabImgNewFemale : https://imgs.gmilesquan.com//glNiWYdR.png
         * tabSelectedImgNewFemale : https://imgs.gmilesquan.com//iyZQCULU.png
         * tabIosImg : https://imgs.gmilesquan.com//lWwlKySS.png
         * tabIosSelectedImg : https://imgs.gmilesquan.com//uDjfLJZJ.png
         * tabImgNewFemaleIOS : https://imgs.gmilesquan.com//RLcVRGJQ.png
         * tabSelectedImgNewFemaleIOS : https://imgs.gmilesquan.com//zvjXNRQv.png
         * tabType : 4
         * createTime : 2018-06-01 11:23:39
         * isCategoryTab : 1
         * actionBarTitleColor :
         * isFlashSale : 0
         * redPointShow : 0
         * rpShowTimes : 0
         * searchBarBgImg :
         * versionNotShow : 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34
         */

        private int id;
        private String tabName;
        private int sorted;
        private int status;
        private int isDel;
        private int platformIosVersion;
        private int platformAndroidVersion;
        private String tabImg;
        private String tabSelectedImg;
        private String tabImgV2;
        private String tabSelectedImgV2;
        private String tabImgNewFemale;
        private String tabSelectedImgNewFemale;
        private String tabIosImg;
        private String tabIosSelectedImg;
        private String tabImgNewFemaleIOS;
        private String tabSelectedImgNewFemaleIOS;
        private int tabType;
        private String createTime;
        private int isCategoryTab;
        private String actionBarTitleColor;
        private int isFlashSale;
        private int redPointShow;
        private int rpShowTimes;
        private String searchBarBgImg;
        private String versionNotShow;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        public int getSorted() {
            return sorted;
        }

        public void setSorted(int sorted) {
            this.sorted = sorted;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getPlatformIosVersion() {
            return platformIosVersion;
        }

        public void setPlatformIosVersion(int platformIosVersion) {
            this.platformIosVersion = platformIosVersion;
        }

        public int getPlatformAndroidVersion() {
            return platformAndroidVersion;
        }

        public void setPlatformAndroidVersion(int platformAndroidVersion) {
            this.platformAndroidVersion = platformAndroidVersion;
        }

        public String getTabImg() {
            return tabImg;
        }

        public void setTabImg(String tabImg) {
            this.tabImg = tabImg;
        }

        public String getTabSelectedImg() {
            return tabSelectedImg;
        }

        public void setTabSelectedImg(String tabSelectedImg) {
            this.tabSelectedImg = tabSelectedImg;
        }

        public String getTabImgV2() {
            return tabImgV2;
        }

        public void setTabImgV2(String tabImgV2) {
            this.tabImgV2 = tabImgV2;
        }

        public String getTabSelectedImgV2() {
            return tabSelectedImgV2;
        }

        public void setTabSelectedImgV2(String tabSelectedImgV2) {
            this.tabSelectedImgV2 = tabSelectedImgV2;
        }

        public String getTabImgNewFemale() {
            return tabImgNewFemale;
        }

        public void setTabImgNewFemale(String tabImgNewFemale) {
            this.tabImgNewFemale = tabImgNewFemale;
        }

        public String getTabSelectedImgNewFemale() {
            return tabSelectedImgNewFemale;
        }

        public void setTabSelectedImgNewFemale(String tabSelectedImgNewFemale) {
            this.tabSelectedImgNewFemale = tabSelectedImgNewFemale;
        }

        public String getTabIosImg() {
            return tabIosImg;
        }

        public void setTabIosImg(String tabIosImg) {
            this.tabIosImg = tabIosImg;
        }

        public String getTabIosSelectedImg() {
            return tabIosSelectedImg;
        }

        public void setTabIosSelectedImg(String tabIosSelectedImg) {
            this.tabIosSelectedImg = tabIosSelectedImg;
        }

        public String getTabImgNewFemaleIOS() {
            return tabImgNewFemaleIOS;
        }

        public void setTabImgNewFemaleIOS(String tabImgNewFemaleIOS) {
            this.tabImgNewFemaleIOS = tabImgNewFemaleIOS;
        }

        public String getTabSelectedImgNewFemaleIOS() {
            return tabSelectedImgNewFemaleIOS;
        }

        public void setTabSelectedImgNewFemaleIOS(String tabSelectedImgNewFemaleIOS) {
            this.tabSelectedImgNewFemaleIOS = tabSelectedImgNewFemaleIOS;
        }

        public int getTabType() {
            return tabType;
        }

        public void setTabType(int tabType) {
            this.tabType = tabType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIsCategoryTab() {
            return isCategoryTab;
        }

        public void setIsCategoryTab(int isCategoryTab) {
            this.isCategoryTab = isCategoryTab;
        }

        public String getActionBarTitleColor() {
            return actionBarTitleColor;
        }

        public void setActionBarTitleColor(String actionBarTitleColor) {
            this.actionBarTitleColor = actionBarTitleColor;
        }

        public int getIsFlashSale() {
            return isFlashSale;
        }

        public void setIsFlashSale(int isFlashSale) {
            this.isFlashSale = isFlashSale;
        }

        public int getRedPointShow() {
            return redPointShow;
        }

        public void setRedPointShow(int redPointShow) {
            this.redPointShow = redPointShow;
        }

        public int getRpShowTimes() {
            return rpShowTimes;
        }

        public void setRpShowTimes(int rpShowTimes) {
            this.rpShowTimes = rpShowTimes;
        }

        public String getSearchBarBgImg() {
            return searchBarBgImg;
        }

        public void setSearchBarBgImg(String searchBarBgImg) {
            this.searchBarBgImg = searchBarBgImg;
        }

        public String getVersionNotShow() {
            return versionNotShow;
        }

        public void setVersionNotShow(String versionNotShow) {
            this.versionNotShow = versionNotShow;
        }
    }
}
