/*
package com.hzih.sslvpn.web.action.audit;

import com.hzih.sslvpn.utils.StringContext;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

*/
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-16
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 *//*

public class BackUpServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(BackUpServlet.class);

    private Timer timer = null;

    */
/**
     * Destruction of the servlet.
     *//*


    public void destroy() {
        super.destroy();
        if (timer != null) {
            timer.cancel();
        }
    }


    @Override
    public void init() throws ServletException {
        super.init();
        timer = new Timer(true);
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(StringContext.storage_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        if (doc != null) {
            Element config = doc.getRootElement();

            Element local_backup_el = config.element(StorageXMLUtils.local_backup);
            Element local_backup_flag_el = local_backup_el.element(StorageXMLUtils.local_backup_flag);
            Element local_backup_path_el = local_backup_el.element(StorageXMLUtils.local_backup_path);

            Element ftp_backup_el = config.element(StorageXMLUtils.ftp_backup);
            Element ftp_backup_flag_el = ftp_backup_el.element(StorageXMLUtils.ftp_backup_flag);
            Element ftp_host_el = ftp_backup_el.element(StorageXMLUtils.ftp_host);
            Element ftp_port_el = ftp_backup_el.element(StorageXMLUtils.ftp_port);
            Element ftp_user_el = ftp_backup_el.element(StorageXMLUtils.ftp_user);
            Element ftp_pass_el = ftp_backup_el.element(StorageXMLUtils.ftp_pass);
            Element ftp_path_el = ftp_backup_el.element(StorageXMLUtils.ftp_path);

            Element backup_el = config.element(StorageXMLUtils.backup);
            Element backup_flag_el = backup_el.element(StorageXMLUtils.backup_flag);
            Element conf_type_el = backup_el.element(StorageXMLUtils.conf_type);
            Element conf_time_el = backup_el.element(StorageXMLUtils.conf_time);
            Element conf_day_el = backup_el.element(StorageXMLUtils.conf_day);
            Element conf_time2_el = backup_el.element(StorageXMLUtils.conf_time2);
            Element conf_month_day_el = backup_el.element(StorageXMLUtils.conf_month_day);
            Element conf_time3_el = backup_el.element(StorageXMLUtils.conf_time3);
            if (backup_flag_el.getText().equals("on")) {
                Calendar calendar = Calendar.getInstance();
                String conf_type = conf_type_el.getText();
                if (conf_type.equals("day")) {
                    String conf_time = conf_time_el.getText();
                    if (conf_time.contains(":")) {
                        String[] ss = conf_time.split(":");
                        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ss[0]));
                        calendar.set(Calendar.MINUTE, Integer.parseInt(ss[1].substring(0, 1)));
                        calendar.set(Calendar.SECOND, Integer.parseInt(ss[1].substring(1, 2)));
                    }
                } else if (conf_type.equals("week")) {
                    String conf_day = conf_day_el.getText();
                    String conf_time2 = conf_time2_el.getText();
                    calendar.set(Calendar.DAY_OF_WEEK, Integer.parseInt(conf_day));
                    if (conf_time2.contains(":")) {
                        String[] ss = conf_time2.split(":");
                        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ss[0]));
                        calendar.set(Calendar.MINUTE, Integer.parseInt(ss[1].substring(0, 1)));
                        calendar.set(Calendar.SECOND, Integer.parseInt(ss[1].substring(1, 2)));
                    }
                } else if (conf_type.equals("month")) {
                    String conf_month_day = conf_month_day_el.getText();
                    String conf_time3 = conf_time3_el.getText();
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(conf_month_day));
                    if (conf_time3.contains(":")) {
                        String[] ss = conf_time3.split(":");
                        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ss[0]));
                        calendar.set(Calendar.MINUTE, Integer.parseInt(ss[1].substring(0, 1)));
                        calendar.set(Calendar.SECOND, Integer.parseInt(ss[1].substring(1, 2)));
                    }
                }
                Date time = calendar.getTime();
                timer.schedule(new BackUpTask(local_backup_flag_el,local_backup_path_el,ftp_backup_flag_el,ftp_host_el,ftp_port_el,ftp_user_el,ftp_pass_el,ftp_path_el), time);
            } else {
                return;
            }
        } else {
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

*/
