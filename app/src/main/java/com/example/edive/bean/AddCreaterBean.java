package com.example.edive.bean;

public class AddCreaterBean {
    /**
     * code : 200
     * message : 上传成功,请等待审核!
     * data : 1
     */

    private int code;
    private String message;
    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
