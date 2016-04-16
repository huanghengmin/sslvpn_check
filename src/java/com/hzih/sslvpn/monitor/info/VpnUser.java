package com.hzih.sslvpn.monitor.info;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-20
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
public class VpnUser {
    private String username;
    private String identity;
    private String unit;
    private String certid;
    private String tfid;
    private String terminalid;
    private int action;
    private String time;

    public VpnUser(String username, String identity, String unit, String certid, String tfid, String terminalid, int action, String time) {
        this.username = username;
        this.identity = identity;
        this.unit = unit;
        this.certid = certid;
        this.tfid = tfid;
        this.terminalid = terminalid;
        this.action = action;
        this.time = time;
    }

    public VpnUser() {

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCertid() {
        return certid;
    }

    public void setCertid(String certid) {
        this.certid = certid;
    }

    public String getTfid() {
        return tfid;
    }

    public void setTfid(String tfid) {
        this.tfid = tfid;
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
