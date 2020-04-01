package com.example.edive.bean;

import java.util.List;

public class ApplyBean {
    /**
     * code : 200
     * message : 操作成功
     * result : [{"id":1,"name":"质量问题","sort":0,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":12,"name":"发票问题","sort":0,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":13,"name":"其他问题","sort":0,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":14,"name":"物流问题","sort":0,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":15,"name":"售后问题","sort":0,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":16,"name":"就是想退货","sort":0,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":2,"name":"尺码太大","sort":1,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":3,"name":"颜色不喜欢","sort":1,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":4,"name":"7天无理由退货","sort":1,"status":1,"createTime":"2019-11-22 10:00:45"},{"id":5,"name":"价格问题","sort":1,"status":1,"createTime":"2019-11-22 10:00:45"}]
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
         * id : 1
         * name : 质量问题
         * sort : 0
         * status : 1
         * createTime : 2019-11-22 10:00:45
         */

        private int id;
        private String name;
        private int sort;
        private int status;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
