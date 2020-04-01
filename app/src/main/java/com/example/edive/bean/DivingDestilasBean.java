package com.example.edive.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DivingDestilasBean implements Parcelable, Serializable {
    /**
     * code : 200
     * message : 操作成功
     * result : {"id":1,"userId":1,"addressId":2,"title":"一起愉快的潜水,来玩啊","price":666,"productType":2,"location":"巴厘岛","lengthPlay":"2天8晚","costIncludes":"吃喝拉撒","costNotIncludes":"坐车","purchaseNotes":"保管好自身财务","productDescription":"绝对让你满意","image":"http://pic23.photophoto.cn/20120614/0008020246371439_b.jpg","contentArrangement":"[{\"date\":\"第一天\",\"message\":\"啥也不做\"},{\"date\":\"第二天\",\"message\":\"坐车回家\"}]","sales":16,"certificateId":1,"startTime":"2019-11-30 13:17:00","endTime":"2020-01-18 11:05:13","umsCoachVo":{"id":1,"coachName":"17731271980","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"9999","personalProfile":null,"invitationCode":"AAAAAA"}}
     */

    private int code;
    private String message;
    private ResultBean result;

    protected DivingDestilasBean(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<DivingDestilasBean> CREATOR = new Creator<DivingDestilasBean>() {
        @Override
        public DivingDestilasBean createFromParcel(Parcel in) {
            return new DivingDestilasBean(in);
        }

        @Override
        public DivingDestilasBean[] newArray(int size) {
            return new DivingDestilasBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
    }

    public static class ResultBean implements Parcelable,Serializable{
        /**
         * id : 1
         * userId : 1
         * addressId : 2
         * title : 一起愉快的潜水,来玩啊
         * price : 666.0
         * productType : 2
         * location : 巴厘岛
         * lengthPlay : 2天8晚
         * costIncludes : 吃喝拉撒
         * costNotIncludes : 坐车
         * purchaseNotes : 保管好自身财务
         * productDescription : 绝对让你满意
         * image : http://pic23.photophoto.cn/20120614/0008020246371439_b.jpg
         * contentArrangement : [{"date":"第一天","message":"啥也不做"},{"date":"第二天","message":"坐车回家"}]
         * sales : 16
         * certificateId : 1
         * startTime : 2019-11-30 13:17:00
         * endTime : 2020-01-18 11:05:13
         * umsCoachVo : {"id":1,"coachName":"17731271980","icon":"http://img1.imgtn.bdimg.com/it/u=1411728850,1869975885&fm=26&gp=0.jpg","phone":"18630455265","coachGrade":"9999","personalProfile":null,"invitationCode":"AAAAAA"}
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
        private UmsCoachVoBean umsCoachVo;
        private int status;

        protected ResultBean(Parcel in) {
            id = in.readInt();
            userId = in.readInt();
            addressId = in.readInt();
            title = in.readString();
            price = in.readDouble();
            productType = in.readInt();
            location = in.readString();
            lengthPlay = in.readString();
            costIncludes = in.readString();
            costNotIncludes = in.readString();
            purchaseNotes = in.readString();
            productDescription = in.readString();
            image = in.readString();
            contentArrangement = in.readString();
            sales = in.readInt();
            certificateId = in.readInt();
            startTime = in.readString();
            endTime = in.readString();
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel in) {
                return new ResultBean(in);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public UmsCoachVoBean getUmsCoachVo() {
            return umsCoachVo;
        }

        public void setUmsCoachVo(UmsCoachVoBean umsCoachVo) {
            this.umsCoachVo = umsCoachVo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(userId);
            dest.writeInt(addressId);
            dest.writeString(title);
            dest.writeDouble(price);
            dest.writeInt(productType);
            dest.writeString(location);
            dest.writeString(lengthPlay);
            dest.writeString(costIncludes);
            dest.writeString(costNotIncludes);
            dest.writeString(purchaseNotes);
            dest.writeString(productDescription);
            dest.writeString(image);
            dest.writeString(contentArrangement);
            dest.writeInt(sales);
            dest.writeInt(certificateId);
            dest.writeString(startTime);
            dest.writeString(endTime);
        }

        public static class UmsCoachVoBean implements Parcelable,Serializable{
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

            protected UmsCoachVoBean(Parcel in) {
                id = in.readInt();
                coachName = in.readString();
                icon = in.readString();
                phone = in.readString();
                coachGrade = in.readString();
                invitationCode = in.readString();
            }

            public static final Creator<UmsCoachVoBean> CREATOR = new Creator<UmsCoachVoBean>() {
                @Override
                public UmsCoachVoBean createFromParcel(Parcel in) {
                    return new UmsCoachVoBean(in);
                }

                @Override
                public UmsCoachVoBean[] newArray(int size) {
                    return new UmsCoachVoBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(coachName);
                dest.writeString(icon);
                dest.writeString(phone);
                dest.writeString(coachGrade);
                dest.writeString(invitationCode);
            }
        }
    }
}
