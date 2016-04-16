package com.hzih.sslvpn.monitor.info;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-15
 * Time: 下午1:41
 * To change this template use File | Settings | File Templates.
 */
public class AlgMode {
    private List<String> auth;
    private List<String> crypto;
    private List<String> digest;

    public AlgMode(List<String> auth, List<String> crypto, List<String> digest) {
        this.auth = auth;
        this.crypto = crypto;
        this.digest = digest;
    }

    public List<String> getAuth() {
        return auth;
    }

    public void setAuth(List<String> auth) {
        this.auth = auth;
    }

    public List<String> getCrypto() {
        return crypto;
    }

    public void setCrypto(List<String> crypto) {
        this.crypto = crypto;
    }

    public List<String> getDigest() {
        return digest;
    }

    public AlgMode() {
    }

    public void setDigest(List<String> digest) {

        this.digest = digest;
    }

}
