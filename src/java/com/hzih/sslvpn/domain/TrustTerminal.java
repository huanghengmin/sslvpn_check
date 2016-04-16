package com.hzih.sslvpn.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 15-4-8.
 */
public class TrustTerminal implements Serializable {
    private long id;
    private String ip;
    private String mac;
    private String startTime;
    private String endTime;
    private int status;

    public TrustTerminal(String ip, String mac,String startTime,String endTime) {
        this.ip = ip;
        this.mac = mac;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public TrustTerminal(int id) {
        this.id = id;
    }

    public TrustTerminal() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
