package com.hzih.sslvpn.monitor.info;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-15
 * Time: 下午1:48
 * To change this template use File | Settings | File Templates.
 */
public class SecurityDeviceInfo {
    private String companyname;
    private String driverversion;
    private String workstat;

    public String getCompanyname() {
        return companyname;
    }

    public SecurityDeviceInfo() {
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;

    }

    public String getDriverversion() {
        return driverversion;
    }

    public void setDriverversion(String driverversion) {
        this.driverversion = driverversion;
    }

    public String getWorkstat() {
        return workstat;
    }

    public void setWorkstat(String workstat) {
        this.workstat = workstat;
    }

    public SecurityDeviceInfo(String companyname, String driverversion, String workstat) {
        this.companyname = companyname;
        this.driverversion = driverversion;
        this.workstat = workstat;
    }
}
