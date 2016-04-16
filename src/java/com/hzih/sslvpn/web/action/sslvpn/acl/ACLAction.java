package com.hzih.sslvpn.web.action.sslvpn.acl;

import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.web.action.AccountLogUtils;
import com.hzih.sslvpn.syslog.sender.SyslogSender;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.hzih.sslvpn.web.action.audit.AuditFlagAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-18
 * Time: 下午7:56
 * To change this template use File | Settings | File Templates.
 */
public class ACLAction extends ActionSupport {

    private Logger logger = Logger.getLogger(ACLAction.class);

    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String saveConfig() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = null;
        String msg = null;
        String status = request.getParameter("status");
        if (status == null)
            status = "0";
        String control_url = request.getParameter("url");
        String proxy_port = request.getParameter("proxy_port");
        String proxy_ip = request.getParameter("proxy_ip");
        String port = request.getParameter("port");
        boolean flag = ACLXML.saveConfig(status, control_url, port, proxy_ip, proxy_port);
        if (flag) {
            msg = "保存访问控制信息成功";
            json = "{success:true,msg:'" + msg + "'}";
            if(AuditFlagAction.getAuditFlag()) {
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "访问控制", msg);
                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "访问控制", "info", "004", "1", new Date());
                SyslogSender.sysLog(log);
            }
        } else {
            msg = "保存访问控制信息失败";
            json = "{success:false,msg:'" + msg + "'}";
            if(AuditFlagAction.getAuditFlag()) {
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "访问控制", msg);
                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "访问控制", "info", "004", "0", new Date());
                SyslogSender.sysLog(log);
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String selectControl() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        int totalCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            getData(stringBuilder);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        totalCount = totalCount + 1;
        StringBuilder json = new StringBuilder("{totalCount:" + totalCount + ",root:[");
        json.append(stringBuilder);
        json.append("]}");
        try {
            actionBase.actionEnd(response, json.toString(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getData(StringBuilder stringBuilder) {
        stringBuilder.append("{");
        if (ACLXML.getAttribute(ACLXML.status) != null)
            stringBuilder.append("status:'" + ACLXML.getAttribute(ACLXML.status) + "',");
        else
            stringBuilder.append("status:'',");

        if (ACLXML.getAttribute(ACLXML.port) != null)
            stringBuilder.append("port:'" + ACLXML.getAttribute(ACLXML.port) + "',");
        else
            stringBuilder.append("port:'',");

        if (ACLXML.getAttribute(ACLXML.proxy_ip) != null)
            stringBuilder.append("proxy_ip:'" + ACLXML.getAttribute(ACLXML.proxy_ip) + "',");
        else
            stringBuilder.append("proxy_ip:'',");

        if (ACLXML.getAttribute(ACLXML.proxy_port) != null)
            stringBuilder.append("proxy_port:'" + ACLXML.getAttribute(ACLXML.proxy_port) + "',");
        else
            stringBuilder.append("proxy_port:'',");

        if (ACLXML.getAttribute(ACLXML.url) != null)
            stringBuilder.append("url:'" + ACLXML.getAttribute(ACLXML.url) + "'");
        else
            stringBuilder.append("url:''");
        stringBuilder.append("}");
    }
}
