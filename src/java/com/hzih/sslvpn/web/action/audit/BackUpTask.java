/*
package com.hzih.sslvpn.web.action.audit;

import com.hzih.sslvpn.utils.FileUtil;
import com.hzih.sslvpn.utils.StringContext;
import com.hzih.sslvpn.web.action.audit.ftp.FtpUpload;
import com.hzih.sslvpn.web.action.sslvpn.backup.mysql.MysqlBakUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.dom4j.Element;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

public class BackUpTask extends TimerTask {

    FtpUpload ftpUpload = new FtpUpload();

    private Logger logger = Logger.getLogger(BackUpServlet.class);

    private Element local_backup_flag_el;
    private Element local_backup_path_el;
    private Element ftp_backup_flag_el;
    private Element ftp_host_el;
    private Element ftp_port_el;
    private Element ftp_user_el;
    private Element ftp_pass_el;
    private Element ftp_path_el;


    public BackUpTask(Element local_backup_flag_el, Element local_backup_path_el, Element ftp_backup_flag_el, Element ftp_host_el, Element ftp_port_el, Element ftp_user_el, Element ftp_pass_el, Element ftp_path_el) {

        this.local_backup_flag_el = local_backup_flag_el;
        this.local_backup_path_el = local_backup_path_el;
        this.ftp_backup_flag_el = ftp_backup_flag_el;
        this.ftp_host_el = ftp_host_el;
        this.ftp_port_el = ftp_port_el;
        this.ftp_user_el = ftp_user_el;
        this.ftp_pass_el = ftp_pass_el;
        this.ftp_path_el = ftp_path_el;
    }

    @Override
    public void run() {
        logger.info("执行 BackUp 任务,时间:"+new Date());
        try {
            MysqlBakUtils.backup();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        if (this.local_backup_flag_el.getText().equals("on")) {
            File f = new File(StringContext.mysql_bak_sql);
            if (f.exists() && f.length() > 0) {
                try {
                    File f_b =  new File(local_backup_flag_el.getText());
                    if(f_b.exists())
                        FileUtil.copy(f, local_backup_path_el.getText() + "/" + f.getName());
                    else {
                        f_b.mkdirs();
                        logger.info("创建本地备份路径:"+local_backup_path_el.getText());
                        FileUtil.copy(f, local_backup_path_el.getText() + "/" + f.getName());
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }

        if (this.ftp_backup_flag_el.equals("on")) {
            File f = new File(StringContext.mysql_bak_sql);
            if (f.exists() && f.length() > 0) {
                try {
                    FTPClient ftpClient = ftpUpload.connect(ftp_path_el.getText(), ftp_host_el.getText(), Integer.parseInt(ftp_port_el.getText()), ftp_user_el.getText(), ftp_pass_el.getText());
                    ftpUpload.upload(ftpClient, f);
                    ftpClient.disconnect();
                    ftpClient.logout();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}*/
