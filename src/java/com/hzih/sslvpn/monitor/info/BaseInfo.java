package com.hzih.sslvpn.monitor.info;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-15
 * Time: 下午1:46
 * To change this template use File | Settings | File Templates.
 */
public class BaseInfo {
    private String os;
    private String version;

    public BaseInfo(String os, String version) {
        this.os = os;
        this.version = version;
    }

    public BaseInfo() {
    }

    public String getOs() {

        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
