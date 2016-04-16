package com.hzih.sslvpn.web.action.digest;

import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.domain.EquipmentLog;
import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.utils.DateUtils;
import com.hzih.sslvpn.utils.StringContext;
import com.hzih.sslvpn.utils.StringUtils;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.hzih.sslvpn.web.action.audit.AuditFlagAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 15-8-21.
 */
public class DigestAction extends ActionSupport {
    private Logger logger = Logger.getLogger(DigestAction.class);

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        StringBuilder json = new StringBuilder();
        File file = new File(StringContext.digestLog);
        if (file.exists()) {
            Runtime rt = Runtime.getRuntime();
            Process p = null;
            String[] cmd = {"sh", "-c", "tail -n " + 10 + " " + StringContext.digestLog + " | tac"};
            try {
                p = rt.exec(cmd);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
            if (p != null) {
                BufferedReader in = null;
                in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String str = null;
                int line = 0;
                try {
                    while ((str = in.readLine()) != null) {
                        line++;
                        logger.info("读取签名日志:" + str);
                        json.append(str).append("\n");
                    }

                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        actionBase.actionEnd(response, json.toString(), result);
        return null;
    }
}