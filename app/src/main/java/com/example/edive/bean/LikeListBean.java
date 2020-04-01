package com.example.edive.bean;

import java.util.List;

public class LikeListBean {
    /**
     * code : 200
     * message : 操作成功
     * result : {"total":10,"list":[{"noticeId":151,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:14","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":150,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:14","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":152,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:17","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":153,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:42","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":155,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:15","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":156,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:17","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":157,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:18","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":159,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:30","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":160,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:31","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":161,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:40","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"}],"pageNum":1,"pageSize":10,"size":10,"startRow":0,"endRow":9,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 10
         * list : [{"noticeId":151,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:14","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":150,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:14","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":152,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:17","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":153,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 13:47:42","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":155,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:15","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":156,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:17","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":157,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:18","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":159,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:30","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":160,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:31","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"},{"noticeId":161,"isRead":0,"title":"动态点赞消息","content":"#克罗索尔岛","createdTime":"2019-12-04 14:53:40","userId":23,"icon":"/data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg","userName":"15609563858","nickName":"time","jumpAddress":"46"}]
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * startRow : 0
         * endRow : 9
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
             * noticeId : 151
             * isRead : 0
             * title : 动态点赞消息
             * content : #克罗索尔岛
             * createdTime : 2019-12-04 13:47:14
             * userId : 23
             * icon : /data/user/0/com.example.diving/cache/takephoto_cache/1575444652228.jpg
             * userName : 15609563858
             * nickName : time
             * jumpAddress : 46
             */

            private int noticeId;
            private int isRead;
            private String title;
            private String content;
            private String createdTime;
            private int userId;
            private String icon;
            private String userName;
            private String nickName;
            private String jumpAddress;

            public int getNoticeId() {
                return noticeId;
            }

            public void setNoticeId(int noticeId) {
                this.noticeId = noticeId;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getJumpAddress() {
                return jumpAddress;
            }

            public void setJumpAddress(String jumpAddress) {
                this.jumpAddress = jumpAddress;
            }
        }
    }
}
