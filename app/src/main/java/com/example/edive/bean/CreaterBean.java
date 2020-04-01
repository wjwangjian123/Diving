package com.example.edive.bean;

import java.util.List;

public class CreaterBean {
    /**
     * code : 200
     * message : 操作成功
     * data : [{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":2,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"AOWD","certificateLevel":"2"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":3,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"EFA","certificateLevel":"3"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":4,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"RED","certificateLevel":"4"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":5,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"SC","certificateLevel":"5"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":6,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"MSD","certificateLevel":"6"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":7,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"DM","certificateLevel":"7"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":8,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"OWS","certificateLevel":"8"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":9,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"MSDT","certificateLevel":"9"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":10,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"IDCSI","certificateLevel":"10"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":11,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"MI","certificateLevel":"11"},{"id":null,"pictures":null,"userId":null,"status":null,"certificateId":12,"createTime":null,"adopTime":null,"operator":null,"reason":null,"certificateName":"CD","certificateLevel":"12"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : null
         * pictures : null
         * userId : null
         * status : null
         * certificateId : 2
         * createTime : null
         * adopTime : null
         * operator : null
         * reason : null
         * certificateName : AOWD
         * certificateLevel : 2
         */

        private int id;
        private String pictures;
        private int userId;
        private String  status;
        private int certificateId;
        private String createTime;
        private String adopTime;
        private String operator;
        private String reason;
        private String certificateName;
        private String certificateLevel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPictures() {
            return pictures;
        }

        public void setPictures(String pictures) {
            this.pictures = pictures;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getCertificateId() {
            return certificateId;
        }

        public void setCertificateId(int certificateId) {
            this.certificateId = certificateId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getAdopTime() {
            return adopTime;
        }

        public void setAdopTime(String adopTime) {
            this.adopTime = adopTime;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getCertificateName() {
            return certificateName;
        }

        public void setCertificateName(String certificateName) {
            this.certificateName = certificateName;
        }

        public String getCertificateLevel() {
            return certificateLevel;
        }

        public void setCertificateLevel(String certificateLevel) {
            this.certificateLevel = certificateLevel;
        }
    }

}
