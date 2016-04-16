package com.hzih.sslvpn.monitor.handler;

import com.hzih.sslvpn.utils.JSONUtils;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Error;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Request;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Response;
import com.hzih.sslvpn.jsonrpc.server.MessageContext;
import com.hzih.sslvpn.jsonrpc.server.RequestHandler;
import com.hzih.sslvpn.monitor.info.VpnGetUser;
import com.hzih.sslvpn.monitor.info.VpnInfoUtils;
import com.hzih.sslvpn.monitor.info.VpnUser;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-20
 * Time: 上午9:51
 * To change this template use File | Settings | File Templates.
 */
public class SSLVpnGetUsersHandler implements RequestHandler {

    @Override
    public String[] handledRequests() {
        return new String[]{"vpn.getUsers"};
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        if (request.getMethod().equals("vpn.getUsers")) {
            List params = (List) request.getPositionalParams();
            Object start = params.get(0);
            Object limit = params.get(1);
            if (null == start) {
                start = "0";
            }
            if (null == limit) {
                limit = "10000";
            }
            List<VpnUser> vpnUsers = null;
            VpnInfoUtils vpnInfoUtils = new VpnInfoUtils();
         /*   GetUsersDao getUsersDao = new GetUsersDao();
            List<User> users = getUsersDao.getUsers(Integer.parseInt(start.toString()), Integer.parseInt(limit.toString()));
            if (null != users) {
                vpnUsers = new ArrayList<>();
                for (User u : users) {
                    VpnUser vpnUser = new VpnUser(u.getCn(), u.getId_card(), "", u.getSerial_number(), "", "", 0, vpnInfoUtils.getTimeStamp());
                    vpnUsers.add(vpnUser);
                }
            }*/
            VpnGetUser vpnGetUser = new VpnGetUser(vpnInfoUtils.getMessageFrom(), vpnInfoUtils.getTimeStamp(), vpnUsers);
            String rs = JSONUtils.toJSONString(JSONObject.fromObject(vpnGetUser));
            return new JSONRPC2Response(rs, request.getID());
        } else {
            return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, request.getID());
        }
    }
}
