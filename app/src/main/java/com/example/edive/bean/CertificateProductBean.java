package com.example.edive.bean;

import java.util.List;

public class CertificateProductBean {
    /**
     * code : 200
     * message : 操作成功
     * result : {"id":5,"userId":1,"addressId":2,"title":"发达发达发达附件","price":666,"productType":1,"location":"巴厘岛","lengthPlay":"5天8晚","costIncludes":"吃喝拉撒","costNotIncludes":"坐车","purchaseNotes":"保管好自身财务","productDescription":"绝对让你满意","image":"https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg","contentArrangement":"[{\"date\":\"第一天\",\"message\":\"啥也不做\"},{\"date\":\"第二天\",\"message\":\"坐车回家\"}]","sales":0,"certificateId":1,"startTime":"2019-11-12 11:05:16","endTime":"2019-11-30 11:05:18","pmsCertificateVo":{"id":1,"englishName":"OPEN WATER DIVER","englishShorthand":"OWD","certificateLevel":"1","pic":"http://img0.imgtn.bdimg.com/it/u=3614539914,2228362630&fm=26&gp=0.jpg","introduction":"开放水域初级潜水员是最基本的潜水课程，受训学员可以学习到潜水技巧、与潜伴潜水时的潜水安全知识。完成本次课程即可获得OW证书！"},"umsCoachVos":[{"id":1,"coachName":"17731271980","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"9999","personalProfile":null,"invitationCode":"AAAAAA"},{"id":2,"coachName":"王海成","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"BBBBBB"},{"id":3,"coachName":"大脑袋","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"CCCCCC"},{"id":4,"coachName":"大头儿子","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"DDDDDD"},{"id":5,"coachName":"小头爸爸","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"EEEEEEE"}]}
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

    public static class  ResultBean {
        /**
         * id : 5
         * userId : 1
         * addressId : 2
         * title : 发达发达发达附件
         * price : 666.0
         * productType : 1
         * location : 巴厘岛
         * lengthPlay : 5天8晚
         * costIncludes : 吃喝拉撒
         * costNotIncludes : 坐车
         * purchaseNotes : 保管好自身财务
         * productDescription : 绝对让你满意
         * image : https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg
         * contentArrangement : [{"date":"第一天","message":"啥也不做"},{"date":"第二天","message":"坐车回家"}]
         * sales : 0
         * certificateId : 1
         * startTime : 2019-11-12 11:05:16
         * endTime : 2019-11-30 11:05:18
         * pmsCertificateVo : {"id":1,"englishName":"OPEN WATER DIVER","englishShorthand":"OWD","certificateLevel":"1","pic":"http://img0.imgtn.bdimg.com/it/u=3614539914,2228362630&fm=26&gp=0.jpg","introduction":"开放水域初级潜水员是最基本的潜水课程，受训学员可以学习到潜水技巧、与潜伴潜水时的潜水安全知识。完成本次课程即可获得OW证书！"}
         * umsCoachVos : [{"id":1,"coachName":"17731271980","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"9999","personalProfile":null,"invitationCode":"AAAAAA"},{"id":2,"coachName":"王海成","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"BBBBBB"},{"id":3,"coachName":"大脑袋","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"CCCCCC"},{"id":4,"coachName":"大头儿子","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"DDDDDD"},{"id":5,"coachName":"小头爸爸","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"8888","personalProfile":null,"invitationCode":"EEEEEEE"}]
         */

        private int id;
        private int userId;
        private int addressId;
        private String title;
        private double price;
        private int productType;
        private String location;
        private String lengthPlay;
        private String costIncludes;
        private String costNotIncludes;
        private String purchaseNotes;
        private String productDescription;
        private String image;
        private String contentArrangement;
        private int sales;
        private int certificateId;
        private String startTime;
        private String endTime;
        private int status;
        private PmsCertificateVoBean pmsCertificateVo;
        private List<UmsCoachVosBean> umsCoachVos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLengthPlay() {
            return lengthPlay;
        }

        public void setLengthPlay(String lengthPlay) {
            this.lengthPlay = lengthPlay;
        }

        public String getCostIncludes() {
            return costIncludes;
        }

        public void setCostIncludes(String costIncludes) {
            this.costIncludes = costIncludes;
        }

        public String getCostNotIncludes() {
            return costNotIncludes;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setCostNotIncludes(String costNotIncludes) {
            this.costNotIncludes = costNotIncludes;
        }

        public String getPurchaseNotes() {
            return purchaseNotes;
        }

        public void setPurchaseNotes(String purchaseNotes) {
            this.purchaseNotes = purchaseNotes;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContentArrangement() {
            return contentArrangement;
        }

        public void setContentArrangement(String contentArrangement) {
            this.contentArrangement = contentArrangement;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getCertificateId() {
            return certificateId;
        }

        public void setCertificateId(int certificateId) {
            this.certificateId = certificateId;
        }

        public String getStartTime() {
            return startTime;
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

        public PmsCertificateVoBean getPmsCertificateVo() {
            return pmsCertificateVo;
        }

        public void setPmsCertificateVo(PmsCertificateVoBean pmsCertificateVo) {
            this.pmsCertificateVo = pmsCertificateVo;
        }

        public List<UmsCoachVosBean> getUmsCoachVos() {
            return umsCoachVos;
        }

        public void setUmsCoachVos(List<UmsCoachVosBean> umsCoachVos) {
            this.umsCoachVos = umsCoachVos;
        }

        public static class PmsCertificateVoBean {
            /**
             * id : 1
             * englishName : OPEN WATER DIVER
             * englishShorthand : OWD
             * certificateLevel : 1
             * pic : http://img0.imgtn.bdimg.com/it/u=3614539914,2228362630&fm=26&gp=0.jpg
             * introduction : 开放水域初级潜水员是最基本的潜水课程，受训学员可以学习到潜水技巧、与潜伴潜水时的潜水安全知识。完成本次课程即可获得OW证书！
             */

            private int id;
            private String englishName;
            private String englishShorthand;
            private String certificateLevel;
            private String pic;
            private String introduction;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getEnglishName() {
                return englishName;
            }

            public void setEnglishName(String englishName) {
                this.englishName = englishName;
            }

            public String getEnglishShorthand() {
                return englishShorthand;
            }

            public void setEnglishShorthand(String englishShorthand) {
                this.englishShorthand = englishShorthand;
            }

            public String getCertificateLevel() {
                return certificateLevel;
            }

            public void setCertificateLevel(String certificateLevel) {
                this.certificateLevel = certificateLevel;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }
        }

        public static class UmsCoachVosBean {
            /**
             * id : 1
             * coachName : 17731271980
             * icon : http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg
             * phone : 18630455265
             * coachGrade : 9999
             * personalProfile : null
             * invitationCode : AAAAAA
             */

            private int id;
            private String coachName;
            private String icon;
            private String phone;
            private String coachGrade;
            private Object personalProfile;
            private String invitationCode;
            private String nickName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCoachName() {
                return coachName;
            }

            public void setCoachName(String coachName) {
                this.coachName = coachName;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCoachGrade() {
                return coachGrade;
            }

            public void setCoachGrade(String coachGrade) {
                this.coachGrade = coachGrade;
            }

            public Object getPersonalProfile() {
                return personalProfile;
            }

            public void setPersonalProfile(Object personalProfile) {
                this.personalProfile = personalProfile;
            }

            public String getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
        }
    }
}
