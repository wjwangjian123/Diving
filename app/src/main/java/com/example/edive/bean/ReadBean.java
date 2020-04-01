package com.example.edive.bean;

import java.util.List;

public class ReadBean {
    /**
     * code : 200
     * message : 操作成功
     * result : [{"messageType":1,"isRead":0,"count":0},{"messageType":2,"isRead":1,"count":47},{"messageType":3,"isRead":1,"count":2}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * messageType : 1
         * isRead : 0
         * count : 0
         */

        private int messageType;
        private int isRead;
        private int count;

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
