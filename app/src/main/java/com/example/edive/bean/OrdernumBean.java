package com.example.edive.bean;

import java.util.List;

public class OrdernumBean {
    /**
     * code : 200
     * message : 操作成功
     * result : [{"count":0,"status":0},{"count":1,"status":1},{"count":0,"status":2},{"count":0,"status":5}]
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
         * count : 0
         * status : 0
         */

        private int count;
        private int status;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
