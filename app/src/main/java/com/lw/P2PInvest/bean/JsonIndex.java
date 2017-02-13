package com.lw.P2PInvest.bean;

import java.util.List;

/**
 * Created by lw on 2017/2/12.
 */

public class JsonIndex {

    /**
     * imageArr : [{"ID":"1","IMAPAURL":"http://gwop.xtrich.com/xtApp/","IMAURL":"http://192.168.191.1:8080/P2PInvest"},{"ID":"2","IMAPAURL":"http://gwop.xtrich.com/xtApp/","IMAURL":"http://192.168.191.1:8080/P2PInvest"},{"ID":"3","IMAPAURL":"http://gwop.xtrich.com/xtApp/","IMAURL":"http://192.168.191.1:8080/P2PInvest"},{"ID":"5","IMAPAURL":"http://gwop.xtrich.com/xtApp/","IMAURL":"http://192.168.191.1:8080/P2PInvest"}]
     * proInfo : {"id":"1","memberNum":"100","minTouMoney":"100","money":"10","name":"��Ȳʺ����ּƻ�","progress":"90","suodingDays":"30","yearRate":"8.00"}
     */

    private ProInfoBean proInfo;
    private List<ImageArrBean> imageArr;

    public ProInfoBean getProInfo() {
        return proInfo;
    }

    public void setProInfo(ProInfoBean proInfo) {
        this.proInfo = proInfo;
    }

    public List<ImageArrBean> getImageArr() {
        return imageArr;
    }

    public void setImageArr(List<ImageArrBean> imageArr) {
        this.imageArr = imageArr;
    }

    public static class ProInfoBean {
        /**
         * id : 1
         * memberNum : 100
         * minTouMoney : 100
         * money : 10
         * name : ��Ȳʺ����ּƻ�
         * progress : 90
         * suodingDays : 30
         * yearRate : 8.00
         */

        private String id;
        private String memberNum;
        private String minTouMoney;
        private String money;
        private String name;
        private String progress;
        private String suodingDays;
        private String yearRate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(String memberNum) {
            this.memberNum = memberNum;
        }

        public String getMinTouMoney() {
            return minTouMoney;
        }

        public void setMinTouMoney(String minTouMoney) {
            this.minTouMoney = minTouMoney;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getSuodingDays() {
            return suodingDays;
        }

        public void setSuodingDays(String suodingDays) {
            this.suodingDays = suodingDays;
        }

        public String getYearRate() {
            return yearRate;
        }

        public void setYearRate(String yearRate) {
            this.yearRate = yearRate;
        }
    }

    public static class ImageArrBean {
        /**
         * ID : 1
         * IMAPAURL : http://gwop.xtrich.com/xtApp/
         * IMAURL : http://192.168.191.1:8080/P2PInvest
         */

        private String ID;
        private String IMAPAURL;
        private String IMAURL;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getIMAPAURL() {
            return IMAPAURL;
        }

        public void setIMAPAURL(String IMAPAURL) {
            this.IMAPAURL = IMAPAURL;
        }

        public String getIMAURL() {
            return IMAURL;
        }

        public void setIMAURL(String IMAURL) {
            this.IMAURL = IMAURL;
        }
    }
}
