package com.hzih.sslvpn.web.action.config;

import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.dao.ServerDao;
import com.hzih.sslvpn.domain.Server;
import com.hzih.sslvpn.domain.TrustTerminal;
import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.service.TrustTerminalService;
import com.hzih.sslvpn.utils.ShellUtils;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.AccountLogUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.hzih.sslvpn.web.action.audit.AuditFlagAction;
import com.hzih.sslvpn.web.action.boot.LoaderListener;
import com.hzih.sslvpn.web.servlet.SiteContextLoaderServlet;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 15-4-14.
 */
public class TrustTerminalAction extends ActionSupport {
    private Logger logger = Logger.getLogger(TrustTerminalAction.class);
    private TrustTerminalService trustTerminalService;
    private TrustTerminal trustTerminal;
    private LogService logService;
    private ServerDao serverDao;

    public ServerDao getServerDao() {
        return serverDao;
    }

    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public TrustTerminal getTrustTerminal() {
        return trustTerminal;
    }

    public void setTrustTerminal(TrustTerminal trustTerminal) {
        this.trustTerminal = trustTerminal;
    }

    public TrustTerminalService getTrustTerminalService() {
        return trustTerminalService;
    }

    public void setTrustTerminalService(TrustTerminalService trustTerminalService) {
        this.trustTerminalService = trustTerminalService;
    }

    public String add() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase base = new ActionBase();
        String result = base.actionBegin(request);
        String msg = null;
        String json = null;
        if (trustTerminal != null) {
            try {
                TrustTerminal target_exist = trustTerminalService.findByIpMac(trustTerminal.getIp(), trustTerminal.getMac());
                if (target_exist != null) {
                    msg = "目标记录已经在,不能新增";
                    json = "{success:false,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                        SiteContextLoaderServlet.sysLogService.offer(log);
                    }
                } else {
                    boolean flag = trustTerminalService.add(trustTerminal);
                    if (flag) {
                        msg = "添加信任终端成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "1", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    } else {
                        msg = "添加信任终端失败";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage(),e);
                msg = "添加信任终端失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg + e.getMessage());
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                    SiteContextLoaderServlet.sysLogService.offer(log);
                }
            }
        }
        base.actionEnd(response, json, result);
        return null;
    }

    public String mod() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase base = new ActionBase();
        String result = base.actionBegin(request);
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String msg = null;
        String json = null;
        if (trustTerminal != null && ip != null && mac != null&&startTime!=null&&endTime!=null) {
            if (trustTerminal.getIp().equals(ip)&& trustTerminal.getMac().equals(mac)) {
                if(trustTerminal.getStartTime().equals(startTime)&&trustTerminal.getEndTime().equals(endTime)){
                    msg = "信息未修改,无需更新";
                    json = "{success:false,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                        SiteContextLoaderServlet.sysLogService.offer(log);
                    }
                }else {
                    try {
                        TrustTerminal target_trust = trustTerminalService.findByIpMac(trustTerminal.getIp(), trustTerminal.getMac());
                        if(target_trust!=null){
                            target_trust.setStartTime(trustTerminal.getStartTime());
                            target_trust.setEndTime(trustTerminal.getEndTime());
                            trustTerminalService.modify(target_trust);
                            if(target_trust.getStatus()==1) {
                                Server server = serverDao.find();
                                String s_protocol = server.getProtocol();
                                int s_port = server.getPort();
                                ShellUtils.del_ipMac(ip, mac, s_protocol, String.valueOf(s_port), startTime, endTime);
                                ShellUtils.add_ipMac(trustTerminal.getIp(), trustTerminal.getMac(), s_protocol, String.valueOf(s_port), trustTerminal.getStartTime(), trustTerminal.getEndTime());
                            }
                            msg = "修改信任终端成功";
                            json = "{success:true,msg:'" + msg + "'}";
                            if(AuditFlagAction.getAuditFlag()) {
                                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                                SiteContextLoaderServlet.sysLogService.offer(log);
                            }
                        }else {
                            msg = "修改信任终端失败";
                            json = "{success:true,msg:'" + msg + "'}";
                            if(AuditFlagAction.getAuditFlag()) {
                                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                                SiteContextLoaderServlet.sysLogService.offer(log);
                            }
                        }
                    } catch (Exception e) {

//                        e.printStackTrace();
                        logger.error(e.getMessage(), e);
                        msg = "修改信任终端失败";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    }
                }
            } else {
                try {
                    TrustTerminal target_trust = trustTerminalService.findByIpMac(trustTerminal.getIp(), trustTerminal.getMac());
                    if (target_trust != null) {
                        msg = "目标记录已在在,无法更新";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    } else {
                        TrustTerminal trustT = trustTerminalService.findByIpMac(ip, mac);
                        if (trustT != null) {
                            Server server = serverDao.find();
                            trustT.setIp(trustTerminal.getIp());
                            trustT.setMac(trustTerminal.getMac());
                            trustT.setStartTime(trustTerminal.getStartTime());
                            trustT.setEndTime(trustTerminal.getEndTime());
                            boolean flag = trustTerminalService.modify(trustT);
                            if (flag) {
                                if (trustT.getStatus() == 1) {
                                    String s_protocol = server.getProtocol();
                                    int s_port = server.getPort();
                                    ShellUtils.del_ipMac(ip, mac, s_protocol, String.valueOf(s_port),startTime,endTime);
                                    ShellUtils.add_ipMac(trustTerminal.getIp(), trustTerminal.getMac(), s_protocol, String.valueOf(s_port),trustTerminal.getStartTime(),trustTerminal.getEndTime());
                                }
                                msg = "更新信任终端成功";
                                json = "{success:true,msg:'" + msg + "'}";
                                if(AuditFlagAction.getAuditFlag()) {
                                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "1", new Date());
                                    SiteContextLoaderServlet.sysLogService.offer(log);
                                }
                            } else {
                                msg = "更新信任终端失败";
                                json = "{success:false,msg:'" + msg + "'}";
                                if(AuditFlagAction.getAuditFlag()) {
                                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                                    SiteContextLoaderServlet.sysLogService.offer(log);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.info(e.getMessage(),e);
                    msg = "更新信任终端失败,出现异常";
                    json = "{success:false,msg:'" + msg + "'}";
                    if(AuditFlagAction.getAuditFlag()) {
                        logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg + e.getMessage());
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                        String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                        SiteContextLoaderServlet.sysLogService.offer(log);
                    }
                }
            }
        }
        base.actionEnd(response, json, result);
        return null;
    }

    public String del() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase base = new ActionBase();
        String result = base.actionBegin(request);
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String msg = null;
        String json = null;
        if (ip != null && mac != null) {
            try {
                TrustTerminal trustT = trustTerminalService.findByIpMac(ip, mac);
                if (trustT != null) {
                    boolean flag = trustTerminalService.delete(trustT);
                    if (flag) {
                        Server server = serverDao.find();
                        if (trustT.getStatus() == 1) {
                            String s_protocol = server.getProtocol();
                            int s_port = server.getPort();
                            ShellUtils.del_ipMac(ip, mac, s_protocol, String.valueOf(s_port),startTime,endTime);
                        }
                        msg = "删除信任终端成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "1", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    } else {
                        msg = "删除信任终端失败";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage(),e);
                msg = "删除信任终端失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg + e.getMessage());
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                    SiteContextLoaderServlet.sysLogService.offer(log);
                }
            }
        }
        base.actionEnd(response, json, result);
        return null;
    }

    public String enable() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase base = new ActionBase();
        String result = base.actionBegin(request);
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String msg = null;
        String json = null;
        if (ip != null && mac != null) {
            try {
                TrustTerminal trustT = trustTerminalService.findByIpMac(ip, mac);
                if (trustT != null) {
                    trustT.setStatus(1);
                    boolean flag = trustTerminalService.modify(trustT);
                    if (flag) {
                        Server server = serverDao.find();
                        if (trustT.getStatus() == 1) {
                            String s_protocol = server.getProtocol();
                            int s_port = server.getPort();
                            ShellUtils.add_ipMac(ip, mac, s_protocol, String.valueOf(s_port),startTime,endTime);
                        }
                        msg = "启用信任终端成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "1", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    } else {
                        msg = "启用信任终端失败";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage(),e);
                msg = "启用信任终端失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg + e.getMessage());
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                    SiteContextLoaderServlet.sysLogService.offer(log);
                }
            }
        }
        base.actionEnd(response, json, result);
        return null;
    }

    public String disable() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase base = new ActionBase();
        String result = base.actionBegin(request);
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String msg = null;
        String json = null;
        if (ip != null && mac != null) {
            try {
                TrustTerminal trustT = trustTerminalService.findByIpMac(ip, mac);
                if (trustT != null) {
                    Server server = serverDao.find();
                    if (trustT.getStatus() == 1) {
                        String s_protocol = server.getProtocol();
                        int s_port = server.getPort();
                        ShellUtils.del_ipMac(ip, mac, s_protocol, String.valueOf(s_port),startTime,endTime);
                    }
                    trustT.setStatus(0);
                    boolean flag = trustTerminalService.modify(trustT);
                    if (flag) {
                        msg = "停用信任终端成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "1", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    } else {
                        msg = "停用信任终端失败";
                        json = "{success:false,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                            SiteContextLoaderServlet.sysLogService.offer(log);
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage(),e);
                msg = "停用信任终端失败,出现异常";
                json = "{success:false,msg:'" + msg + "'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg + e.getMessage());
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "信任终端", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "信任终端", "info", "004", "0", new Date());
                    SiteContextLoaderServlet.sysLogService.offer(log);
                }
            }
        }
        base.actionEnd(response, json, result);
        return null;
    }

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String start = request.getParameter("start");
        String limit = request.getParameter("limit");
        String json = null;
        PageResult pageResult = trustTerminalService.findByPages(Integer.parseInt(start), Integer.parseInt(limit));
        if (pageResult != null) {
            List<TrustTerminal> list = pageResult.getResults();
            int count = pageResult.getAllResultsAmount();
            if (list != null) {
                json = "{success:true,total:" + count + ",rows:[";
                Iterator<TrustTerminal> raUserIterator = list.iterator();
                while (raUserIterator.hasNext()) {
                    TrustTerminal log = raUserIterator.next();
                    if (raUserIterator.hasNext()) {
                        json += "{" +
                                "ip:'" + log.getIp() +
                                "',mac:'" + log.getMac() +
                                "',id:'" + log.getId() +
                                "',startTime:'" + log.getStartTime() +
                                "',endTime:'" + log.getEndTime() +
                                "',status:'" + log.getStatus() + "'" +
                                "},";
                    } else {
                        json += "{" +
                                "ip:'" + log.getIp() +
                                "',mac:'" + log.getMac() +
                                "',id:'" + log.getId() +
                                "',startTime:'" + log.getStartTime() +
                                "',endTime:'" + log.getEndTime() +
                                "',status:'" + log.getStatus() + "'" +
                                "}";
                    }
                }
                json += "]}";
                actionBase.actionEnd(response, json, result);
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }
}
