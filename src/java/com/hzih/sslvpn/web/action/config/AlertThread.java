/*
package com.hzih.sslvpn.web.action.config;


import com.hzih.sslvpn.constant.AppConstant;
import com.hzih.sslvpn.utils.MailUtils;
import com.hzih.sslvpn.web.SiteContext;
import com.hzih.sslvpn.web.action.audit.AuditFlagAction;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * Created by Administrator on 15-4-24.
 *//*

public class AlertThread extends Thread {

    private Logger logger = Logger.getLogger(AlertThread.class);

    private List<Node> nodes;

    private AlertMessage message;

    public AlertThread(List<Node> nodes, AlertMessage message) {
        this.nodes = nodes;
        this.message = message;
    }

    public void processAlert(List<Node> nodes,AlertMessage message) {
        if(nodes!=null&&nodes.size()>0) {
            SAXReader reader = new SAXReader();
            String fileName = SiteContext.getInstance().contextRealPath + AppConstant.XML_ALERT_CONFIG_PATH;
            Document document = null;
            try {
                document = reader.read(new File(fileName));
            } catch (DocumentException e) {
                return;
            }
            try {
                Map<String, String> model = new HashMap<String, String>();
                Node tempNode = document.selectSingleNode("//config/mailconfig/server");
                model.put(tempNode.getName(), tempNode.getText());
                tempNode = document.selectSingleNode("//config/mailconfig/port");
                model.put(tempNode.getName(), tempNode.getText());
                tempNode = document.selectSingleNode("//config/mailconfig/email");
                model.put(tempNode.getName(), tempNode.getText());
                tempNode = document.selectSingleNode("//config/mailconfig/account");
                model.put(tempNode.getName(), tempNode.getText());
                tempNode = document.selectSingleNode("//config/mailconfig/password");
                model.put(tempNode.getName(), tempNode.getText());
                tempNode = document.selectSingleNode("//config/mailconfig/title");
                model.put(tempNode.getName(), tempNode.getText());
                tempNode = document.selectSingleNode("//config/smsconfig/smsnumber");
                model.put(tempNode.getName(), tempNode.getText());
                tempNode = document.selectSingleNode("//config/smsconfig/smstitle");
                model.put(tempNode.getName(), tempNode.getText());
                String emailServer = model.get("server");
                String port = model.get("port");
                String connectName = model.get("account");
                String password = model.get("password");
                boolean isNeedAuth = true;
                String fromEmail = model.get("email");
                boolean isSuccess = MailUtils.sendSimpleEmailList(emailServer, Integer.parseInt(port), connectName, password, isNeedAuth, fromEmail, nodes, null, null, "utf-8", message.getSubject(), message.getText());
                if (isSuccess) {
                    String msg = "报警信息发送成功!" + message.toString();
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info(msg);
                    }
                } else {
                    String msg = "报警信息发送失败!" + message.toString();
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info(msg);
                    }
                }
            }catch (Exception e){
                return;
            }
        }
    }

    @Override
    public void run() {
        if(nodes!=null&&nodes.size()>0){
            if(message!=null){
                processAlert(nodes,message);
            }
        }
    }
}
*/
