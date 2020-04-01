package com.example.edive.bean;

public class CourseProductBean {
    /**
     * code : 200
     * message : 操作成功
     * result : {"totalPayment":666,"orderSn":"201911210100000001"}
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
         * totalPayment : 666.0
         * orderSn : 201911210100000001
         */

        private double totalPayment;
        private String orderSn;

        public double getTotalPayment() {
            return totalPayment;
        }

        public void setTotalPayment(double totalPayment) {
            this.totalPayment = totalPayment;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }
    }
}
