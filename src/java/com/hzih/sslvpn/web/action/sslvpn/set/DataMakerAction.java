package com.hzih.sslvpn.web.action.sslvpn.set;

import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.utils.Dom4jUtil;
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
public class DataMakerAction extends ActionSupport {
    private Logger logger = Logger.getLogger(DataMakerAction.class);
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
        Document doc = Dom4jUtil.getDocument(StringContext.datamakers_xml);
        StringBuilder sb = new StringBuilder();
        if (doc != null) {
            List<Element> services = doc.selectNodes("/config/datamarker");
            if (services != null) {
                sb.append("{success:true,'list':").append(services.size()).append(",'rows':[");
                for (int i = 0; i < services.size(); i++) {
                    Element element = services.get(i);
                    String sourceip = element.attributeValue("sourceip");
                    String sourceport = element.attributeValue("sourceport");
                    String sourcemask = element.attributeValue("sourcemask");
                    String targetip = element.attributeValue("targetip");
                    String targetmask = element.attributeValue("targetmask");
                    String targetport = element.attributeValue("targetport");
                    String protocol = element.attributeValue("protocol");
                    String sensitivelevel = element.attributeValue("sensitivelevel");
                    String processmode = element.attributeValue("processmode");
//                    Element e_sensitivelevel = element.element("sensitivelevel");
//                    String sensitivelevel = e_sensitivelevel.getText();
//                    Element e_processmode = element.element("processmode");
//                    String processmode = e_processmode.getText();
                    sb.append("{");
                    sb.append("sourceip:'").append(sourceip).append("'").append(",");
                    sb.append("sourceport:'").append(sourceport).append("'").append(",");
                    sb.append("sourcemask:'").append(sourcemask).append("'").append(",");
                    sb.append("targetip:'").append(targetip).append("'").append(",");
                    sb.append("targetmask:'").append(targetmask).append("'").append(",");
                    sb.append("targetport:'").append(targetport).append("'").append(",");
                    sb.append("protocol:'").append(protocol).append("'").append(",");
                    sb.append("sensitivelevel:'").append(sensitivelevel).append("'").append(",");
                    sb.append("processmode:'").append(processmode).append("'");
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
        Document doc = Dom4jUtil.getDocument(StringContext.datamakers_xml);
        String sourceip = request.getParameter("sourceip");
        String sourceport = request.getParameter("sourceport");
        String sourcemask = request.getParameter("sourcemask");
        String targetip = request.getParameter("targetip");
        String targetport = request.getParameter("targetport");
        String targetmask = request.getParameter("targetmask");
        String protocol = request.getParameter("protocol");
        String sensitivelevel = request.getParameter("sensitivelevel");
        String processmode = request.getParameter("processmode");
        String msg = null;
        String json = null;
        if (doc != null) {
            Element selectSingleNode = (Element) doc.selectSingleNode("/config/datamarker[@sourceip='" + sourceip + "'][@sourceport='" + sourceport + "'][@sourcemask='" + sourcemask + "'][@targetip='" + targetip + "'][@targetport='" + targetport + "'][@targetmask='" + targetmask + "'][@protocol='" + protocol + "']");
            if (selectSingleNode != null) {
                msg = "添加标记冲突,绑定地址、端口、协议、必须唯一";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            } else {
                Element rootElement = doc.getRootElement();
                Element datamarker = rootElement.addElement("datamarker");
                datamarker.addAttribute("sourceip", sourceip);
                datamarker.addAttribute("sourceport", sourceport);
                datamarker.addAttribute("sourcemask", sourcemask);
                datamarker.addAttribute("targetip", targetip);
                datamarker.addAttribute("targetport", targetport);
                datamarker.addAttribute("targetmask", targetmask);
                datamarker.addAttribute("protocol", protocol);
                datamarker.addAttribute("sensitivelevel", sensitivelevel);
                datamarker.addAttribute("processmode", processmode);
//                Element e_sensitivelevel = datamarker.addElement("sensitivelevel");
//                e_sensitivelevel.setText(sensitivelevel);
//                Element e_processmode = datamarker.addElement("processmode");
//                e_processmode.setText(processmode);
                Dom4jUtil.writeDocumentToFile(doc, StringContext.datamakers_xml);
                msg = "添加标记成功";
                json = "{success:true,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "1", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        } else {
            Document document = DocumentHelper.createDocument();
            Element config = document.addElement("config");
            Element datamarker_el = config.addElement("datamarker");
            datamarker_el.addAttribute("sourceip", sourceip);
            datamarker_el.addAttribute("sourceport", sourceport);
            datamarker_el.addAttribute("sourcemask", sourcemask);
            datamarker_el.addAttribute("targetip", targetip);
            datamarker_el.addAttribute("targetport", targetport);
            datamarker_el.addAttribute("targetmask", targetmask);
            datamarker_el.addAttribute("protocol", protocol);
            datamarker_el.addAttribute("sensitivelevel", sensitivelevel);
            datamarker_el.addAttribute("processmode", processmode);
//            Element e_sensitivelevel = datamarker_el.addElement("sensitivelevel");
//            e_sensitivelevel.setText(sensitivelevel);
//            Element e_processmode = datamarker_el.addElement("processmode");
//            e_processmode.setText(processmode);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            format.setIndent(true);
            try {
                XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.datamakers_xml)), format);
                try {
                    xmlWriter.write(document);
                    msg = "添加标记成功";
                    json = "{success:true,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "1", new Date());
                        SyslogSender.sysLog(log);
                    }
                } catch (IOException e) {
//                    logger.info(e.getMessage());
                    msg = "添加标记失败,出现异常";
                    json = "{success:false,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info(e.getMessage(),e);
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                        SyslogSender.sysLog(log);
                    }
                } finally {
                    try {
                        xmlWriter.flush();
                        xmlWriter.close();
                    } catch (IOException e) {
//                        logger.info(e.getMessage());
                        msg = "添加标记失败,出现异常";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info(e.getMessage(),e);
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
//                logger.info(e.getMessage());
                msg = "添加标记失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info(e.getMessage(),e);
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            } catch (FileNotFoundException e) {
//                logger.info(e.getMessage());
                msg = "添加标记失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info(e.getMessage(),e);
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
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
        Document doc = Dom4jUtil.getDocument(StringContext.datamakers_xml);
        String sourceip = request.getParameter("sourceip");
        String sourceport = request.getParameter("sourceport");
        String sourcemask = request.getParameter("sourcemask");
        String targetip = request.getParameter("targetip");
        String targetport = request.getParameter("targetport");
        String targetmask = request.getParameter("targetmask");
        String protocol = request.getParameter("protocol");
//        String sensitivelevel = request.getParameter("sensitivelevel");
//        String processmode = request.getParameter("processmode");
        String msg = null;
        String json = null;
        if (doc != null) {
            Element selectSingleNode = (Element) doc.selectSingleNode("/config/datamarker[@sourceip='" + sourceip + "'][@sourceport='" + sourceport + "'][@sourcemask='" + sourcemask + "'][@targetip='" + targetip + "'][@targetport='" + targetport + "'][@targetmask='" + targetmask + "'][@protocol='" + protocol + "']");
            if (selectSingleNode != null) {
                Element root = doc.getRootElement();
                boolean flag = root.remove(selectSingleNode);
                if(flag) {
                    Dom4jUtil.writeDocumentToFile(doc, StringContext.datamakers_xml);
                    msg = "删除标记成功";
                    json = "{success:true,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "1", new Date());
                        SyslogSender.sysLog(log);
                    }
                }else {
                    msg = "删除标记失败";
                    json = "{success:false,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                        SyslogSender.sysLog(log);
                    }
                }
            } else {
                msg = "删除标记失败,未找到指定规则";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        } else {
            msg = "删除标记失败,未找到指定规则";
            json = "{success:false,msg:'" + msg + "'}";
            if(AuditFlagAction.getAuditFlag()) {
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
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
        Document doc = Dom4jUtil.getDocument(StringContext.datamakers_xml);
        String sourceip = request.getParameter("sourceip");
        String sourceport = request.getParameter("sourceport");
        String sourcemask = request.getParameter("sourcemask");
        String targetip = request.getParameter("targetip");
        String targetport = request.getParameter("targetport");
        String targetmask = request.getParameter("targetmask");
        String protocol = request.getParameter("protocol");
        String oldSourceip = request.getParameter("oldSourceip");
        String oldSourceport = request.getParameter("oldSourceport");
        String oldSourcemask = request.getParameter("oldSourcemask");
        String oldTargetip = request.getParameter("oldTargetip");
        String oldTargetmask = request.getParameter("oldTargetmask");
        String oldTargetport = request.getParameter("oldTargetport");
        String oldProtocol = request.getParameter("oldProtocol");
        String sensitivelevel = request.getParameter("sensitivelevel");
        String processmode = request.getParameter("processmode");
        String msg = null;
        String json = null;
        if (doc != null) {
            Element selectSingleNode = (Element) doc.selectSingleNode("/config/datamarker[@sourceip='" + oldSourceip + "'][@sourceport='" + oldSourceport + "'][@sourcemask='" + oldSourcemask + "'][@targetip='" + oldTargetip + "'][@targetport='" + oldTargetport + "'][@targetmask='" + oldTargetmask + "'][@protocol='" + oldProtocol + "']");
            if (selectSingleNode != null) {
                if (oldSourceip.equals(sourceip) && oldSourceport.equals(sourceport) && oldSourcemask.equals(sourcemask)
                        &&oldTargetip.equals(targetip)&&oldTargetport.equals(targetport)&&oldTargetmask.equals(targetmask)
                        &&oldProtocol.equals(protocol)) {
//                    Element e_sensitivelevel = selectSingleNode.element("sensitivelevel");
//                    Element e_processmode = selectSingleNode.element("processmode");
                    if (selectSingleNode.attributeValue("sensitivelevel").equals(sensitivelevel) &&
                            selectSingleNode.attributeValue("processmode").equals(processmode)) {
                        msg = "信息未修改,无须更新";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                            SyslogSender.sysLog(log);
                        }
                    } else {
                        Attribute sensitivelevel_attr = selectSingleNode.attribute("sensitivelevel");
                        Attribute processmode_attr =selectSingleNode.attribute("processmode");
                        sensitivelevel_attr.setValue(sensitivelevel);
                        processmode_attr.setValue(processmode);
//                        e_sensitivelevel.setText(sensitivelevel);
//                        e_processmode.setText(processmode);
                        Dom4jUtil.writeDocumentToFile(doc, StringContext.datamakers_xml);
                        msg = "更新成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "1", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                } else {
                    Element sSingleNode = (Element) doc.selectSingleNode("/config/datamarker[@sourceip='" + sourceip + "'][@sourceport='" + sourceport + "'][@sourcemask='" + sourcemask + "'][@targetip='" + targetip + "'][@targetport='" + targetport + "'][@targetmask='" + targetmask + "'][@protocol='" + protocol + "']");
                    if (sSingleNode != null) {
                        msg = "目标记录已在在";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                            SyslogSender.sysLog(log);
                        }
                    } else {
//                        doc.remove(selectSingleNode);
//                        Element rootElement = doc.getRootElement();
//                        Element datamarker = rootElement.addElement("datamarker");
                        Attribute sourceip_attr = selectSingleNode.attribute("sourceip");
                        sourceip_attr.setValue(sourceip);
                        Attribute sourceport_attr = selectSingleNode.attribute("sourceport");
                        sourceport_attr.setValue(sourceport);
                        Attribute sourcemask_attr = selectSingleNode.attribute("sourcemask");
                        sourcemask_attr.setValue(sourcemask);
                        Attribute targetip_attr = selectSingleNode.attribute("targetip");
                        targetip_attr.setValue(targetip);
                        Attribute targetport_attr = selectSingleNode.attribute("targetport");
                        targetport_attr.setValue(targetport);
                        Attribute targetmask_attr = selectSingleNode.attribute("targetmask");
                        targetmask_attr.setValue(targetmask);
                        Attribute protocol_attr = selectSingleNode.attribute("protocol");
                        protocol_attr.setValue(protocol);
                        Attribute sensitivelevel_attr = selectSingleNode.attribute("sensitivelevel");
                        sensitivelevel_attr.setValue(sensitivelevel);
                        Attribute processmode_attr = selectSingleNode.attribute("processmode");
                        processmode_attr.setValue(processmode);
//                        Element e_sensitivelevel = selectSingleNode.element("sensitivelevel");
//                        e_sensitivelevel.setText(sensitivelevel);
//                        Element e_processmode = selectSingleNode.element("processmode");
//                        e_processmode.setText(processmode);
                        Dom4jUtil.writeDocumentToFile(doc, StringContext.datamakers_xml);
                        msg = "更新成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "1", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                }
            } else {
                msg = "更新失败,未找到指定规则";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        } else {
            msg = "更新失败,未找到指定规则";
            json = "{success:false,msg:'" + msg + "'}";
            if(AuditFlagAction.getAuditFlag()) {
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "数据标记", msg);
                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "数据标记", "info", "004", "0", new Date());
                SyslogSender.sysLog(log);
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }
}
