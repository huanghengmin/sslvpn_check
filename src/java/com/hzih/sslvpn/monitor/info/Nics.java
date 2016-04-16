package com.hzih.sslvpn.monitor.info;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-15
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public class Nics {
    private String inter;

    public Nics(String inter, String type, String ip_addr, String netmask, String stats) {
        this.inter = inter;
        this.type = type;
        this.ip_addr = ip_addr;
        this.netmask = netmask;
        this.stats = stats;
    }

    private String type;

    public Nics() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp_addr() {
        return ip_addr;
    }

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    public void setIp_addr(String ip_addr) {
        this.ip_addr = ip_addr;

    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    private String ip_addr;
    private String netmask;
    private String stats;

}
