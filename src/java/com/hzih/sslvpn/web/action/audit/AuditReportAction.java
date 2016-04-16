/*
package com.hzih.sslvpn.web.action.audit;

import com.hzih.sslvpn.dao.AccountLogDao;
import com.hzih.sslvpn.dao.ClientLogDao;
import com.hzih.sslvpn.domain.AccountLog;
import com.hzih.sslvpn.domain.ClientLog;
import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

*/
/**
 * Created by Administrator on 15-4-23.
 *//*

public class AuditReportAction extends ActionSupport {
    private LogService logService;
    private AccountLogDao accountLogDao;
    private ClientLogDao clientLogDao;

    public AccountLogDao getAccountLogDao() {
        return accountLogDao;
    }

    public void setAccountLogDao(AccountLogDao accountLogDao) {
        this.accountLogDao = accountLogDao;
    }

    public ClientLogDao getClientLogDao() {
        return clientLogDao;
    }

    public void setClientLogDao(ClientLogDao clientLogDao) {
        this.clientLogDao = clientLogDao;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String getReportCount() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        StringBuilder str = new StringBuilder("{success:true,total:11,rows:[");
        List<AccountLog> account001Logs = accountLogDao.findByCode("001");
        List<AccountLog> account004Logs = accountLogDao.findByCode("004");
        List<AccountLog> account010Logs = accountLogDao.findByCode("010");

        List<ClientLog> client002Logs = clientLogDao.findByCode("002");
        List<ClientLog> client003Logs = clientLogDao.findByCode("003");
        List<ClientLog> client005Logs = clientLogDao.findByCode("005");
        List<ClientLog> client006Logs = clientLogDao.findByCode("006");
        List<ClientLog> client007Logs = clientLogDao.findByCode("007");
        List<ClientLog> client008Logs = clientLogDao.findByCode("008");
        List<ClientLog> client009Logs = clientLogDao.findByCode("009");
        List<ClientLog> client011Logs = clientLogDao.findByCode("011");

        if (account001Logs != null && account001Logs.size() > 0) {
            str.append("{");
            str.append("'name':'审计开启与关闭','count':" + account001Logs.size() + ",'code':'001'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'审计开启与关闭','count':0,'code':'001'");
            str.append("},");
        }

        if (client002Logs != null && client002Logs.size() > 0) {
            str.append("{");
            str.append("'name':'用户鉴别失败','count':" + client002Logs.size() + ",'code':'002'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'用户鉴别失败','count':0,'code':'002'");
            str.append("},");
        }

        if (client003Logs != null && client003Logs.size() > 0) {
            str.append("{");
            str.append("'name':'用户一般操作','count':" + client003Logs.size() + ",'code':'003'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'用户一般操作','count':0,'code':'003'");
            str.append("},");
        }

        if (account004Logs != null && account004Logs.size() > 0) {
            str.append("{");
            str.append("'name':'管理员操作','count':" + account004Logs.size() + ",'code':'004'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'管理员操作','count':0,'code':'004'");
            str.append("},");
        }


        if (client005Logs != null && client005Logs.size() > 0) {
            str.append("{");
            str.append("'name':'隧道的建立删除','count':" + client005Logs.size() + ",'code':'005'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'隧道的建立删除','count':0,'code':'005'");
            str.append("},");
        }

        if (client006Logs != null && client006Logs.size() > 0) {
            str.append("{");
            str.append("'name':'同一隧道的建立删除','count':" + client006Logs.size() + ",'code':'006'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'同一隧道的建立删除','count':0,'code':'006'");
            str.append("},");
        }

        if (client007Logs != null && client007Logs.size() > 0) {
            str.append("{");
            str.append("'name':'完整性校验失败','count':" + client007Logs.size() + ",'code':'007'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'完整性校验失败','count':0,'code':'007'");
            str.append("},");
        }

        if (client008Logs != null && client008Logs.size() > 0) {
            str.append("{");
            str.append("'name':'数据解密失败','count':" + client008Logs.size() + ",'code':'008'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'数据解密失败','count':0,'code':'008'");
            str.append("},");
        }

        if (client009Logs != null && client009Logs.size() > 0) {
            str.append("{");
            str.append("'name':'包被丢弃事件','count':" + client009Logs.size() + ",'code':'009'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'包被丢弃事件','count':0,'code':'009'");
            str.append("},");
        }

        if (account010Logs != null && account010Logs.size() > 0) {
            str.append("{");
            str.append("'name':'日志存储失败','count':" + account010Logs.size() + ",'code':'010'");
            str.append("},");
        } else {
            str.append("{");
            str.append("'name':'日志存储失败','count':0,'code':'010'");
            str.append("},");
        }

        if (client011Logs != null && client011Logs.size() > 0) {
            str.append("{");
            str.append("'name':'重放数据攻击','count':" + client011Logs.size() + ",'code':'011'");
            str.append("}");
        } else {
            str.append("{");
            str.append("'name':'重放数据攻击','count':0,'code':'011'");
            str.append("}");
        }
        str.append("]}");
        actionBase.actionEnd(response, str.toString(), result);
        return null;
    }
}
*/
