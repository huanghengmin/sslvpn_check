/*
package com.hzih.sslvpn.web.action.audit;

import com.hzih.sslvpn.utils.StringContext;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class StorageXMLUtils {
    private static Logger logger = Logger.getLogger(StorageXMLUtils.class);
    public static final String config = "config";
    public static final String slider = "slider";
    public static final String slider_backup = "slider_backup";
    public static final String slider_process = "slider_process";
    public static final String local_backup = "local_backup";
    public static final String local_backup_flag = "local_backup_flag";
    public static final String local_backup_path = "local_backup_path";
    public static final String ftp_backup = "ftp_backup";
    public static final String ftp_backup_flag = "ftp_backup_flag";
    public static final String ftp_host = "ftp_host";
    public static final String ftp_port = "ftp_port";
    public static final String ftp_user = "ftp_user";
    public static final String ftp_pass = "ftp_pass";
    public static final String ftp_path = "ftp_path";
    public static final String backup = "backup";
    public static final String backup_flag = "backup_flag";
    public static final String conf_type = "conf_type";
    public static final String conf_time = "conf_time";
    public static final String conf_day = "conf_day";
    public static final String conf_time2 = "conf_time2";
    public static final String conf_month_day = "conf_month_day";
    public static final String conf_time3 = "conf_time3";
    public static final String charset = "utf-8";


    public static String find() {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(StringContext.storage_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        if(doc!=null){
            Element config = doc.getRootElement();

            Element slider_el = config.element(slider);
            Element slider_backup_el = slider_el.element(slider_backup);
            Element slider_process_el = slider_el.element(slider_process);

            Element local_backup_el = config.element(local_backup);
            Element local_backup_flag_el = local_backup_el.element(local_backup_flag);
            Element local_backup_path_el = local_backup_el.element(local_backup_path);

            Element ftp_backup_el = config.element(ftp_backup);
            Element ftp_backup_flag_el = ftp_backup_el.element(ftp_backup_flag);
            Element ftp_host_el = ftp_backup_el.element(ftp_host);
            Element ftp_port_el = ftp_backup_el.element(ftp_port);
            Element ftp_user_el = ftp_backup_el.element(ftp_user);
            Element ftp_pass_el = ftp_backup_el.element(ftp_pass);
            Element ftp_path_el = ftp_backup_el.element(ftp_path);

            Element backup_el = config.element(backup);
            Element backup_flag_el = backup_el.element(backup_flag);
            Element conf_type_el = backup_el.element(conf_type);
            Element conf_time_el = backup_el.element(conf_time);
            Element conf_day_el = backup_el.element(conf_day);
            Element conf_time2_el = backup_el.element(conf_time2);
            Element conf_month_day_el = backup_el.element(conf_month_day);
            Element conf_time3_el = backup_el.element(conf_time3);

            StringBuilder sb = new StringBuilder();

            sb.append("{");
            sb.append("slider_backup:'"+ slider_backup_el.getText()+"',");
            sb.append("slider_process:'"+slider_process_el.getText() +"',");
            sb.append("local_backup:'"+ local_backup_flag_el.getText() + "',");
            sb.append("local_backup_path:'"+local_backup_path_el.getText()  + "',");
            sb.append("ftp_backup:'"+ ftp_backup_flag_el.getText() + "',");
            sb.append("ftp_host:'"+ftp_host_el.getText()  + "',");
            sb.append("ftp_port:'"+ ftp_port_el.getText() + "',");
            sb.append("ftp_user:'"+ ftp_user_el.getText() + "',");
            sb.append("ftp_pass:'"+ftp_pass_el.getText() +"',");
            sb.append("ftp_path:'"+ ftp_path_el.getText()+"',");
            sb.append("backup_flag:'"+backup_flag_el.getText() +"',");
            sb.append("conf_type:'"+conf_type_el.getText() +"',");
            sb.append("conf_time:'"+ conf_time_el.getText()+"',");
            sb.append("conf_day:'"+conf_day_el.getText() +"',");
            sb.append("conf_time2:'"+conf_time2_el.getText()+"',");
            sb.append("conf_month_day:'"+conf_month_day_el.getText()+"',");
            sb.append("conf_time3:'"+conf_time3_el.getText()+"'");
            sb.append("}");
            return sb.toString();
        }
        return null;
    }

    public static boolean save(
            String slider_backup,
            String slider_process,
            String local_backup,
            String local_backup_path,
            String ftp_backup,
            String ftp_host,
            String ftp_port,
            String ftp_user,
            String ftp_pass,
            String ftp_path,
            String backup_flag,
            String conf_type,
            String conf_time,
            String conf_day,
            String conf_time2,
            String conf_month_day,
            String conf_time3) {
        boolean flag = false;
        Document doc = DocumentHelper.createDocument();
        Element config = doc.addElement(StorageXMLUtils.config);

        Element slider_el = config.addElement(StorageXMLUtils.slider);

        Element slider_backup_el =  slider_el.addElement(StorageXMLUtils.slider_backup);
        slider_backup_el.setText(slider_backup);
        Element slider_process_el =  slider_el.addElement(StorageXMLUtils.slider_process);
        slider_process_el.setText(slider_process);

        Element local_backup_el = config.addElement(StorageXMLUtils.local_backup);

        Element local_backup_flag_el =  local_backup_el.addElement(StorageXMLUtils.local_backup_flag);
        local_backup_flag_el.setText(local_backup);

        Element local_backup_path_el =  local_backup_el.addElement(StorageXMLUtils.local_backup_path);
        local_backup_path_el.setText(local_backup_path);

        Element ftp_backup_el = config.addElement(StorageXMLUtils.ftp_backup);

        Element ftp_backup_flag_el =  ftp_backup_el.addElement(StorageXMLUtils.ftp_backup_flag);
        ftp_backup_flag_el.setText(ftp_backup);
        Element ftp_host_el =  ftp_backup_el.addElement(StorageXMLUtils.ftp_host);
        ftp_host_el.setText(ftp_host);
        Element ftp_port_el =  ftp_backup_el.addElement(StorageXMLUtils.ftp_port);
        ftp_port_el.setText(ftp_port);
        Element ftp_user_el =  ftp_backup_el.addElement(StorageXMLUtils.ftp_user);
        ftp_user_el.setText(ftp_user);
        Element ftp_pass_el =  ftp_backup_el.addElement(StorageXMLUtils.ftp_pass);
        ftp_pass_el.setText(ftp_pass);
        Element ftp_path_el =  ftp_backup_el.addElement(StorageXMLUtils.ftp_path);
        ftp_path_el.setText(ftp_path);

        Element backup_el = config.addElement(StorageXMLUtils.backup);

        Element backup_flag_el =  backup_el.addElement(StorageXMLUtils.backup_flag);
        backup_flag_el.setText(backup_flag);
        Element conf_type_el =  backup_el.addElement(StorageXMLUtils.conf_type);
        conf_type_el.setText(conf_type);
        Element conf_time_el =  backup_el.addElement(StorageXMLUtils.conf_time);
        conf_time_el.setText(conf_time);
        Element conf_day_el =  backup_el.addElement(StorageXMLUtils.conf_day);
        conf_day_el.setText(conf_day);
        Element conf_time2_el =  backup_el.addElement(StorageXMLUtils.conf_time2);
        conf_time2_el.setText(conf_time2);
        Element conf_month_day_el =  backup_el.addElement(StorageXMLUtils.conf_month_day);
        conf_month_day_el.setText(conf_month_day);
        Element conf_time3_el =  backup_el.addElement(StorageXMLUtils.conf_time3);
        conf_time3_el.setText(conf_time3);
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        format.setIndent(true);
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.storage_xml)), format);
            try {
                xmlWriter.write(doc);
                flag = true;
            } catch (IOException e) {
                logger.info(e.getMessage());
            } finally {
                try {
                    xmlWriter.flush();
                    xmlWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
        }
        return flag;
    }
}


*/
