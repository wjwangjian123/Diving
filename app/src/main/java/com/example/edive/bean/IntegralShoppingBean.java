package com.example.edive.bean;

import java.util.List;

public class IntegralShoppingBean {
    /**
     * code : 200
     * message : 操作成功
     * result : {"productId":1,"productPic":"http://192.168.0.246/image/1575368930200_95882950.jpg","productIntegral":1000,"productName":"花花公子男士长袖t恤圆领卫衣秋季纯棉男装潮流打底衫印花上衣服","productSpec":[{"value":"x","key":"尺码"},{"value":"蓝","key":"颜色"}],"productQuantity":1,"totalIntegral":1000,"availableIntegral":46545445}
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
         * productId : 1
         * productPic : http://192.168.0.246/image/1575368930200_95882950.jpg
         * productIntegral : 1000
         * productName : 花花公子男士长袖t恤圆领卫衣秋季纯棉男装潮流打底衫印花上衣服
         * productSpec : [{"value":"x","key":"尺码"},{"value":"蓝","key":"颜色"}]
         * productQuantity : 1
         * totalIntegral : 1000
         * availableIntegral : 46545445
         */

        private int productId;
        private String productPic;
        private int productIntegral;
        private String productName;
        private int productQuantity;
        private int totalIntegral;
        private int availableIntegral;
        private List<ProductSpecBean> productSpec;

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

        public int getProductIntegral() {
            return productIntegral;
        }

        public void setProductIntegral(int productIntegral) {
            this.productIntegral = productIntegral;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
        }

        public int getTotalIntegral() {
            return totalIntegral;
        }

        public void setTotalIntegral(int totalIntegral) {
            this.totalIntegral = totalIntegral;
        }

        public int getAvailableIntegral() {
            return availableIntegral;
        }

        public void setAvailableIntegral(int availableIntegral) {
            this.availableIntegral = availableIntegral;
        }

        public List<ProductSpecBean> getProductSpec() {
            return productSpec;
        }

        public void setProductSpec(List<ProductSpecBean> productSpec) {
            this.productSpec = productSpec;
        }

        public static class ProductSpecBean {
            /**
             * value : x
             * key : 尺码
             */

            private String value;
            private String key;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }
}
