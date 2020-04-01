package com.example.edive.bean;

public class MyPersonBean {
    /**
     * code : 200
     * message : 操作成功
     * data : {"id":32,"memberLevelId":null,"username":"15609563858","password":"$2a$10$1ElsujFeiUoPxF7p9GF9ZuxuBqoYIi4EL5vrKI.cHwtHWla32hoBW","nickname":"主要村","phone":"15609563858","status":2,"createTime":1575628480000,"icon":"http://47.107.50.253:8080/webapps/uploadFile/image/1575796840202_41501680.jpg","gender":null,"birthday":null,"city":null,"job":null,"personalizedSignature":"股海护航班乱七八糟糕糕糕点店子龙哥那个月肯定型枕套盒子上午的呢吧羽绒服务员工的代价值连城冠豸山里你别去哪找你们老师徒关系\n","sourceType":null,"integration":15000,"growth":null,"luckeyCount":null,"historyIntegration":30000,"identityCard":null,"stature":null,"weight":null,"role":null,"openId":null,"coachName":null,"coachGrade":null,"invitationCode":null,"personalProfile":null,"pics":"http://47.107.50.253:8080/webapps/uploadFile/image/1576028801029_68984344.jpg,http://47.107.50.253:8080/webapps/uploadFile/image/1576028802554_82734891.jpg,http://47.107.50.253:8080/webapps/uploadFile/image/1576028802375_95805557.jpg,http://47.107.50.253:8080/webapps/uploadFile/image/1576028802266_79113204.jpg","loginType":null}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 32
         * memberLevelId : null
         * username : 15609563858
         * password : $2a$10$1ElsujFeiUoPxF7p9GF9ZuxuBqoYIi4EL5vrKI.cHwtHWla32hoBW
         * nickname : 主要村
         * phone : 15609563858
         * status : 2
         * createTime : 1575628480000
         * icon : http://47.107.50.253:8080/webapps/uploadFile/image/1575796840202_41501680.jpg
         * gender : null
         * birthday : null
         * city : null
         * job : null
         * personalizedSignature : 股海护航班乱七八糟糕糕糕点店子龙哥那个月肯定型枕套盒子上午的呢吧羽绒服务员工的代价值连城冠豸山里你别去哪找你们老师徒关系
         * sourceType : null
         * integration : 15000
         * growth : null
         * luckeyCount : null
         * historyIntegration : 30000
         * identityCard : null
         * stature : null
         * weight : null
         * role : null
         * openId : null
         * coachName : null
         * coachGrade : null
         * invitationCode : null
         * personalProfile : null
         * pics : http://47.107.50.253:8080/webapps/uploadFile/image/1576028801029_68984344.jpg,http://47.107.50.253:8080/webapps/uploadFile/image/1576028802554_82734891.jpg,http://47.107.50.253:8080/webapps/uploadFile/image/1576028802375_95805557.jpg,http://47.107.50.253:8080/webapps/uploadFile/image/1576028802266_79113204.jpg
         * loginType : null
         */

        private int id;
        private Object memberLevelId;
        private String username;
        private String password;
        private String nickname;
        private String phone;
        private int status;
        private long createTime;
        private String icon;
        private Object gender;
        private Object birthday;
        private Object city;
        private Object job;
        private String personalizedSignature;
        private Object sourceType;
        private int integration;
        private Object growth;
        private Object luckeyCount;
        private int historyIntegration;
        private Object identityCard;
        private Object stature;
        private Object weight;
        private Object role;
        private Object openId;
        private Object coachName;
        private Object coachGrade;
        private Object invitationCode;
        private Object personalProfile;
        private String pics;
        private Object loginType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getMemberLevelId() {
            return memberLevelId;
        }

        public void setMemberLevelId(Object memberLevelId) {
            this.memberLevelId = memberLevelId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getJob() {
            return job;
        }

        public void setJob(Object job) {
            this.job = job;
        }

        public String getPersonalizedSignature() {
            return personalizedSignature;
        }

        public void setPersonalizedSignature(String personalizedSignature) {
            this.personalizedSignature = personalizedSignature;
        }

        public Object getSourceType() {
            return sourceType;
        }

        public void setSourceType(Object sourceType) {
            this.sourceType = sourceType;
        }

        public int getIntegration() {
            return integration;
        }

        public void setIntegration(int integration) {
            this.integration = integration;
        }

        public Object getGrowth() {
            return growth;
        }

        public void setGrowth(Object growth) {
            this.growth = growth;
        }

        public Object getLuckeyCount() {
            return luckeyCount;
        }

        public void setLuckeyCount(Object luckeyCount) {
            this.luckeyCount = luckeyCount;
        }

        public int getHistoryIntegration() {
            return historyIntegration;
        }

        public void setHistoryIntegration(int historyIntegration) {
            this.historyIntegration = historyIntegration;
        }

        public Object getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(Object identityCard) {
            this.identityCard = identityCard;
        }

        public Object getStature() {
            return stature;
        }

        public void setStature(Object stature) {
            this.stature = stature;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
        }

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
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

        public Object getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(Object invitationCode) {
            this.invitationCode = invitationCode;
        }

        public Object getPersonalProfile() {
            return personalProfile;
        }

        public void setPersonalProfile(Object personalProfile) {
            this.personalProfile = personalProfile;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public Object getLoginType() {
            return loginType;
        }

        public void setLoginType(Object loginType) {
            this.loginType = loginType;
        }
    }
}
