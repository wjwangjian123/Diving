package com.example.edive.bean;

import java.util.List;

public class AllMessagerBean {
    /**
     * code : 200
     * message : 操作成功
     * result : {"total":6,"list":[{"userId":19,"userName":"17731271980","userIcon":"e099663fcd484f1882c3f1e4e7363743.jpg","nickName":"花花","createdTime":"2019-11-22 14:38:52","info":"什么东东了,玩的一点都不开心,不介意去","level":"-1","descStar":5,"logisticsStar":1,"attitudeStar":5,"pic":"http://pic23.photophoto.cn/20120614/0008020246371439_b.jpg,https://imgsa.baidu.com/forum/pic/item/ab64034f78f0f7365c388f0e0555b319eac413ff.jpg","picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 10:17:38","info":"和","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 11:45:40","info":"和","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 11:48:13","info":"哈","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 11:50:32","info":"和","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]}],"pageNum":1,"pageSize":5,"size":5,"startRow":1,"endRow":5,"pages":2,"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2],"navigateFirstPage":1,"navigateLastPage":2,"firstPage":1,"lastPage":2}
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
         * total : 6
         * list : [{"userId":19,"userName":"17731271980","userIcon":"e099663fcd484f1882c3f1e4e7363743.jpg","nickName":"花花","createdTime":"2019-11-22 14:38:52","info":"什么东东了,玩的一点都不开心,不介意去","level":"-1","descStar":5,"logisticsStar":1,"attitudeStar":5,"pic":"http://pic23.photophoto.cn/20120614/0008020246371439_b.jpg,https://imgsa.baidu.com/forum/pic/item/ab64034f78f0f7365c388f0e0555b319eac413ff.jpg","picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 10:17:38","info":"和","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 11:45:40","info":"和","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 11:48:13","info":"哈","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]},{"userId":21,"userName":"15609563333","userIcon":"www.baidu.com","nickName":"在爱与悲伤徘徊的女孩纸","createdTime":"2019-11-27 11:50:32","info":"和","level":null,"descStar":null,"logisticsStar":null,"attitudeStar":null,"pic":null,"picList":[]}]
         * pageNum : 1
         * pageSize : 5
         * size : 5
         * startRow : 1
         * endRow : 5
         * pages : 2
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2]
         * navigateFirstPage : 1
         * navigateLastPage : 2
         * firstPage : 1
         * lastPage : 2
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
             * userId : 19
             * userName : 17731271980
             * userIcon : e099663fcd484f1882c3f1e4e7363743.jpg
             * nickName : 花花
             * createdTime : 2019-11-22 14:38:52
             * info : 什么东东了,玩的一点都不开心,不介意去
             * level : -1
             * descStar : 5
             * logisticsStar : 1
             * attitudeStar : 5
             * pic : http://pic23.photophoto.cn/20120614/0008020246371439_b.jpg,https://imgsa.baidu.com/forum/pic/item/ab64034f78f0f7365c388f0e0555b319eac413ff.jpg
             * picList : []
             */

            private int userId;
            private String userName;
            private String userIcon;
            private String nickName;
            private String createdTime;
            private String info;
            private String level;
            private int descStar;
            private int logisticsStar;
            private int attitudeStar;
            private String pic;
            private List<?> picList;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserIcon() {
                return userIcon;
            }

            public void setUserIcon(String userIcon) {
                this.userIcon = userIcon;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public int getDescStar() {
                return descStar;
            }

            public void setDescStar(int descStar) {
                this.descStar = descStar;
            }

            public int getLogisticsStar() {
                return logisticsStar;
            }

            public void setLogisticsStar(int logisticsStar) {
                this.logisticsStar = logisticsStar;
            }

            public int getAttitudeStar() {
                return attitudeStar;
            }

            public void setAttitudeStar(int attitudeStar) {
                this.attitudeStar = attitudeStar;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public List<?> getPicList() {
                return picList;
            }

            public void setPicList(List<?> picList) {
                this.picList = picList;
            }
        }
    }
}
