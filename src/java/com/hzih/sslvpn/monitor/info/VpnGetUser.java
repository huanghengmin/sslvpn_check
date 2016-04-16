package com.hzih.sslvpn.monitor.info;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-20
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
public class VpnGetUser {
    private String from;
    private String timestamp;

    public VpnGetUser(String from, String timestamp, List<VpnUser> users) {
        this.from = from;
        this.timestamp = timestamp;
        this.users = users;
    }

    public VpnGetUser() {

    }

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

    public List<VpnUser> getUsers() {
        return users;
    }

    public void setUsers(List<VpnUser> users) {
        this.users = users;
    }

    private List<VpnUser>  users;
}
