package com.example.edive.bean;

import com.zyyoona7.wheel.IWheelEntity;

import java.util.List;

public class LocationBean implements IWheelEntity {
    /**
     * code : 200
     * message : 操作成功
     * result : [{"id":1,"address":"马来西亚"},{"id":2,"address":"巴厘岛"}]
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

    @Override
    public String getWheelText() {
        return null;
    }

    public static class ResultBean implements IWheelEntity{
        /**
         * id : 1
         * address : 马来西亚
         */

        private int id;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String getWheelText() {
            return address == null ? "" : address;
        }
    }

}
