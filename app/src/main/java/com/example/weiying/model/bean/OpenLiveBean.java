package com.example.weiying.model.bean;

/**
 * author:Created by WangZhiQiang on 2018/7/20.
 * 开启直播
 */
public class OpenLiveBean {
    /**
     * address : rtmp://172.17.8.100/live/18600151568_20180720084006
     * message : 开启直播成功
     * status : 0000
     */

    private String address;
    private String message;
    private String status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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


    @Override
    public String toString() {
        return "OpenLiveBean{" +
                "address='" + address + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
