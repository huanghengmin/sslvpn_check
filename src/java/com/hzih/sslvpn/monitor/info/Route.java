package com.hzih.sslvpn.monitor.info;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-15
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public class Route {
    private String net;
    private String mask;
    private String via;

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public Route() {
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public Route(String net, String mask, String via) {
        this.net = net;
        this.mask = mask;
        this.via = via;
    }
}
