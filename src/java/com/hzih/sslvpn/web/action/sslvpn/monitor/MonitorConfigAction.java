package com.hzih.sslvpn.web.action.sslvpn.monitor;

import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.AccountLogUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.hzih.sslvpn.syslog.sender.SyslogSender;
import com.hzih.sslvpn.web.action.audit.AuditFlagAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-17
 * Time: 下午12:42
 * To change this template use File | Settings | File Templates.
 */
public class MonitorConfigAction extends ActionSupport {
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String getMonitorInfo() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        int totalCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        getResult(stringBuilder);
        totalCount = totalCount + 1;
        StringBuilder json = new StringBuilder("{totalCount:" + totalCount + ",root:[");
        json.append(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1));
        json.append("]}");
        try {
            actionBase.actionEnd(response, json.toString(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getResult(StringBuilder stringBuilder) {
        stringBuilder.append("{");
        stringBuilder.append("ip:'" + MonitorConfigXML.getAttribute(MonitorConfigXML.ip) + "',");
        stringBuilder.append("port:'" + MonitorConfigXML.getAttribute(MonitorConfigXML.port) + "',");
        stringBuilder.append("rpc_port:'" + MonitorConfigXML.getAttribute(MonitorConfigXML.rpc_port) + "'");
        stringBuilder.append("},");
    }


    public String saveMonitorInfo() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false}";
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        String rpc_port = request.getParameter("rpc_port");
        MonitorConfigXML.saveConfig(ip, port, rpc_port);
        String msg = "保存监控配置信息成功";
        json = "{success:true,msg:'"+msg+"'}";
        if(AuditFlagAction.getAuditFlag()) {
            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "监控管理", "info", "004", "0", new Date());
            SyslogSender.sysLog(log);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }
}
