package com.hzih.sslvpn.monitor.handler;

import com.hzih.sslvpn.utils.JSONUtils;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Error;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Request;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Response;
import com.hzih.sslvpn.jsonrpc.server.MessageContext;
import com.hzih.sslvpn.jsonrpc.server.RequestHandler;
import com.hzih.sslvpn.monitor.info.VpnInfo;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-16
 * Time: 下午12:21
 * To change this template use File | Settings | File Templates.
 */
public class SSLVpnGetInfoHandler implements RequestHandler {
    private Logger logger = Logger.getLogger(SSLVpnGetInfoHandler.class);

    @Override
    public String[] handledRequests() {
        return new String[]{"vpn.getInfo"};
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        if (request.getMethod().equals("vpn.getInfo")) {
            List params = (List) request.getPositionalParams();
            /*type 网关类型ssl或者ipsec*/
            Object type = params.get(0);
            /*网关使用的算法，是一个对象，包含成员有：
            * auth:认证算法，是一个数组，列出网关使用的所有非对称算法
            * crypto:对称算法，是一个数组，列出网关傅的所有对称算法
            * digest:摘要算法，是一个数组，列出网关使用的所有摘要算法
            */
            Object algmode = params.get(1);
            /*tunelmode 网关通道模式，用数组列出使用的模式，tunel为隧道模式，forward为转发模式*/
            Object tunelmode = params.get(2);
            /*routes:网关配置的路由规则，是一个数组，其中每一个成员是一个对象，描述一条路由，该对象成员说明如下：
            * net : 网段
            * mask:掩码，若为0.0.0.0则表示默认网关
            * via:路由目的地址*/
            Object routes = params.get(3);
            /*nics:网关ip地址和开放的服务器端口，是一个数组，每个成员是一个对象，该对象成员说明如下：
            * interface：网卡
            * type:网卡类型，WAN表示外网口，LAN为内网口，ADMIN为管理网口
            * ip_addr:网卡的ip地址。*/
            Object nics = params.get(4);
            /*系统基本信息*/
            Object baseinfo = params.get(5);
            /*加密设备信息*/
            Object securitydeviceinfo = params.get(6);

            SSLVpnProcessHandler monitorHandler = new SSLVpnProcessHandler();

            VpnInfo vpnInfo = monitorHandler.sslVpnGetInfo(type, algmode, tunelmode, routes, nics, baseinfo, securitydeviceinfo);

            String rs = JSONUtils.toJSONString(JSONObject.fromObject(vpnInfo));

            rs = rs.replace("inter", "interface");

            return new JSONRPC2Response(rs, request.getID());
        } else {
            return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, request.getID());
        }
    }
}
