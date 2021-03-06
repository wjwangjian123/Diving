package com.example.edive.bean;

import java.util.List;

public class Integralfishbean {
    /**
     * code : 200
     * message : 操作成功
     * result : {"total":1,"list":[{"orderId":153,"orderItemId":null,"orderSn":"1202861568219983872","userName":null,"userIcon":null,"userId":null,"coachGrade":null,"phone":null,"totalAmount":0,"payAmount":0,"integrationAmount":130,"payType":3,"paymentTime":"2019-12-06 16:05:21","status":3,"productId":2,"productPic":"https://gd3.alicdn.com/imgextra/i3/2150512261/O1CN018881JB1SZa0YX1Din_!!2150512261.jpg","productName":"台湾V.DIVE专业自由潜水长脚蹼","productTitle":null,"productPrice":13000,"productType":4,"createdTime":"2019-12-06 16:05:21","endTime":"2019-12-09 16:41:59","closeTime":null,"spec":"[{\"value\":\"s\",\"key\":\"尺码\"},{\"value\":\"蓝\",\"key\":\"颜色\"}]","sizeSpec":"s","productQuantity":1,"orderType":2,"realAmount":0,"returnStatus":null}],"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 1
         * list : [{"orderId":153,"orderItemId":null,"orderSn":"1202861568219983872","userName":null,"userIcon":null,"userId":null,"coachGrade":null,"phone":null,"totalAmount":0,"payAmount":0,"integrationAmount":130,"payType":3,"paymentTime":"2019-12-06 16:05:21","status":3,"productId":2,"productPic":"https://gd3.alicdn.com/imgextra/i3/2150512261/O1CN018881JB1SZa0YX1Din_!!2150512261.jpg","productName":"台湾V.DIVE专业自由潜水长脚蹼","productTitle":null,"productPrice":13000,"productType":4,"createdTime":"2019-12-06 16:05:21","endTime":"2019-12-09 16:41:59","closeTime":null,"spec":"[{\"value\":\"s\",\"key\":\"尺码\"},{\"value\":\"蓝\",\"key\":\"颜色\"}]","sizeSpec":"s","productQuantity":1,"orderType":2,"realAmount":0,"returnStatus":null}]
         * pageNum : 1
         * pageSize : 10
         * size : 1
         * startRow : 1
         * endRow : 1
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * orderId : 153
             * orderItemId : null
             * orderSn : 1202861568219983872
             * userName : null
             * userIcon : null
             * userId : null
             * coachGrade : null
             * phone : null
             * totalAmount : 0.0
             * payAmount : 0.0
             * integrationAmount : 130.0
             * payType : 3
             * paymentTime : 2019-12-06 16:05:21
             * status : 3
             * productId : 2
             * productPic : https://gd3.alicdn.com/imgextra/i3/2150512261/O1CN018881JB1SZa0YX1Din_!!2150512261.jpg
             * productName : 台湾V.DIVE专业自由潜水长脚蹼
             * productTitle : null
             * productPrice : 13000.0
             * productType : 4
             * createdTime : 2019-12-06 16:05:21
             * endTime : 2019-12-09 16:41:59
             * closeTime : null
             * spec : [{"value":"s","key":"尺码"},{"value":"蓝","key":"颜色"}]
             * sizeSpec : s
             * productQuantity : 1
             * orderType : 2
             * realAmount : 0.0
             * returnStatus : null
             */

            private int orderId;
            private Object orderItemId;
            private String orderSn;
            private Object userName;
            private Object userIcon;
            private Object userId;
            private Object coachGrade;
            private Object phone;
            private double totalAmount;
            private double payAmount;
            private double integrationAmount;
            private int payType;
            private String paymentTime;
            private int status;
            private int productId;
            private String productPic;
            private String productName;
            private Object productTitle;
            private double productPrice;
            private int productType;
            private String createdTime;
            private String endTime;
            private Object closeTime;
            private String spec;
            private String sizeSpec;
            private int productQuantity;
            private int orderType;
            private double realAmount;
            private Object returnStatus;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public Object getOrderItemId() {
                return orderItemId;
            }

            public void setOrderItemId(Object orderItemId) {
                this.orderItemId = orderItemId;
            }

            public String getOrderSn() {
                return orderSn;
            }

            public void setOrderSn(String orderSn) {
                this.orderSn = orderSn;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public Object getUserIcon() {
                return userIcon;
            }

            public void setUserIcon(Object userIcon) {
                this.userIcon = userIcon;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public Object getCoachGrade() {
                return coachGrade;
            }

            public void setCoachGrade(Object coachGrade) {
                this.coachGrade = coachGrade;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
            }

            public double getPayAmount() {
                return payAmount;
            }

            public void setPayAmount(double payAmount) {
                this.payAmount = payAmount;
            }

            public double getIntegrationAmount() {
                return integrationAmount;
            }

            public void setIntegrationAmount(double integrationAmount) {
                this.integrationAmount = integrationAmount;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public String getPaymentTime() {
                return paymentTime;
            }

            public void setPaymentTime(String paymentTime) {
                this.paymentTime = paymentTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductPic() {
                return productPic;
            }

            public void setProductPic(String productPic) {
                this.productPic = productPic;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public Object getProductTitle() {
                return productTitle;
            }

            public void setProductTitle(Object productTitle) {
                this.productTitle = productTitle;
            }

            public double getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }

            public int getProductType() {
                return productType;
            }

            public void setProductType(int productType) {
                this.productType = productType;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public Object getCloseTime() {
                return closeTime;
            }

            public void setCloseTime(Object closeTime) {
                this.closeTime = closeTime;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getSizeSpec() {
                return sizeSpec;
            }

            public void setSizeSpec(String sizeSpec) {
                this.sizeSpec = sizeSpec;
            }

            public int getProductQuantity() {
                return productQuantity;
            }

            public void setProductQuantity(int productQuantity) {
                this.productQuantity = productQuantity;
            }

            public int getOrderType() {
                return orderType;
            }

            public void setOrderType(int orderType) {
                this.orderType = orderType;
            }

            public double getRealAmount() {
                return realAmount;
            }

            public void setRealAmount(double realAmount) {
                this.realAmount = realAmount;
            }

            public Object getReturnStatus() {
                return returnStatus;
            }

            public void setReturnStatus(Object returnStatus) {
                this.returnStatus = returnStatus;
            }
        }
    }
}
