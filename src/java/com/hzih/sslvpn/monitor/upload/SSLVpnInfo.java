package com.hzih.sslvpn.monitor.upload;

import com.hzih.sslvpn.web.action.sslvpn.monitor.MonitorConfigXML;
import com.hzih.sslvpn.utils.JSONUtils;
import com.hzih.sslvpn.monitor.mina.TcpClient;
import com.hzih.sslvpn.monitor.handler.SSLVpnProcessHandler;
import com.hzih.sslvpn.monitor.info.VpnInfo;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

public class SSLVpnInfo {
    private Logger logger = Logger.getLogger(SSLVpnInfo.class);
    //更新在线用户
    public void uploadVpnInfo(){
        SSLVpnProcessHandler monitorHandler = new SSLVpnProcessHandler();
        Object type = "type";
        Object algmode = "algmode";
        Object tunelmode = "tunelmode";
        Object routes = "routes";
        Object nics = "nics";
        Object baseinfo = "baseinfo";
        Object securitydeviceinfo = "securitydeviceinfo";
        VpnInfo vpnInfo = monitorHandler.sslVpnGetInfo(type, algmode, tunelmode, routes, nics, baseinfo, securitydeviceinfo);
        String rs = JSONUtils.toJSONString(JSONObject.fromObject(vpnInfo));
        rs = rs.replace("inter","interface");
        StringBuilder json = new StringBuilder();
        json.append("{").append("\"jsonrpc\":").append("\"2.0\",").append("\"method\":").append("\"vpn.uploadInfo\"").append("\"params\":").append("[").append("\"vpnInfo\":").append(rs).append("]").append("}");
        TcpClient tcpClient = new TcpClient();
        tcpClient.setServerIp(MonitorConfigXML.ip);
        tcpClient.setServerPort(4300);
        tcpClient.connect();
        tcpClient.getSession().write(json);
        tcpClient.close();
    }
}
