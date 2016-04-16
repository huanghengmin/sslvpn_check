/*
package com.hzih.sslvpn.web.action.sslvpn.ccd;

import com.hzih.sslvpn.dao.UserDao;
import com.hzih.sslvpn.domain.User;
import com.hzih.sslvpn.entity.X509User;
import com.hzih.sslvpn.entity.mapper.X509UserAttributeMapper;
import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.utils.StringContext;
import com.hzih.sslvpn.utils.VPNConfigUtil;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.AccountLogUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.hzih.sslvpn.web.action.sslvpn.ldap.*;
import com.hzih.sslvpn.syslog.sender.SyslogSender;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class ImPortUserAction extends ActionSupport {

    private Logger logger = Logger.getLogger(ImPortUserAction.class);

    private UserDao userDao;

    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String ImportUser() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String msg = null;
        String json = null;
        int i = 0;
        DirContext ctx = new LdapUtils().getCtx();
        if (ctx != null) {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration en = null;
            try {
                en = ctx.search(LdapXMLUtils.getValue(LdapXMLUtils.base), "(objectClass=" + X509User.getObjAttr() + ")", constraints);
            } catch (NamingException e) {
                logger.error(e.getMessage());
            }
            X509UserAttributeMapper mapper = new X509UserAttributeMapper();
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    i++;
                    if (i % 50 == 0) {
                        try {
                            userDao.flushSession();
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (si != null) {
                        User x509User = null;
                        try {
                            x509User = mapper.mapFromAttributes(si);
                        } catch (NamingException e) {
                            logger.error(e.getMessage());
                        }
                        if (x509User != null) {
                            User u = null;
                            try {
                                u = userDao.findByCommonName(x509User.getCn());
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                            }
                            boolean flag = false;
                            try {
                                if (u != null) {
                                    i++;
                                    x509User.setId(u.getId());
                                    flag = userDao.modify(x509User);
                                } else {
                                    i++;
                                    flag = userDao.add(x509User);
                                }
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                                flag = false;
                            }
                            if (flag) {
                                try {
                                    VPNConfigUtil.configUser(x509User, StringContext.ccd);
                                } catch (Exception e) {
                                    logger.error(e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
            msg = "批量导入用户成功!";
            json = "{success:true,msg:'" + msg + "'}";
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "批量导入", msg);
            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "批量导入", "info", "004", "1", new Date());
            SyslogSender.sysLog(log);
        } else {
            msg = "LDAP服务器连通失败!";
            json = "{success:false,msg:'" + msg + "'}";
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "批量导入", msg);
            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "批量导入", "info", "004", "0", new Date());
            SyslogSender.sysLog(log);
        }
        LdapUtils.close(ctx);
        actionBase.actionEnd(response, json, result);
        return null;
    }

}


*/
