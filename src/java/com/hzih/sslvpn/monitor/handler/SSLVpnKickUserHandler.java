package com.hzih.sslvpn.monitor.handler;

import com.hzih.sslvpn.jsonrpc.JSONRPC2Error;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Request;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Response;
import com.hzih.sslvpn.jsonrpc.server.MessageContext;
import com.hzih.sslvpn.jsonrpc.server.RequestHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-16
 * Time: 下午12:21
 * To change this template use File | Settings | File Templates.
 */
public class SSLVpnKickUserHandler implements RequestHandler{
    @Override
    public String[] handledRequests() {
        return new String[]{"vpn.kickUser"};
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        if (request.getMethod().equals("vpn.kickUser")) {

            // Echo first parameter
            List params = (List)request.getPositionalParams();
//            Map<String,String> map = (Map<String,String>)params.get(0);
            
//            String serial = params.get("params");
            Object serial = params.get(0);

            String temp_serial = serial.toString().toUpperCase();
            while (temp_serial.startsWith("0")){
                temp_serial = temp_serial.substring(1,temp_serial.length());
            }
            
            SSLVpnProcessHandler handler = new SSLVpnProcessHandler();

            String kill_res = null;
            try {
                kill_res = handler.sslVpnKickUser(temp_serial.toString());
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return new JSONRPC2Response(kill_res, request.getID());
        }
        else {
            // Method name not supported

            return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, request.getID());
        }
    }
}
