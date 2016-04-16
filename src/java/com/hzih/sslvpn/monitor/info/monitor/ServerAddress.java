package com.hzih.sslvpn.monitor.info.monitor;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-16
 * Time: 下午1:08
 * To change this template use File | Settings | File Templates.
 */
public class ServerAddress {
    /*返回数据
        * {
     "jsonrpc": "2.0",
     "result": {
         "res":"OK",//OK，成功；FAILED 失败；
         "serveraddress":{
         "tunelmode": "100”,// 100 隧道模式，101 代理模式
         "monitorserverip": "192.168.1.10”//如果是隧道模式，需要填写		监测服务器IP，隧道模式不需要填写端口monitorserverporxyport
        "monitorserverporxyport": "55566”//如果是代理模式，需要填写		VPN代理后的本地端口，代理模式不需要填写monitorserverip
        }
    },
    "id": 1
    }
    */

    private String tunelmode;
    private String monitorserverip;
    private String monitorserverporxyport;

    public String getTunelmode() {
        return tunelmode;
    }

    public void setTunelmode(String tunelmode) {
        this.tunelmode = tunelmode;
    }

    public String getMonitorserverip() {
        return monitorserverip;
    }

    public void setMonitorserverip(String monitorserverip) {
        this.monitorserverip = monitorserverip;
    }

    public String getMonitorserverporxyport() {
        return monitorserverporxyport;
    }

    public void setMonitorserverporxyport(String monitorserverporxyport) {
        this.monitorserverporxyport = monitorserverporxyport;
    }

    public ServerAddress(String tunelmode, String monitorserverip, String monitorserverporxyport) {
        this.tunelmode = tunelmode;
        this.monitorserverip = monitorserverip;
        this.monitorserverporxyport = monitorserverporxyport;
    }

    public ServerAddress() {

    }
}
