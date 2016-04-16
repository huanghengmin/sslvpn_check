package com.hzih.sslvpn.utils;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-7
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class StringContext {
    public final static String service_name = "sslvpn";
    public final static String systemPath = System.getProperty(service_name+".home");
    public final static String INTERFACE_NAME = "interfaces";//linux下IP信息存储文件
    public final static String INTERFACE = "/etc/network/"+INTERFACE_NAME;//linux下IP信息存储文件
    public final static String IFSTATE_NAME = "ifstate"; //linux下DNS信息
    public final static String IFSTATE = "/etc/network/run/"+IFSTATE_NAME; //linux下DNS信息
    public final static String localLogPath = systemPath + "/logs";   //日志文件目录
    public final static String webPath = systemPath + "/tomcat/webapps"; //war服务文件存储目录
    public final static String tempPath = systemPath + "/tomcat/temp"; //缓存目录
    public final static String sql_path = systemPath + "/sql";//mysql 备份文件
    public final static String mysql_bak_sql = sql_path + "/mysqlbak.sql";//mysql 备份文件
    public final static String mysql_sql = sql_path + "/"+service_name+".sql";//mysql 备份文件
    public final static String hotConfig = systemPath + "/data/backup/hotconfig.xml";//双机热备备份文件
    public final static String serverLogPath = systemPath + "/server_logs";  //服务日志目录
    public final static String serverLog = serverLogPath + "/server.log";  //服务日志
    public final static String digestLog = serverLogPath + "/digest.log";  //签名日志
    public final static String server_sslPath = systemPath + "/pki";
    public final static String server_caPath = server_sslPath + "/ca";
    public final static String server_certPath = server_sslPath + "/cert";
    public final static String server_keyPath = server_sslPath + "/key";
    public final static String dh_file = server_sslPath + "/dh/dh1024.pem";
    public final static String ca_file = server_caPath + "/ca.pem";
    public final static String server_file = server_certPath + "/cert.pem";
    public final static String key_file = server_keyPath + "/key.pem";
    public final static String static_key_path = server_sslPath + "/static_key";
    public final static String ta_key_file = static_key_path + "/ta.key";
    public final static String crl_xml = systemPath + "/config/crl.xml";
    public final static String crl_path = server_sslPath + "/crl";
    public final static String crl_file = crl_path + "/crl.pem";
    public final static String ccd = systemPath + "/ccd";
    public final static String config_path = systemPath + "/config";
    public final static String server_config_path = systemPath + "/server_config";
    public final static String client_config_path = systemPath + "/client_config";
    public final static String pool_path = systemPath + "/pool";
    public final static String pool_file = pool_path + "/ipp.txt";
    public final static String server_bak_config_path = systemPath + "/source";
    public final static String script_path = systemPath + "/script";
    public final static String server_config_file = server_config_path + "/server.conf";
    public final static String server_config_file_md5 = server_bak_config_path + "/server.conf.md5";
    public final static String server_config_file_bak = server_bak_config_path + "/server.conf.bak";
    public final static String server_des_config_file = server_bak_config_path + "/server.conf.export";
    public final static String server_des_config_file_md5 = server_bak_config_path + "/server.conf.export.md5";
    public final static String android_config_file = client_config_path + "/android.ovpn";
    public final static String windows_config_file = client_config_path + "/windows.ovpn";
    public final static String ldap_xml = config_path + "/ldap.xml";
    public final static String strategy_xml = config_path + "/strategy.xml";
    public final static String syslog_xml = config_path + "/syslog.xml";
    public final static String SECURITY_CONFIG = systemPath + "/tomcat/conf/server.xml";
    public final static String acl_xml = config_path + "/acl_config.xml";
    public final static String monitor_xml = config_path + "/monitor_config.xml";
    public final static String rules_xml = config_path + "/rules.xml";
    public final static String datamakers_xml = config_path + "/datamakers.xml";
    public final static String crl_check_xml = config_path + "/crl_check_xml.xml";
    public final static String audit_flag_xml = config_path + "/audit_flag.xml";
    public final static String syslogserver_xml = config_path + "/syslogserver.xml";
    public final static String storage_xml = config_path + "/storage.xml";
    public final static String process_xml = config_path + "/process.xml";
    public final static String software_bin = systemPath + "/bin/openvpn";
    public final static String software_bin_bak = server_bak_config_path + "/openvpn";

}
