package com.hzih.sslvpn.monitor.info.monitor;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-16
 * Time: 下午1:05
 * To change this template use File | Settings | File Templates.
 */
public class Monitor {
    private String res;
    private ServerAddress serveraddress;

    public Monitor() {
    }

    public Monitor(String res, ServerAddress serveraddress) {

        this.res = res;
        this.serveraddress = serveraddress;
    }

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

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public ServerAddress getServeraddress() {
        return serveraddress;
    }

    public void setServeraddress(ServerAddress serveraddress) {
        this.serveraddress = serveraddress;
    }
}
