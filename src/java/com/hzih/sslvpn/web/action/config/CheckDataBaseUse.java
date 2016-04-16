/*
package com.hzih.sslvpn.web.action.config;

import com.hzih.sslvpn.constant.AppConstant;
import com.hzih.sslvpn.utils.StringContext;
import com.hzih.sslvpn.web.SiteContext;
import com.hzih.sslvpn.web.action.audit.StorageXMLUtils;
import com.inetec.common.util.OSInfo;
import com.inetec.common.util.Proc;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.*;

*/
/**
 * Created by Administrator on 15-4-24.
 *//*

public class CheckDataBaseUse {
    private Logger logger = Logger.getLogger(CheckDataBaseUse.class);

    public int alertDatabaseUse() {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(StringContext.storage_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            return -1;//未设置审计存储阀值
        }
        if (doc != null) {
            Element config = doc.getRootElement();
            Element slider_el = config.element(StorageXMLUtils.slider);
            Element slider_backup_el = slider_el.element(StorageXMLUtils.slider_backup);
            Element slider_process_el = slider_el.element(StorageXMLUtils.slider_process);
            String h = slider_backup_el.getText();
            String fileName = SiteContext.getInstance().contextRealPath + AppConstant.XML_DB_CONFIG_PATH;
            Document document = null;
            try {
                document = saxReader.read(new File(fileName));
                int diskUsed = getDataBaseAtDisk(document);            //审计库所在磁盘使用容量(%)
                if (diskUsed >= Integer.parseInt(h)) {//审计数据超出设置存储阀值
                    if(slider_process_el.getText().equals("0")){
                        return 0;//忽略审计数据
                    }else {
                        return 1;//覆盖最老的审计数据
                    }
                }
            } catch (DocumentException e) {
                return -1;
            }
        } else {
            return -1;//审计数据未超出设置存储阀值
        }
        return -1;//审计数据未超出设置存储阀值
    }


    private int getDataBaseAtDisk(Document doc) {
        String ip = doc.selectSingleNode("//config/maindb/dbip").getText();
        String port = doc.selectSingleNode("//config/maindb/dbport").getText();
        String dbName = "information_schema";
        String userName = doc.selectSingleNode("//config/maindb/username").getText();
        String password = doc.selectSingleNode("//config/maindb/password").getText();
        String dataDir = null;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + ip
                    + ":" + port + "/" + dbName
                    + "?useUnicode=true&characterEncoding=utf-8";
            conn = DriverManager.getConnection(url, userName, password);
            stat = conn.createStatement();
            rs = stat.executeQuery("select VARIABLE_VALUE from `GLOBAL_VARIABLES` where VARIABLE_NAME = 'datadir';");
            if (rs != null && rs.next()) {
                dataDir = rs.getString(1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                    rs = null;
                }
                if (null != stat) {
                    stat.close();
                    stat = null;
                }
                if (null != conn) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        int used = 100;
        Proc proc;
        OSInfo osinfo = OSInfo.getOSInfo();
        if (osinfo.isWin()) {
            used = 0;
        }
        if (osinfo.isLinux()) {
            proc = new Proc();
            proc.exec("df " + dataDir);
            String result = proc.getOutput();
            String[] lines = result.split("\n");
            String[] str = lines[1].split("\\s");
            for (int i = 0; i < str.length; i++) {
                if (str[i].endsWith("%")) {
                    used = Integer.parseInt(str[i].split("%")[0]);
                    logger.info("liunx database used "+used);
                }
            }
        }
        return used;
    }
}
*/
