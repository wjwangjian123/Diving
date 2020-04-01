package com.example.edive.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CertifitDetailsBean implements Serializable , Parcelable {
    /**
     * code : 200
     * message : 操作成功
     * result : {"pmsCertificate":{"id":2,"englishName":"ADVANCED OPEN WATER DIVER","englishShorthand":"AOWD","certificateLevel":"2","pic":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1919205464,1223035513&fm=26&gp=0.jpg","introduction":"AOW的水深限度是30米，并且可以做夜潜,本课程提供参加学员不同的专长潜水训练，如深潜、船潜、水中导航及夜潜...等，其中深潜和水中导航是必须选择的专长，除此两个专长以外，还需要选三个专长，通过这五个专长的训练后，方可拿到执照"},"pmsCourseProduct":{"id":5,"creator":null,"creatorId":null,"createdTime":null,"lastOperator":null,"lastOperatorId":null,"updateTime":null,"pageNum":null,"pageSize":null,"orderBy":null,"productName":"学证3","userId":1,"shopId":2,"shopName":null,"title":"发达发达发达附件","price":666,"productType":1,"location":"巴厘岛","lengthPlay":"5天8晚","isTimeLimit":0,"startTime":"2019-11-30 13:17:00","endTime":"2020-01-18 11:05:13","numberIsLimit":1,"numberLimit":8,"stock":7,"costIncludes":"吃喝拉撒","costNotIncludes":"坐车","purchaseNotes":"保管好自身财务","productDescription":"绝对让你满意","image":"https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg","contentArrangement":"[{\"date\":\"第一天\",\"message\":\"啥也不做\"},{\"date\":\"第二天\",\"message\":\"坐车回家\"}]","status":1,"approvalStatus":2,"sales":0,"sort":0,"certificateId":1,"addressId":2,"searchStartTime":null,"searchEndTime":null,"coachIcon":null,"coachName":null,"coachGrade":null}}
     */

    private int code;
    private String message;
    private ResultBean result;

    protected CertifitDetailsBean(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<CertifitDetailsBean> CREATOR = new Creator<CertifitDetailsBean>() {
        @Override
        public CertifitDetailsBean createFromParcel(Parcel in) {
            return new CertifitDetailsBean(in);
        }

        @Override
        public CertifitDetailsBean[] newArray(int size) {
            return new CertifitDetailsBean[size];
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

    public static class ResultBean implements Serializable,Parcelable{
        /**
         * pmsCertificate : {"id":2,"englishName":"ADVANCED OPEN WATER DIVER","englishShorthand":"AOWD","certificateLevel":"2","pic":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1919205464,1223035513&fm=26&gp=0.jpg","introduction":"AOW的水深限度是30米，并且可以做夜潜,本课程提供参加学员不同的专长潜水训练，如深潜、船潜、水中导航及夜潜...等，其中深潜和水中导航是必须选择的专长，除此两个专长以外，还需要选三个专长，通过这五个专长的训练后，方可拿到执照"}
         * pmsCourseProduct : {"id":5,"creator":null,"creatorId":null,"createdTime":null,"lastOperator":null,"lastOperatorId":null,"updateTime":null,"pageNum":null,"pageSize":null,"orderBy":null,"productName":"学证3","userId":1,"shopId":2,"shopName":null,"title":"发达发达发达附件","price":666,"productType":1,"location":"巴厘岛","lengthPlay":"5天8晚","isTimeLimit":0,"startTime":"2019-11-30 13:17:00","endTime":"2020-01-18 11:05:13","numberIsLimit":1,"numberLimit":8,"stock":7,"costIncludes":"吃喝拉撒","costNotIncludes":"坐车","purchaseNotes":"保管好自身财务","productDescription":"绝对让你满意","image":"https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg","contentArrangement":"[{\"date\":\"第一天\",\"message\":\"啥也不做\"},{\"date\":\"第二天\",\"message\":\"坐车回家\"}]","status":1,"approvalStatus":2,"sales":0,"sort":0,"certificateId":1,"addressId":2,"searchStartTime":null,"searchEndTime":null,"coachIcon":null,"coachName":null,"coachGrade":null}
         */

        private PmsCertificateBean pmsCertificate;
        private PmsCourseProductBean pmsCourseProduct;

        protected ResultBean(Parcel in) {
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

        public PmsCertificateBean getPmsCertificate() {
            return pmsCertificate;
        }

        public void setPmsCertificate(PmsCertificateBean pmsCertificate) {
            this.pmsCertificate = pmsCertificate;
        }

        public PmsCourseProductBean getPmsCourseProduct() {
            return pmsCourseProduct;
        }

        public void setPmsCourseProduct(PmsCourseProductBean pmsCourseProduct) {
            this.pmsCourseProduct = pmsCourseProduct;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        public static class PmsCertificateBean implements Serializable,Parcelable{
            /**
             * id : 2
             * englishName : ADVANCED OPEN WATER DIVER
             * englishShorthand : AOWD
             * certificateLevel : 2
             * pic : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1919205464,1223035513&fm=26&gp=0.jpg
             * introduction : AOW的水深限度是30米，并且可以做夜潜,本课程提供参加学员不同的专长潜水训练，如深潜、船潜、水中导航及夜潜...等，其中深潜和水中导航是必须选择的专长，除此两个专长以外，还需要选三个专长，通过这五个专长的训练后，方可拿到执照
             */

            private int id;
            private String englishName;
            private String englishShorthand;
            private String certificateLevel;
            private String pic;
            private String introduction;

            protected PmsCertificateBean(Parcel in) {
                id = in.readInt();
                englishName = in.readString();
                englishShorthand = in.readString();
                certificateLevel = in.readString();
                pic = in.readString();
                introduction = in.readString();
            }

            public static final Creator<PmsCertificateBean> CREATOR = new Creator<PmsCertificateBean>() {
                @Override
                public PmsCertificateBean createFromParcel(Parcel in) {
                    return new PmsCertificateBean(in);
                }

                @Override
                public PmsCertificateBean[] newArray(int size) {
                    return new PmsCertificateBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(englishName);
                dest.writeString(englishShorthand);
                dest.writeString(certificateLevel);
                dest.writeString(pic);
                dest.writeString(introduction);
            }
        }

        public static class PmsCourseProductBean implements Serializable,Parcelable{
            /**
             * id : 5
             * creator : null
             * creatorId : null
             * createdTime : null
             * lastOperator : null
             * lastOperatorId : null
             * updateTime : null
             * pageNum : null
             * pageSize : null
             * orderBy : null
             * productName : 学证3
             * userId : 1
             * shopId : 2
             * shopName : null
             * title : 发达发达发达附件
             * price : 666.0
             * productType : 1
             * location : 巴厘岛
             * lengthPlay : 5天8晚
             * isTimeLimit : 0
             * startTime : 2019-11-30 13:17:00
             * endTime : 2020-01-18 11:05:13
             * numberIsLimit : 1
             * numberLimit : 8
             * stock : 7
             * costIncludes : 吃喝拉撒
             * costNotIncludes : 坐车
             * purchaseNotes : 保管好自身财务
             * productDescription : 绝对让你满意
             * image : https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg
             * contentArrangement : [{"date":"第一天","message":"啥也不做"},{"date":"第二天","message":"坐车回家"}]
             * status : 1
             * approvalStatus : 2
             * sales : 0
             * sort : 0
             * certificateId : 1
             * addressId : 2
             * searchStartTime : null
             * searchEndTime : null
             * coachIcon : null
             * coachName : null
             * coachGrade : null
             */

            private int id;
            private Object creator;
            private Object creatorId;
            private Object createdTime;
            private Object lastOperator;
            private Object lastOperatorId;
            private Object updateTime;
            private Object pageNum;
            private Object pageSize;
            private Object orderBy;
            private String productName;
            private int userId;
            private int shopId;
            private Object shopName;
            private String title;
            private double price;
            private int productType;
            private String location;
            private String lengthPlay;
            private int isTimeLimit;
            private String startTime;
            private String endTime;
            private int numberIsLimit;
            private int numberLimit;
            private int stock;
            private String costIncludes;
            private String costNotIncludes;
            private String purchaseNotes;
            private String productDescription;
            private String image;
            private String contentArrangement;
            private int status;
            private int approvalStatus;
            private int sales;
            private int sort;
            private int certificateId;
            private int addressId;
            private Object searchStartTime;
            private Object searchEndTime;
            private Object coachIcon;
            private Object coachName;
            private Object coachGrade;

            protected PmsCourseProductBean(Parcel in) {
                id = in.readInt();
                productName = in.readString();
                userId = in.readInt();
                shopId = in.readInt();
                title = in.readString();
                price = in.readDouble();
                productType = in.readInt();
                location = in.readString();
                lengthPlay = in.readString();
                isTimeLimit = in.readInt();
                startTime = in.readString();
                endTime = in.readString();
                numberIsLimit = in.readInt();
                numberLimit = in.readInt();
                stock = in.readInt();
                costIncludes = in.readString();
                costNotIncludes = in.readString();
                purchaseNotes = in.readString();
                productDescription = in.readString();
                image = in.readString();
                contentArrangement = in.readString();
                status = in.readInt();
                approvalStatus = in.readInt();
                sales = in.readInt();
                sort = in.readInt();
                certificateId = in.readInt();
                addressId = in.readInt();
            }

            public static final Creator<PmsCourseProductBean> CREATOR = new Creator<PmsCourseProductBean>() {
                @Override
                public PmsCourseProductBean createFromParcel(Parcel in) {
                    return new PmsCourseProductBean(in);
                }

                @Override
                public PmsCourseProductBean[] newArray(int size) {
                    return new PmsCourseProductBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCreator() {
                return creator;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

            public Object getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(Object creatorId) {
                this.creatorId = creatorId;
            }

            public Object getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(Object createdTime) {
                this.createdTime = createdTime;
            }

            public Object getLastOperator() {
                return lastOperator;
            }

            public void setLastOperator(Object lastOperator) {
                this.lastOperator = lastOperator;
            }

            public Object getLastOperatorId() {
                return lastOperatorId;
            }

            public void setLastOperatorId(Object lastOperatorId) {
                this.lastOperatorId = lastOperatorId;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getPageNum() {
                return pageNum;
            }

            public void setPageNum(Object pageNum) {
                this.pageNum = pageNum;
            }

            public Object getPageSize() {
                return pageSize;
            }

            public void setPageSize(Object pageSize) {
                this.pageSize = pageSize;
            }

            public Object getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(Object orderBy) {
                this.orderBy = orderBy;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public Object getShopName() {
                return shopName;
            }

            public void setShopName(Object shopName) {
                this.shopName = shopName;
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

            public int getIsTimeLimit() {
                return isTimeLimit;
            }

            public void setIsTimeLimit(int isTimeLimit) {
                this.isTimeLimit = isTimeLimit;
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

            public int getNumberIsLimit() {
                return numberIsLimit;
            }

            public void setNumberIsLimit(int numberIsLimit) {
                this.numberIsLimit = numberIsLimit;
            }

            public int getNumberLimit() {
                return numberLimit;
            }

            public void setNumberLimit(int numberLimit) {
                this.numberLimit = numberLimit;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getApprovalStatus() {
                return approvalStatus;
            }

            public void setApprovalStatus(int approvalStatus) {
                this.approvalStatus = approvalStatus;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getCertificateId() {
                return certificateId;
            }

            public void setCertificateId(int certificateId) {
                this.certificateId = certificateId;
            }

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public Object getSearchStartTime() {
                return searchStartTime;
            }

            public void setSearchStartTime(Object searchStartTime) {
                this.searchStartTime = searchStartTime;
            }

            public Object getSearchEndTime() {
                return searchEndTime;
            }

            public void setSearchEndTime(Object searchEndTime) {
                this.searchEndTime = searchEndTime;
            }

            public Object getCoachIcon() {
                return coachIcon;
            }

            public void setCoachIcon(Object coachIcon) {
                this.coachIcon = coachIcon;
            }

            public Object getCoachName() {
                return coachName;
            }

            public void setCoachName(Object coachName) {
                this.coachName = coachName;
            }

            public Object getCoachGrade() {
                return coachGrade;
            }

            public void setCoachGrade(Object coachGrade) {
                this.coachGrade = coachGrade;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(productName);
                dest.writeInt(userId);
                dest.writeInt(shopId);
                dest.writeString(title);
                dest.writeDouble(price);
                dest.writeInt(productType);
                dest.writeString(location);
                dest.writeString(lengthPlay);
                dest.writeInt(isTimeLimit);
                dest.writeString(startTime);
                dest.writeString(endTime);
                dest.writeInt(numberIsLimit);
                dest.writeInt(numberLimit);
                dest.writeInt(stock);
                dest.writeString(costIncludes);
                dest.writeString(costNotIncludes);
                dest.writeString(purchaseNotes);
                dest.writeString(productDescription);
                dest.writeString(image);
                dest.writeString(contentArrangement);
                dest.writeInt(status);
                dest.writeInt(approvalStatus);
                dest.writeInt(sales);
                dest.writeInt(sort);
                dest.writeInt(certificateId);
                dest.writeInt(addressId);
            }
        }
    }
}
