package com.hzih.sslvpn.monitor.info;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-17
 * Time: 下午1:26
 * To change this template use File | Settings | File Templates.
 */
public class VpnInfoUtils {

    public List<String> getVpnAuthList(){
        String auth_one = "rsa";
        String auth_two = "sm2";
        List<String> auth_list = new ArrayList<>();
        auth_list.add(auth_one);
        auth_list.add(auth_two);
        return auth_list;
    }
    
    public List<String> getVpnCryptoList(){
        String crypto_one = "sm1";
        String crypto_two = "sm4";
        String crypto_three = "3des";
        List<String> crypto_list = new ArrayList<>();
        crypto_list.add(crypto_one);
        crypto_list.add(crypto_two);
        crypto_list.add(crypto_three);
        return crypto_list;
    }
    
    public List<String> getVpnDigestList(){
        String digest_one = "sm3";
        String digest_two = "sha1";
        String digest_three = "md5";
        List<String> digest_list = new ArrayList<>();
        digest_list.add(digest_one);
        digest_list.add(digest_two);
        digest_list.add(digest_three);
        return digest_list;
    }
    
    
    public  List<String> getTunelMode(){
        String tunelMode_one = "tunnel";
        String tunelMode_two = "forward";
        List<String> tunnel_list = new ArrayList<>();
        tunnel_list.add(tunelMode_one);
        tunnel_list.add(tunelMode_two);
        return tunnel_list;
    }
    
    
    public String getTimeStamp(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            tsStr = sdf.format(ts);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return tsStr;
    }


    public SecurityDeviceInfo getSecurityDeviceInfo() {
        return new SecurityDeviceInfo("zd","3.0","working");
    }
    
    public String getVpnType(){
        return "ssl";
    }
    
    public String getMessageFrom(){
        return "vpn";
    }
}
