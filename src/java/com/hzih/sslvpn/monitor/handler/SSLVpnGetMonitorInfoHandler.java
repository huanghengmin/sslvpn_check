package com.hzih.sslvpn.monitor.handler;

import com.hzih.sslvpn.utils.JSONUtils;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Error;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Request;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Response;
import com.hzih.sslvpn.jsonrpc.server.MessageContext;
import com.hzih.sslvpn.jsonrpc.server.RequestHandler;
import com.hzih.sslvpn.monitor.info.monitor.Monitor;
import net.sf.json.JSONObject;


/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-16
 * Time: 下午12:21
 * To change this template use File | Settings | File Templates.
 */
public class SSLVpnGetMonitorInfoHandler implements RequestHandler{
    @Override
    public String[] handledRequests() {
        return new String[]{"vpn.getmonitor"};
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        if (request.getMethod().equals("vpn.getmonitor")) {
            // Echo first parameter
            SSLVpnProcessHandler monitorHandler = new SSLVpnProcessHandler();
            Monitor monitor = monitorHandler.sslVpnGetMonitorInfo();
            String rs = null;
            if(null!=monitor) {
                rs = JSONUtils.toJSONString(JSONObject.fromObject(monitor));
                return new JSONRPC2Response(rs, request.getID());
            }else {
                return new JSONRPC2Response("not found server config!", request.getID());
            }
        }
        else {
            // Method name not supported
            return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, request.getID());
        }
    }
}
