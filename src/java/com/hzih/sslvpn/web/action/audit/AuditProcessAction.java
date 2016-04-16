/*
package com.hzih.sslvpn.web.action.audit;

import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * Created by Administrator on 15-4-24.
 *//*

public class AuditProcessAction extends ActionSupport {
    private Logger logger = Logger.getLogger(AuditProcessAction.class);

    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String find() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        int totalCount = 0;
        String s = ProcessXMLUtils.find();
        totalCount = totalCount + 1;
        StringBuilder json = new StringBuilder("[");
        json.append(s);
        json.append("]");
        try {
            actionBase.actionEnd(response, json.toString(), result);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    public String update() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String msg = "更新失败";
        String audit_flag = request.getParameter("audit_flag");
        if (audit_flag == null)
            audit_flag = "";
        String verity_flag = request.getParameter("verity_flag");
        if (verity_flag == null)
            verity_flag = "";
        String client_flag = request.getParameter("client_flag");
        if (client_flag == null)
            client_flag = "";
        String admin_flag = request.getParameter("admin_flag");
        if (admin_flag == null)
            admin_flag = "";
        String build_flag = request.getParameter("build_flag");
        if (build_flag == null)
            build_flag = "";
        String build_num_flag = request.getParameter("build_num_flag");
        if (build_num_flag == null)
            build_num_flag = "";
        String full_flag = request.getParameter("full_flag");
        if (full_flag == null)
            full_flag = "";
        String decode_flag = request.getParameter("decode_flag");
        if (decode_flag == null)
            decode_flag = "";
        String discard_flag = request.getParameter("discard_flag");
        if (discard_flag == null)
            discard_flag = "";
        String storage_flag = request.getParameter("storage_flag");
        if (storage_flag == null)
            storage_flag = "";
        String replay_flag = request.getParameter("replay_flag");
        if (replay_flag == null)
            replay_flag = "";
        boolean flag = ProcessXMLUtils.save(audit_flag, verity_flag, client_flag, admin_flag, build_flag, build_num_flag, full_flag, decode_flag, discard_flag, storage_flag, replay_flag);
        if (flag) {
            msg = "保存成功";
            if(AuditFlagAction.getAuditFlag()) {
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "用户更新审计备份策略信息成功");
            }
        } else {
            if(AuditFlagAction.getAuditFlag()) {
                logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "审计备份策略", "用户更新审计备份策略信息失败");
            }
            msg = "保存失败";
        }
        String json = "{success:true,msg:'" + msg + "'}";
        actionBase.actionEnd(response, json, result);
        return null;
    }
}
*/
