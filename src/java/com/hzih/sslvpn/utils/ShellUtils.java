package com.hzih.sslvpn.utils;

import com.inetec.common.util.OSInfo;
import com.inetec.common.util.Proc;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 15-5-5.
 */
public class ShellUtils {
    private static Logger logger = Logger.getLogger(ShellUtils.class);
    /**
     * 添加规则
     * @param protocol
     * @param port
     * @param ip
     * @return
     */
    public static boolean add_rule(String protocol,String port,String ip) {
        Proc proc = new Proc();
        String command = null;
        logger.info("添加规则 ip:"+ip+",port:"+port+",protocol:"+protocol);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/rule_add.bat " +
                    protocol + " " +
                    ip+ " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/rule_add.sh " +
                    protocol + " " +
                    ip+ " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }


    /**
     * 删除规则
     * @param protocol
     * @param port
     * @param ip
     * @return
     */
    public static boolean del_rule(String protocol,String port,String ip) {
        Proc proc = new Proc();
        String command = null;
        logger.info("删除规则 ip:"+ip+",port:"+port+",protocol:"+protocol);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/rule_del.bat " +
                    protocol + " " +
                    ip+ " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/rule_del.sh " +
                    protocol + " " +
                    ip+ " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }

    /**
     * 添加信任终端
     * @param ip
     * @param mac
     * @param server_protocol
     * @param server_port
     * @return
     */
    public static boolean add_ipMac(String ip,String mac,String server_protocol,String server_port,String startTime,String endTime) {
        if(startTime.contains(":")){
            String[] startTimes =startTime.split(":");
            if(startTimes!=null&&startTimes.length>0){
                int i = Integer.parseInt(startTimes[0]);
                i=i-8;
                if(i<0){
                    i=i+24;
                }
                String sI = String.valueOf(i);
                if(sI.length()<=1){
                    sI = "0"+sI;
                    startTime = sI+":"+startTimes[1]+":"+startTimes[2];
                }else{
                    startTime = sI+":"+startTimes[1]+":"+startTimes[2];
                }
            }
        }


        if(endTime.contains(":")){
            String[] endTimes =endTime.split(":");
            if(endTimes!=null&&endTimes.length>0){
                int i = Integer.parseInt(endTimes[0]);
                i=i-8;
                if(i<0){
                    i=i+24;
                }
                String sI = String.valueOf(i);
                if(sI.length()<=1){
                    sI = "0"+sI;
                    endTime = sI+":"+endTimes[1]+":"+endTimes[2];
                }else{
                    endTime = sI+":"+endTimes[1]+":"+endTimes[2];
                }
            }
        }

        Proc proc = new Proc();
        String command = null;
        logger.info("添加可信终端 ip:"+ip+",mac:"+mac+",server_protocol:"+server_protocol+",server_port:"+server_port);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/ip_mac_add.bat " +
                    ip + " " +
                    mac+ " " +
                    server_protocol+ " " +
                    server_port+ " " +
                    startTime+ " " +
                    endTime;
        } else {
            command = StringContext.systemPath + "/script/ip_mac_add.sh " +
                    ip + " " +
                    mac+ " " +
                    server_protocol+ " " +
                    server_port+ " " +
                    startTime+ " " +
                    endTime;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }




    /**
     * 删除信任终端
     * 注：北京时间比UTC时间多8个小时，startTime,endTime时间修改
     * @param ip
     * @param mac
     * @param server_protocol
     * @param server_port
     * @return
     */
    public static boolean del_ipMac(String ip,String mac,String server_protocol,String server_port,String startTime,String endTime) {
        if(startTime.contains(":")){
            String[] startTimes =startTime.split(":");
            if(startTimes!=null&&startTimes.length>0){
                int i = Integer.parseInt(startTimes[0]);
                i=i-8;
                if(i<0){
                    i=i+24;
                }
                String sI = String.valueOf(i);
                if(sI.length()<=1){
                    sI = "0"+sI;
                    startTime = sI+":"+startTimes[1]+":"+startTimes[2];
                }else{
                    startTime = sI+":"+startTimes[1]+":"+startTimes[2];
                }
            }
        }


        if(endTime.contains(":")){
            String[] endTimes =endTime.split(":");
            if(endTimes!=null&&endTimes.length>0){
                int i = Integer.parseInt(endTimes[0]);
                i=i-8;
                if(i<0){
                    i=i+24;
                }
                String sI = String.valueOf(i);
                if(sI.length()<=1){
                    sI = "0"+sI;
                    endTime = sI+":"+endTimes[1]+":"+endTimes[2];
                }else{
                    endTime = sI+":"+endTimes[1]+":"+endTimes[2];
                }
            }
        }
        Proc proc = new Proc();
        String command = null;
        logger.info("删除可信终端 ip:"+ip+",mac:"+mac+",server_protocol:"+server_protocol+",server_port:"+server_port);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/ip_mac_del.bat " +
                    ip + " " +
                    mac+ " " +
                    server_protocol+ " " +
                    server_port+ " " +
                    startTime+ " " +
                    endTime;
        } else {
            command = StringContext.systemPath + "/script/ip_mac_del.sh " +
                    ip + " " +
                    mac+ " " +
                    server_protocol+ " " +
                    server_port+ " " +
                    startTime+ " " +
                    endTime;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }


    /**
     * 开启SSLVPN服务
     * @param net_mask
     * @param protocol
     * @param port
     * @return
     */
    public static boolean firewall_start(String net_mask,String protocol,String port) {
        Proc proc = new Proc();
        String command = null;
        logger.info("开启SSLVPN服务脚本 net_mask:"+net_mask+",protocol:"+protocol+",port:"+port);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/firewall_start.bat " +
                    net_mask + " " +
                    protocol+ " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/firewall_start.sh " +
                    net_mask + " " +
                    protocol+ " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }


    /**
     * 关闭SSLVPN服务
     * @param net_mask
     * @param protocol
     * @param port
     * @return
     */
    public static boolean firewall_stop(String net_mask,String protocol,String port) {
        Proc proc = new Proc();
        String command = null;
        logger.info("终止SSLVPN服务脚本 net_mask:"+net_mask+",protocol:"+protocol+",port:"+port);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/firewall_stop.bat " +
                    net_mask + " " +
                    protocol+ " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/firewall_stop.sh " +
                    net_mask + " " +
                    protocol+ " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }


    /**
     * 添加syslog_server
     * @param protocol
     * @param port
     * @return
     */
    public static boolean add_syslog_server(String protocol,String port) {
        Proc proc = new Proc();
        String command = null;
        logger.info("新增 syslog_server port:"+port+",protocol:"+protocol);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/syslog_server_start.bat " +
                    protocol + " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/syslog_server_start.sh " +
                    protocol + " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }


    /**
     * 删除 syslog_server
     * @param protocol
     * @param port
     * @return
     */
    public static boolean del_syslog_server(String protocol,String port) {
        Proc proc = new Proc();
        String command = null;
        logger.info("删除 syslog_server port:"+port+",protocol:"+protocol);
        if (OSInfo.getOSInfo().isWin()) {
            command = StringContext.systemPath + "/script/syslog_server_stop.bat " +
                    protocol + " " +
                    port;
        } else {
            command = StringContext.systemPath + "/script/syslog_server_stop.sh " +
                    protocol + " " +
                    port;
        }
        proc.exec(command);
        if (proc.getResultCode() != -1) {
            if(!proc.getErrorOutput().contains("error")&&!proc.getErrorOutput().contains("Error")){
                return true;
            } else {
                logger.equals(proc.getErrorOutput());
            }
        }
        return false;
    }
}
