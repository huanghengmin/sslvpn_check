package com.hzih.sslvpn.domain;

import java.util.Date;

/**
 * Created by Administrator on 15-4-23.
 */
public class ClientLog {
    private long id;
    private String CN;
    private String SN;
    private String O;
    private String OU;
    private String L;
    private String ST;
    private String phone;
    private String idcard;
    private String email;
    private String sourceip;
    private String sourceport;
    private String accessurl;
    private String result;
    private String upbytes;
    private String downbytes;
    private String audittype;
    private Date datetime;

    public ClientLog() {
    }

    public ClientLog(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCN() {
        return CN;
    }

    public void setCN(String CN) {
        this.CN = CN;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getO() {
        return O;
    }

    public void setO(String o) {
        O = o;
    }

    public String getOU() {
        return OU;
    }

    public void setOU(String OU) {
        this.OU = OU;
    }

    public String getL() {
        return L;
    }

    public void setL(String l) {
        L = l;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSourceip() {
        return sourceip;
    }

    public void setSourceip(String sourceip) {
        this.sourceip = sourceip;
    }

    public String getSourceport() {
        return sourceport;
    }

    public void setSourceport(String sourceport) {
        this.sourceport = sourceport;
    }

    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUpbytes() {
        return upbytes;
    }

    public void setUpbytes(String upbytes) {
        this.upbytes = upbytes;
    }

    public String getDownbytes() {
        return downbytes;
    }

    public void setDownbytes(String downbytes) {
        this.downbytes = downbytes;
    }

    public String getAudittype() {
        return audittype;
    }

    public void setAudittype(String audittype) {
        this.audittype = audittype;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
