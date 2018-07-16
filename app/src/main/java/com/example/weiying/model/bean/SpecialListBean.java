package com.example.weiying.model.bean;

import java.util.List;

public class SpecialListBean {

    /**
     * msg : 成功
     * ret : {"adv":{"imgURL":"","dataId":"","htmlURL":"","shareURL":"","title":""},"pnum":1,"totalRecords":1,"bannerList":[],"records":30,"list":[{"airTime":2014,"duration":"01:53:22","loadtype":"video","score":0,"angleIcon":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296586400001011.png","dataId":"35b74f148cd34048af00f0a187628589","description":"梅远贵（邓超 饰）是一个情感经历极为丰富的男人，虽然有过这样那样的失败恋情，却也帮他铺就了一条通往荣华复归的康庄大道。现如今的梅远贵生活在首都北京，与其精英团队操办起帮人分手的奇葩业务，无论你有着怎样的感情和生活，只要票子给足，他总会高效率地完成任务。爱情那么虚幻的东西太不可靠，还是钱最让他放心。这一天，梅照例接了一单生意，那就是帮客户甩掉美丽女孩叶小春（杨幂 饰）。小春是一个自强不息的北漂姑娘，她渴望可以让之依靠休息的港湾，同时也为了美好的明天奋力打拼。原本踌躇满志的梅远贵，从和小春接触的第一天起，他们彼此的人生就发生了改变\u2026\u2026 \r\n　　本片根据同名人气话剧改编。","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=35b74f148cd34048af00f0a187628589","shareURL":"","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/30/1498819141523042685.jpg","title":"分手大师","roomId":""}],"totalPnum":1}
     * code : 200
     */

    private String msg;
    private RetBean ret;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class RetBean {
        /**
         * adv : {"imgURL":"","dataId":"","htmlURL":"","shareURL":"","title":""}
         * pnum : 1
         * totalRecords : 1
         * bannerList : []
         * records : 30
         * list : [{"airTime":2014,"duration":"01:53:22","loadtype":"video","score":0,"angleIcon":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296586400001011.png","dataId":"35b74f148cd34048af00f0a187628589","description":"梅远贵（邓超 饰）是一个情感经历极为丰富的男人，虽然有过这样那样的失败恋情，却也帮他铺就了一条通往荣华复归的康庄大道。现如今的梅远贵生活在首都北京，与其精英团队操办起帮人分手的奇葩业务，无论你有着怎样的感情和生活，只要票子给足，他总会高效率地完成任务。爱情那么虚幻的东西太不可靠，还是钱最让他放心。这一天，梅照例接了一单生意，那就是帮客户甩掉美丽女孩叶小春（杨幂 饰）。小春是一个自强不息的北漂姑娘，她渴望可以让之依靠休息的港湾，同时也为了美好的明天奋力打拼。原本踌躇满志的梅远贵，从和小春接触的第一天起，他们彼此的人生就发生了改变\u2026\u2026 \r\n　　本片根据同名人气话剧改编。","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=35b74f148cd34048af00f0a187628589","shareURL":"","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/30/1498819141523042685.jpg","title":"分手大师","roomId":""}]
         * totalPnum : 1
         */

        private AdvBean adv;
        private int pnum;
        private int totalRecords;
        private int records;
        private int totalPnum;
        private List<?> bannerList;
        private List<ListBean> list;

        public AdvBean getAdv() {
            return adv;
        }

        public void setAdv(AdvBean adv) {
            this.adv = adv;
        }

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotalPnum() {
            return totalPnum;
        }

        public void setTotalPnum(int totalPnum) {
            this.totalPnum = totalPnum;
        }

        public List<?> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<?> bannerList) {
            this.bannerList = bannerList;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AdvBean {
            /**
             * imgURL : 
             * dataId : 
             * htmlURL : 
             * shareURL : 
             * title : 
             */

            private String imgURL;
            private String dataId;
            private String htmlURL;
            private String shareURL;
            private String title;

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getHtmlURL() {
                return htmlURL;
            }

            public void setHtmlURL(String htmlURL) {
                this.htmlURL = htmlURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ListBean {
            /**
             * airTime : 2014
             * duration : 01:53:22
             * loadtype : video
             * score : 0
             * angleIcon : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296586400001011.png
             * dataId : 35b74f148cd34048af00f0a187628589
             * description : 梅远贵（邓超 饰）是一个情感经历极为丰富的男人，虽然有过这样那样的失败恋情，却也帮他铺就了一条通往荣华复归的康庄大道。现如今的梅远贵生活在首都北京，与其精英团队操办起帮人分手的奇葩业务，无论你有着怎样的感情和生活，只要票子给足，他总会高效率地完成任务。爱情那么虚幻的东西太不可靠，还是钱最让他放心。这一天，梅照例接了一单生意，那就是帮客户甩掉美丽女孩叶小春（杨幂 饰）。小春是一个自强不息的北漂姑娘，她渴望可以让之依靠休息的港湾，同时也为了美好的明天奋力打拼。原本踌躇满志的梅远贵，从和小春接触的第一天起，他们彼此的人生就发生了改变…… 
             　　本片根据同名人气话剧改编。
             * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=35b74f148cd34048af00f0a187628589
             * shareURL : 
             * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/30/1498819141523042685.jpg
             * title : 分手大师
             * roomId : 
             */

            private int airTime;
            private String duration;
            private String loadtype;
            private int score;
            private String angleIcon;
            private String dataId;
            private String description;
            private String loadURL;
            private String shareURL;
            private String pic;
            private String title;
            private String roomId;

            public int getAirTime() {
                return airTime;
            }

            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getLoadtype() {
                return loadtype;
            }

            public void setLoadtype(String loadtype) {
                this.loadtype = loadtype;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getAngleIcon() {
                return angleIcon;
            }

            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLoadURL() {
                return loadURL;
            }

            public void setLoadURL(String loadURL) {
                this.loadURL = loadURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }
        }
    }
}
