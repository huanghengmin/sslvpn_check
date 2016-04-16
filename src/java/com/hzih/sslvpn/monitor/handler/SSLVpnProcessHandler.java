package com.hzih.sslvpn.monitor.handler;

import com.hzih.sslvpn.entity.NetInfo;
import com.hzih.sslvpn.utils.GetListNetInfo;
import com.hzih.sslvpn.web.action.sslvpn.monitor.MonitorConfigXML;
import com.hzih.sslvpn.monitor.info.*;
import com.hzih.sslvpn.monitor.info.monitor.Monitor;
import com.hzih.sslvpn.monitor.info.monitor.ServerAddress;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-16
 * Time: 下午12:08
 * To change this template use File | Settings | File Templates.
 */
public class SSLVpnProcessHandler{
    private Logger logger = Logger.getLogger(SSLVpnProcessHandler.class);

    //监测系统获取vpn信息接口
    public VpnInfo sslVpnGetInfo(Object type, Object algmode, Object tunelmode, Object routes, Object nics, Object baseinfo, Object securitydeviceinfo) {
        /*VPN信息组装类*/
        VpnInfoUtils vpnInfoUtils = new VpnInfoUtils();
        /*网络信息组装类*/
        GetListNetInfo getListNetInfo = new GetListNetInfo();
        String vpn_type = "";
        if (null != type) {
            vpn_type = vpnInfoUtils.getVpnType();
        }
        AlgMode algMode = null;
        if (null != algmode) {
            /*认证算法信息*/
            List<String> auth_list = vpnInfoUtils.getVpnAuthList();
            /*对称算法信息*/
            List<String> crypto_list = vpnInfoUtils.getVpnCryptoList();
            /*非对称算法信息*/
            List<String> digest_list = vpnInfoUtils.getVpnDigestList();
            /*网关使用算法信息*/
            algMode = new AlgMode(auth_list, crypto_list, digest_list);
        }
        List<Route> routes_list = new ArrayList<>();
        if (null != routes) {
        /*路由信息*/
            List<NetInfo> netInfos = getListNetInfo.readRouter();
            for (NetInfo netInfo : netInfos) {
                routes_list.add(new Route(netInfo.getDestination(), netInfo.getSubnetMask(), netInfo.getGateway()));
            }
        }
        /*网卡信息*/
        List<Nics> nicsList = new ArrayList<>();
//        StringBuilder nics_json = new StringBuilder();
//        nics_json.append("[");
        if (null != nics) {
            try {
                List<NetInfo> interfaceInfos = getListNetInfo.readInterfaces();
                Iterator<NetInfo> infoIterator = interfaceInfos.listIterator();
                while (infoIterator.hasNext()) {
                    NetInfo netInfo = infoIterator.next();
                    Nics nics1 = new Nics(netInfo.getInterfaceName(), "LAN", netInfo.getIp(), netInfo.getSubnetMask(), netInfo.getIsUp() ? "UP" : "DOWN");
                    nicsList.add(nics1);
//                    nics_json.append("{").
//                            append("\"interface\"").append(":").append("\""+netInfo.getInterfaceName()+"\"").append(",").
//                            append("\"type\"").append(":").append("\""+"LAN"+"\"").append(",").
//                            append("\"ip_addr\"").append(":").append("\""+netInfo.getIp()+"\"").append(",").
//                            append("\"netmask\"").append(":").append("\""+netInfo.getSubnetMask()+"\"").append(",").
//                            append("\"status\"").append(":").append("\""+(netInfo.getIsUp()?"UP":"DOWN")+"\"").append("}");
//                    if(infoIterator.hasNext()){
//                        nics_json.append(",");
//                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage(),e);
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
//        nics_json.append("]");
        /*系统信息*/
        BaseInfo baseInfo = null;
        if (null != baseinfo)
            baseInfo = new BaseInfo(System.getProperty("os.name"), System.getProperty("os.version"));
        /*加密设备信息*/
        SecurityDeviceInfo securityDeviceInfo = null;
        if (null != securitydeviceinfo)
            securityDeviceInfo = vpnInfoUtils.getSecurityDeviceInfo();
        /*网关通道模式信息*/
        List<String> tunnel_list = null;
        if (null != tunelmode)
            tunnel_list = vpnInfoUtils.getTunelMode();
        /*数据响应时间*/
        String time = vpnInfoUtils.getTimeStamp();
        /*VPN信息*/
        VpnInfo vpnInfo = new VpnInfo(vpnInfoUtils.getMessageFrom(), time, vpn_type, algMode, tunnel_list, routes_list, nicsList/*nics_json.toString()*/, baseInfo, securityDeviceInfo);
        return vpnInfo;
    }

    //vpn提供用户强制下线服务
    public String sslVpnKickUser(String cn) {
        String result_status = "not found";
        String requestUrl = "http://localhost/ClientControl_disable.action";
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5 * 1000);
        client.getHttpConnectionManager().getParams().setSoTimeout(5 * 1000);
        PostMethod post = new PostMethod(requestUrl);
        post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 1000);
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        String[][] params = new String[][]{
                {"cn", cn}
        };

        for (String[] param : params) {
            post.addParameter(param[0], param[1]);
        }
        int statusCode = 0;
        try {
            statusCode = client.executeMethod(post);
            if (statusCode == 200) {
                String data = post.getResponseBodyAsString();
                return data;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return result_status;
    }

    //vpn客户端获取监测系统信息
    public Monitor sslVpnGetMonitorInfo() {
        String ip = MonitorConfigXML.getAttribute(MonitorConfigXML.ip);
        String port = MonitorConfigXML.getAttribute(MonitorConfigXML.port);
        ServerAddress serverAddress = new ServerAddress("100", ip, port);
        Monitor monitor = new Monitor("OK", serverAddress);
        return monitor;
    }


}
