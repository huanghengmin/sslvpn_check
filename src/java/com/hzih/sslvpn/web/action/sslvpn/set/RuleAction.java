package com.hzih.sslvpn.web.action.sslvpn.set;

import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.utils.Dom4jUtil;
import com.hzih.sslvpn.utils.ShellUtils;
import com.hzih.sslvpn.utils.StringContext;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.AccountLogUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.hzih.sslvpn.syslog.sender.SyslogSender;
import com.hzih.sslvpn.web.action.audit.AuditFlagAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-4-16.
 */
public class RuleAction extends ActionSupport {
    private Logger logger = Logger.getLogger(RuleAction.class);
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
        StringBuilder sb = new StringBuilder();
        if (doc != null) {
            List<Element> services = doc.selectNodes("/config/tunnel/service");
            if (services != null) {
                sb.append("{success:true,'list':").append(services.size()).append(",'rows':[");
                for (int i = 0; i < services.size(); i++) {
                    Element element = services.get(i);
                    String bindIp = element.attributeValue("bindIp");
                    String bindPort = element.attributeValue("bindPort");
                    String protocol = element.attributeValue("protocol");
                    String targetIp = element.attributeValue("targetIp");
                    String level = element.attributeValue("level");
                    String targetPort = element.attributeValue("targetPort");
                    sb.append("{");
                    sb.append("targetIp:'").append(targetIp).append("'").append(",");
                    sb.append("targetPort:'").append(targetPort).append("'").append(",");
                    sb.append("protocol:'").append(protocol).append("'").append(",");
                    sb.append("level:'").append(level).append("'").append(",");
                    sb.append("bindIp:'").append(bindIp).append("'").append(",");
                    sb.append("bindPort:'").append(bindPort).append("'");
                    sb.append("}");
                    if (i != (services.size() - 1)) {
                        sb.append(",");
                    }
                }
                sb.append("]}");
            }
        }
        actionBase.actionEnd(response, sb.toString(), result);
        return null;
    }

    public String add() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
        String bindIp = request.getParameter("bindIp");
        String bindPort = request.getParameter("bindPort");
        String protocol = request.getParameter("protocol");
        String level = request.getParameter("level");
        String targetIp = request.getParameter("targetIp");
        String targetPort = request.getParameter("targetPort");
        String msg = null;
        String json = null;
        if (doc != null) {
            Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" + bindIp + "'][@bindPort='" + bindPort + "'][@protocol='" + protocol + "']");
            if (selectSingleNode != null) {
                msg = "添加规则冲突,绑定地址、端口、协议、必须唯一";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            } else {
                Element rootElement = doc.getRootElement();
                Element tunnel = rootElement.addElement("tunnel");
                tunnel.addAttribute("tunnel","1");
                tunnel.addAttribute("datamark", "1");
                Element service = tunnel.addElement("service");
                service.addAttribute("bindIp", bindIp);
                service.addAttribute("bindPort", bindPort);
                service.addAttribute("protocol", protocol);
                service.addAttribute("level", level);
                service.addAttribute("targetIp", targetIp);
                service.addAttribute("targetPort", targetPort);
                Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                ShellUtils.add_rule(protocol,bindPort,bindIp);
                msg = "添加规则成功";
                json = "{success:true,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        } else {
            Document document = DocumentHelper.createDocument();
            Element config = document.addElement("config");
            Element tunnel = config.addElement("tunnel");
            tunnel.addAttribute("tunnel","1");
            tunnel.addAttribute("datamark", "1");
            Element service_el = tunnel.addElement("service");
            service_el.addAttribute("bindIp", bindIp);
            service_el.addAttribute("bindPort", bindPort);
            service_el.addAttribute("protocol", protocol);
            service_el.addAttribute("level", level);
            service_el.addAttribute("targetIp", targetIp);
            service_el.addAttribute("targetPort", targetPort);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            format.setIndent(true);
            try {
                XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.rules_xml)), format);
                try {
                    xmlWriter.write(document);
                    msg = "添加规则成功";
                    ShellUtils.add_rule(protocol,bindPort,bindIp);
                    json = "{success:true,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                        SyslogSender.sysLog(log);
                    }
                } catch (IOException e) {
//                    logger.info(e.getMessage());
                    msg = "添加规则失败,出现异常";
                    json = "{success:false,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info(e.getMessage(),e);
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                        SyslogSender.sysLog(log);
                    }
                } finally {
                    try {
                        xmlWriter.flush();
                        xmlWriter.close();
                    } catch (IOException e) {
//                        logger.info(e.getMessage());
                        msg = "添加规则失败,出现异常";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info(e.getMessage(),e);
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
//                logger.info(e.getMessage());
                msg = "添加规则失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info(e.getMessage(),e);
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            } catch (FileNotFoundException e) {
//                logger.info(e.getMessage());
                msg = "添加规则失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info(e.getMessage(),e);
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String del() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
        String bindIp = request.getParameter("bindIp");
        String bindPort = request.getParameter("bindPort");
        String protocol = request.getParameter("protocol");
//        String targetIp = request.getParameter("targetIp");
//        String targetPort = request.getParameter("targetPort");
        String msg = null;
        String json = null;
        if (doc != null) {
            Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" + bindIp + "'][@bindPort='" + bindPort + "'][@protocol='" + protocol + "']");
            if (selectSingleNode != null) {
                Element root = doc.getRootElement();
                boolean flag =  root.remove(selectSingleNode);
               if(flag) {
                   Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                   ShellUtils.del_rule(protocol, bindPort, bindIp);
                   msg = "删除成功";
                   json = "{success:true,msg:'" + msg + "'}";
                   if(AuditFlagAction.getAuditFlag()) {
                       logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                       logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                       String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                       SyslogSender.sysLog(log);
                   }
               }else {
                   msg = "删除失败";
                   json = "{success:false,msg:'" + msg + "'}";
                   if(AuditFlagAction.getAuditFlag()) {
                       logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                       logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                       String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                       SyslogSender.sysLog(log);
                   }
               }
            } else {
                msg = "删除失败,未找到指定规则";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        } else {
            msg = "删除失败,未找到指定规则";
            json = "{success:false,msg:'" + msg + "'}";
            if(AuditFlagAction.getAuditFlag()) {
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                SyslogSender.sysLog(log);
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }


    public String mod() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
        String bindIp = request.getParameter("bindIp");
        String bindPort = request.getParameter("bindPort");
        String protocol = request.getParameter("protocol");
        String targetIp = request.getParameter("targetIp");
        String targetPort = request.getParameter("targetPort");
        String level = request.getParameter("level");
        String oldBindIp = request.getParameter("oldBindIp");
        String oldBindPort = request.getParameter("oldBindPort");
        String oldProtocol = request.getParameter("oldProtocol");
        String msg = null;
        String json = null;
        if (doc != null) {
            Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" + oldBindIp + "'][@bindPort='" + oldBindPort + "'][@protocol='" + oldProtocol + "']");
            if (selectSingleNode != null) {
                if (oldBindIp.equals(bindIp) && oldBindPort.equals(bindPort) && oldProtocol.equals(protocol)) {
                    Attribute e_targetIp = selectSingleNode.attribute("targetIp");
                    Attribute e_targetPort = selectSingleNode.attribute("targetPort");
                    Attribute e_level = selectSingleNode.attribute("level");
                    if (e_targetIp.getValue().equals(targetIp) && e_targetPort.getValue().equals(targetPort)&&e_level.getValue().equals(level)) {
                        msg = "信息未修改,无须更新";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                            SyslogSender.sysLog(log);
                        }
                    } else {
                        e_targetIp.setValue(targetIp);
                        e_targetPort.setValue(targetPort);
                        e_level.setValue(level);
                        Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                        ShellUtils.del_rule(oldProtocol,oldBindPort,oldBindIp);
                        ShellUtils.add_rule(protocol,bindPort,bindIp);
                        msg = "更新成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                } else {
                    Element singleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" + bindIp + "'][@bindPort='" + bindPort + "'][@protocol='" + protocol + "']");
                    if (singleNode != null) {
                        msg = "目标记录已在在";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                            SyslogSender.sysLog(log);
                        }
                    } else {
//                        doc.remove(selectSingleNode);
//                        Element rootElement = doc.getRootElement();
//                        Element service = rootElement.addElement("service");
                        Attribute bindIp_attr = selectSingleNode.attribute("bindIp");
                        bindIp_attr.setValue(bindIp);
                        Attribute bindPort_attr = selectSingleNode.attribute("bindPort");
                        bindPort_attr.setValue(bindPort);
                        Attribute protocol_attr = selectSingleNode.attribute("protocol");
                        protocol_attr.setValue(protocol);
                        Attribute level_attr = selectSingleNode.attribute("level");
                        level_attr.setValue(level);
                        Attribute targetIp_attr = selectSingleNode.attribute("targetIp");
                        targetIp_attr.setValue(targetIp);
                        Attribute targetPort_attr = selectSingleNode.attribute("targetPort");
                        targetPort_attr.setValue(targetPort);
                        Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                        ShellUtils.del_rule(oldProtocol,oldBindPort,oldBindIp);
                        ShellUtils.add_rule(protocol,bindPort,bindIp);
                        msg = "更新成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                }
            } else {
                msg = "更新失败,未找到指定规则";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        } else {
            msg = "更新失败,未找到指定规则";
            json = "{success:false,msg:'" + msg + "'}";
            if(AuditFlagAction.getAuditFlag()) {
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "0", new Date());
                SyslogSender.sysLog(log);
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }
}
