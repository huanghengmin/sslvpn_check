package com.hzih.sslvpn.domain;

import java.util.Set;

/**
 * Created by Administrator on 15-6-4.
 */
public class Nat {
    private int id;
    private String bindIp;
    private String bindPort;
    private String protocol;
    private int level;
    private String targetIp;
    private String targetPort;
    private Set<User> userSet;
    private int count;

    public Nat() {
    }

    public Nat(int id) {
        this.id = id;
    }

    public Nat(String bindIp, String bindPort, String protocol) {
        this.bindIp = bindIp;
        this.bindPort = bindPort;
        this.protocol = protocol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBindIp() {
        return bindIp;
    }

    public void setBindIp(String bindIp) {
        this.bindIp = bindIp;
    }

    public String getBindPort() {
        return bindPort;
    }

    public void setBindPort(String bindPort) {
        this.bindPort = bindPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    public String getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(String targetPort) {
        this.targetPort = targetPort;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nat nat = (Nat) o;
        if (id != nat.id) return false;
        if (level != nat.level) return false;
        if (bindIp != null ? !bindIp.equals(nat.bindIp) : nat.bindIp != null) return false;
        if (bindPort != null ? !bindPort.equals(nat.bindPort) : nat.bindPort != null) return false;
        if (protocol != null ? !protocol.equals(nat.protocol) : nat.protocol != null) return false;
        if (targetIp != null ? !targetIp.equals(nat.targetIp) : nat.targetIp != null) return false;
        if (targetPort != null ? !targetPort.equals(nat.targetPort) : nat.targetPort != null) return false;
//        if (userSet != null ? !userSet.equals(nat.userSet) : nat.userSet != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bindIp != null ? bindIp.hashCode() : 0);
        result = 31 * result + (bindPort != null ? bindPort.hashCode() : 0);
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + (targetIp != null ? targetIp.hashCode() : 0);
        result = 31 * result + (targetPort != null ? targetPort.hashCode() : 0);
//        result = 31 * result + (userSet != null ? userSet.hashCode() : 0);
        return result;
    }
}
