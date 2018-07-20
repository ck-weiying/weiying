package com.example.weiying.model.bean;

import java.util.List;

/**
 * 直播列表
 */
public class LiveBean {
    /**
     * result : [{"address":"rtmp://172.17.8.100/live/18600151568_20180720084006","id":17,"nickName":"张三","userId":1},{"address":"rtmp://172.17.8.100/live/13522956256_20180720082822","id":13,"nickName":"敏","userId":8},{"address":"rtmp://172.17.8.100/live/13520306913_20180720082803","id":12,"nickName":"璠璠璠","userId":37}]
     * message : 请求成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : rtmp://172.17.8.100/live/18600151568_20180720084006
         * id : 17
         * nickName : 张三
         * userId : 1
         */

        private String address;
        private int id;
        private String nickName;
        private int userId;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
