package com.hzih.sslvpn.monitor.info;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-15
 * Time: 下午1:40
 * To change this template use File | Settings | File Templates.
 */
public class VpnInfo {
    private String from;
    private String timestamp;
    private String type;
    private AlgMode algmode;
    private List<String> tunelmode;
    private List<Route> routes;
    private List<Nics> nics;
//    private String nics;

//    public String getNics() {
//        return nics;
//    }

//    public void setNics(String nics) {
//        this.nics = nics;
//    }

    private BaseInfo baseinfo;
    private SecurityDeviceInfo securitydeviceinfo;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AlgMode getAlgmode() {
        return algmode;
    }

    public void setAlgmode(AlgMode algmode) {
        this.algmode = algmode;
    }

    public List<String> getTunelmode() {
        return tunelmode;
    }

    public void setTunelmode(List<String> tunelmode) {
        this.tunelmode = tunelmode;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Nics> getNics() {
        return nics;
    }

    public void setNics(List<Nics> nics) {
        this.nics = nics;
    }

    public VpnInfo(String from, String timestamp, String type, AlgMode algmode, List<String> tunelmode, List<Route> routes, List<Nics> /*String*/ nics, BaseInfo baseinfo, SecurityDeviceInfo securitydeviceinfo) {
        this.from = from;
        this.timestamp = timestamp;
        this.type = type;
        this.algmode = algmode;
        this.tunelmode = tunelmode;
        this.routes = routes;
        this.nics = nics;
        this.baseinfo = baseinfo;

        this.securitydeviceinfo = securitydeviceinfo;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }


    public BaseInfo getBaseinfo() {
        return baseinfo;
    }

    public void setBaseinfo(BaseInfo baseinfo) {
        this.baseinfo = baseinfo;
    }

    public SecurityDeviceInfo getSecuritydeviceinfo() {
        return securitydeviceinfo;
    }

    public void setSecuritydeviceinfo(SecurityDeviceInfo securitydeviceinfo) {
        this.securitydeviceinfo = securitydeviceinfo;
    }

    public VpnInfo() {

    }
}
