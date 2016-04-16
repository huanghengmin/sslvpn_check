package com.hzih.sslvpn.web.action.sslvpn.set;

import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.dao.NatDao;
import com.hzih.sslvpn.domain.Nat;
import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.syslog.sender.SyslogSender;
import com.hzih.sslvpn.utils.Dom4jUtil;
import com.hzih.sslvpn.utils.ShellUtils;
import com.hzih.sslvpn.utils.StringContext;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.AccountLogUtils;
import com.hzih.sslvpn.web.action.ActionBase;
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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 15-4-14.
 */
public class NatAction extends ActionSupport {
    private Logger logger = Logger.getLogger(NatAction.class);
    private NatDao natDao;
    private Nat nat;
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public Nat getNat() {
        return nat;
    }

    public void setNat(Nat nat) {
        this.nat = nat;
    }

    public NatDao getNatDao() {
        return natDao;
    }

    public void setNatDao(NatDao natDao) {
        this.natDao = natDao;
    }

    private int start;
    private int limit;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String add() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'保存失败'}";
        String msg = null;
        if (nat != null) {
            Nat old = natDao.findByBind(nat.getBindIp(),nat.getBindPort(),nat.getProtocol());
            if (null != old) {
                msg = "资源已在在";
                json = "{success:false,msg:'" + msg + "'}";
              /*  if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "资源管理", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "资源管理", "info", "004", "1", new Date());
                    SyslogSender.sysLog(log);
                }*/
            } else {
                boolean flag  = natDao.add(nat);
                if(flag) {
                    Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
                    if (doc != null) {
                        Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" +
                                old.getBindIp() + "'][@bindPort='" + old.getBindPort() + "'][@protocol='" + old.getProtocol() + "']");
                        if (selectSingleNode != null) {
                            Attribute e_bindIp = selectSingleNode.attribute("bindIp");
                            Attribute e_bindPort = selectSingleNode.attribute("bindPort");
                            Attribute e_protocol = selectSingleNode.attribute("protocol");
                            Attribute e_targetIp = selectSingleNode.attribute("targetIp");
                            Attribute e_targetPort = selectSingleNode.attribute("targetPort");
                            Attribute e_level = selectSingleNode.attribute("level");
                            Attribute e_count = selectSingleNode.attribute("count");
                            e_bindIp.setValue(nat.getBindIp());
                            e_bindPort.setValue(nat.getBindPort());
                            e_protocol.setValue(nat.getProtocol());
                            e_targetIp.setValue(nat.getTargetIp());
                            e_targetPort.setValue(nat.getTargetPort());
                            e_level.setValue(String.valueOf(nat.getLevel()));
                            e_count.setValue(String.valueOf(nat.getCount()));
                            Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                            ShellUtils.add_rule(nat.getProtocol(), nat.getBindPort(), nat.getBindIp());
                            msg = "添加规则成功";
                            json = "{success:true,msg:'" + msg + "'}";
                            if (AuditFlagAction.getAuditFlag()) {
                                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                                SyslogSender.sysLog(log);
                            }
                        }else {
                            Element rootElement = doc.getRootElement();
                            Element tunnel = rootElement.element("tunnel");
                            if(tunnel==null) {
                                tunnel = rootElement.addElement("tunnel");
                                tunnel.addAttribute("tunnel", "1");
                                tunnel.addAttribute("datamark", "1");
                            }else {
                                Attribute tunnelAttri = tunnel.attribute("tunnel");
                                tunnelAttri.setValue("1");
                                Attribute datamarkAttri = tunnel.attribute("datamark");
                                datamarkAttri.setValue("1");
                            }
                            Element service = tunnel.addElement("service");
                            service.addAttribute("bindIp", nat.getBindIp());
                            service.addAttribute("bindPort", nat.getBindPort());
                            service.addAttribute("protocol", nat.getProtocol());
                            service.addAttribute("level", String.valueOf(nat.getLevel()));
                            service.addAttribute("targetIp", nat.getTargetIp());
                            service.addAttribute("targetPort", nat.getTargetPort());
                            service.addAttribute("count", String.valueOf(nat.getCount()));
                            Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                            ShellUtils.add_rule(nat.getProtocol(), nat.getBindPort(), nat.getBindIp());
                            msg = "添加规则成功";
                            json = "{success:true,msg:'" + msg + "'}";
                            if (AuditFlagAction.getAuditFlag()) {
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
                        tunnel.addAttribute("tunnel", "1");
                        tunnel.addAttribute("datamark", "1");
                        Element service_el = tunnel.addElement("service");
                        service_el.addAttribute("bindIp", nat.getBindIp());
                        service_el.addAttribute("bindPort", nat.getBindPort());
                        service_el.addAttribute("protocol", nat.getProtocol());
                        service_el.addAttribute("level", String.valueOf(nat.getLevel()));
                        service_el.addAttribute("targetIp", nat.getTargetIp());
                        service_el.addAttribute("targetPort", nat.getTargetPort());
                        service_el.addAttribute("count", String.valueOf(nat.getCount()));
                        OutputFormat format = OutputFormat.createPrettyPrint();
                        format.setEncoding("utf-8");
                        format.setIndent(true);
                        try {
                            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.rules_xml)), format);
                            try {
                                xmlWriter.write(document);
                                msg = "添加规则成功";
                                ShellUtils.add_rule(nat.getProtocol(), nat.getBindPort(), nat.getBindIp());
                                json = "{success:true,msg:'" + msg + "'}";
                                if(AuditFlagAction.getAuditFlag()) {
                                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                                    SyslogSender.sysLog(log);
                                }
                            } catch (IOException e) {
//                                logger.info(e.getMessage());
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
//                                    logger.info(e.getMessage());
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
//                            logger.info(e.getMessage());
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
//                            logger.info(e.getMessage());
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
                }
//                msg = "资源添加成功";
//                json = "{success:true,msg:'" + msg + "!'}";
//                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
//                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "资源管理", msg);
//                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "资源管理", "info", "004", "0", new Date());
//                SyslogSender.sysLog(log);
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String modify() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'更新失败'}";
        String msg = null;
        String id = request.getParameter("id");
        Nat old = natDao.findById(Integer.parseInt(id));
        if (null != old) {
            if (old.getBindIp().equals(nat.getBindIp())
                    && old.getBindPort().equals(nat.getBindPort())
                    && old.getTargetIp().equals(nat.getTargetIp())
                    && old.getTargetPort().equals(nat.getTargetPort())
                    && old.getProtocol().equals(nat.getProtocol())
                    && old.getLevel() == nat.getLevel()
                    &&old.getCount()==nat.getCount()) {
//                json = "{success:false,msg:'未修改数据,无需更新'}";
                msg = "未修改数据,无需更新";
                json = "{success:false,msg:'" + msg + "!'}";
                /*if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "资源管理", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "资源管理", "info", "004", "0", new Date());
                    SyslogSender.sysLog(log);
                }*/
            } else if (old.getBindIp().equals(nat.getBindIp())
                    &&old.getBindPort().equals(nat.getBindPort())
                    &&old.getProtocol().equals(nat.getProtocol())) {
                old.setTargetIp(nat.getTargetIp());
                old.setTargetPort(nat.getTargetPort());
                old.setLevel(nat.getLevel());
                old.setCount(nat.getCount());
                boolean flag = natDao.modify(old);
                if(flag){
                    Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
                    Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" +
                            old.getBindIp() + "'][@bindPort='" + old.getBindPort() + "'][@protocol='" + old.getProtocol() + "']");
                    if (selectSingleNode != null) {
                        Attribute e_targetIp = selectSingleNode.attribute("targetIp");
                        Attribute e_targetPort = selectSingleNode.attribute("targetPort");
                        Attribute e_level = selectSingleNode.attribute("level");
                        Attribute e_count = selectSingleNode.attribute("count");
                        e_targetIp.setValue(nat.getTargetIp());
                        e_targetPort.setValue(nat.getTargetPort());
                        e_level.setValue(String.valueOf(nat.getLevel()));
                        e_count.setValue(String.valueOf(nat.getCount()));
                        Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                        ShellUtils.del_rule(old.getProtocol(),old.getBindPort(),old.getBindIp());
                        ShellUtils.add_rule(nat.getProtocol(),nat.getBindPort(),nat.getBindIp());
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
//                json = "{success:true,msg:'更新成功'}";
//                msg = "更新成功";
//                json = "{success:true,msg:'" + msg + "!'}";
//                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
//                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "资源管理", msg);
//                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "资源管理", "info", "004", "1", new Date());
//                SyslogSender.sysLog(log);
            } else {
                Nat net = natDao.findByBind(nat.getBindIp(),nat.getBindPort(),nat.getProtocol());
                if (null != net) {
//                    json = "{success:false,msg:'网络已存在不允许更新到指定网络" + sourceNet.getNet() + "'}";
                    msg = "协议资源已在在，绑定地址:" + nat.getBindIp()+",绑定端口:"+nat.getBindPort()+",协议:"+nat.getProtocol();
                    json = "{success:false,msg:'" + msg + "!'}";
                    /*if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "资源管理", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "资源管理", "info", "004", "0", new Date());
                        SyslogSender.sysLog(log);
                    }*/
                } else {
                    String oldBind =  old.getBindIp();
                    String oldBindPort =  old.getBindPort();
                    String oldProtocol =  old.getProtocol();
                    old.setBindIp(nat.getBindIp());
                    old.setBindPort(nat.getBindPort());
                    old.setProtocol(nat.getProtocol());
                    old.setTargetIp(nat.getTargetIp());
                    old.setTargetPort(nat.getTargetPort());
                    old.setLevel(nat.getLevel());
                    old.setCount(nat.getCount());
                    boolean flag = natDao.modify(old);
                    if(flag){
                        Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
                        Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" + oldBind
                                + "'][@bindPort='" + oldBindPort + "'][@protocol='" + oldProtocol + "']");
                        if (selectSingleNode != null) {
                            Attribute bindIp_attr = selectSingleNode.attribute("bindIp");
                            bindIp_attr.setValue(nat.getBindIp());
                            Attribute bindPort_attr = selectSingleNode.attribute("bindPort");
                            bindPort_attr.setValue(nat.getBindPort());
                            Attribute protocol_attr = selectSingleNode.attribute("protocol");
                            protocol_attr.setValue(nat.getProtocol());
                            Attribute level_attr = selectSingleNode.attribute("level");
                            level_attr.setValue(String.valueOf(nat.getLevel()));
                            Attribute count_attr = selectSingleNode.attribute("count");
                            count_attr.setValue(String.valueOf(nat.getCount()));
                            Attribute targetIp_attr = selectSingleNode.attribute("targetIp");
                            targetIp_attr.setValue(nat.getTargetIp());
                            Attribute targetPort_attr = selectSingleNode.attribute("targetPort");
                            targetPort_attr.setValue(nat.getTargetPort());
                            Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                            ShellUtils.del_rule(oldProtocol, oldBindPort, oldBind);
                            ShellUtils.add_rule(nat.getProtocol(), nat.getBindPort(), nat.getBindIp());
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
//                    json = "{success:true,msg:'更新成功'}";
//                    msg = "更新成功";
//                    json = "{success:true,msg:'" + msg + "!'}";
//                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
//                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "资源管理", msg);
//                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "资源管理", "info", "004", "1", new Date());
//                    SyslogSender.sysLog(log);
                }
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String remove() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'删除失败'}";
        String msg = null;
        String id = request.getParameter("id");
        Nat old = natDao.findById(Integer.parseInt(id));
        boolean flag = natDao.delete(new Nat(Integer.parseInt(id)));
        if(flag){
            Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
            if (doc != null) {
                Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" +
                        old.getBindIp() + "'][@bindPort='" + old.getBindPort() + "'][@protocol='" + old.getProtocol() + "']");
                if (selectSingleNode != null) {
                    Element root = doc.getRootElement();
                    Element tunnele =root.element("tunnel");
                    boolean r = tunnele.remove(selectSingleNode);
                    if (r) {
                        Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                        ShellUtils.del_rule(old.getProtocol(), old.getBindPort(), old.getBindIp());
                        msg = "删除成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "规则列表", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "规则列表", "info", "004", "1", new Date());
                            SyslogSender.sysLog(log);
                        }
                    } else {
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
            }
        }
//        json = "{success:true,msg:'删除成功'}";
//        msg = "删除成功";
//        json = "{success:true,msg:'" + msg + "!'}";
//        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
//        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "资源管理", msg);
//        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "资源管理", "info", "004", "1", new Date());
//        SyslogSender.sysLog(log);
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        int pageIndex = start / limit + 1;
        PageResult pageResult = natDao.listByPage(pageIndex, limit);
        if (pageResult != null) {
            List<Nat> list = pageResult.getResults();
            int count = pageResult.getAllResultsAmount();
            if (list != null) {
                String json = "{success:true,total:" + count + ",rows:[";
                Iterator<Nat> raUserIterator = list.iterator();
                while (raUserIterator.hasNext()) {
                    Nat log = raUserIterator.next();
                    if (raUserIterator.hasNext()) {
                        json += "{" +
                                "id:'" + log.getId() +
                                "',bindip:'" + log.getBindIp() +
                                "',bindport:'" + log.getBindPort() +
                                "',level:'" + log.getLevel() +
                                "',count:'" + log.getCount() +
                                "',targetip:'" + log.getTargetIp() +
                                "',targetport:'" + log.getTargetPort() +
                                "',protocol:'" + log.getProtocol() + "'" +
                                "},";
                    } else {
                        json += "{" +
                                "id:'" + log.getId() +
                                "',bindip:'" + log.getBindIp() +
                                "',bindport:'" + log.getBindPort() +
                                "',level:'" + log.getLevel() +
                                "',count:'" + log.getCount() +
                                "',targetip:'" + log.getTargetIp() +
                                "',targetport:'" + log.getTargetPort() +
                                "',protocol:'" + log.getProtocol() + "'" +
                                "}";
                    }
                }
                json += "]}";
                actionBase.actionEnd(response, json, result);
            }
        }
        return null;
    }
}
