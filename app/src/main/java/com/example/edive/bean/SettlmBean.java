package com.example.edive.bean;

public class SettlmBean {
    /**
     * code : 200
     * message : 操作成功
     * result : {"id":2,"productName":"学证1","location":"巴厘岛","title":"复读机啊看来大家法拉利放大潜水","image":"https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg","price":666,"coachName":"王海成","coachIcon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","coachId":2,"coachGrade":"8888","startTime":"2019-11-30 13:17:00","endTime":"2020-01-18 11:05:13","availableIntegral":null}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 2
         * productName : 学证1
         * location : 巴厘岛
         * title : 复读机啊看来大家法拉利放大潜水
         * image : https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg
         * price : 666.0
         * coachName : 王海成
         * coachIcon : http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg
         * coachId : 2
         * coachGrade : 8888
         * startTime : 2019-11-30 13:17:00
         * endTime : 2020-01-18 11:05:13
         * availableIntegral : null
         */

        private int id;
        private String productName;
        private String location;
        private String title;
        private String image;
        private double price;
        private String coachName;
        private String coachIcon;
        private int coachId;
        private String coachGrade;
        private String startTime;
        private String endTime;
        private Object availableIntegral;
        private String nickName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCoachName() {
            return coachName;
        }

        public void setCoachName(String coachName) {
            this.coachName = coachName;
        }

        public String getCoachIcon() {
            return coachIcon;
        }

        public void setCoachIcon(String coachIcon) {
            this.coachIcon = coachIcon;
        }

        public int getCoachId() {
            return coachId;
        }

        public void setCoachId(int coachId) {
            this.coachId = coachId;
        }

        public String getCoachGrade() {
            return coachGrade;
        }

        public void setCoachGrade(String coachGrade) {
            this.coachGrade = coachGrade;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Object getAvailableIntegral() {
            return availableIntegral;
        }

        public void setAvailableIntegral(Object availableIntegral) {
            this.availableIntegral = availableIntegral;
        }
    }
}
