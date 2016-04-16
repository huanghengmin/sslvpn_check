package com.hzih.sslvpn.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.dao.TrustTerminalDao;
import com.hzih.sslvpn.domain.TrustTerminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhm on 2014/12/17.
 */
public class TrustTerminalDaoImpl extends MyDaoSupport implements TrustTerminalDao {
    @Override
    public void setEntityClass() {
        this.entityClass = TrustTerminal.class;
    }

    @Override
    public boolean add(TrustTerminal terminal) throws Exception {
        boolean flag = false;
        super.getHibernateTemplate().save(terminal);
        flag = true;
        return flag;
    }

    @Override
    public boolean modify(TrustTerminal terminal) throws Exception {
        boolean flag = false;
        super.getHibernateTemplate().saveOrUpdate(terminal);
        flag = true;
        return flag;
    }

    @Override
    public boolean delete(TrustTerminal terminal) throws Exception {
        boolean flag = false;
        super.getHibernateTemplate().delete(terminal);
        flag = true;
        return flag;
    }

    @Override
    public TrustTerminal findByIpMac(String ip, String mac) throws Exception {
        String hql = "from TrustTerminal terminal where terminal.ip ='" + ip + "' and terminal.mac='" + mac + "'";
        List<TrustTerminal> terminalList = super.getHibernateTemplate().find(hql);
        if (terminalList.size() > 0 && terminalList != null) {
            return terminalList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public PageResult findByPages(int start, int limit) {
        int pageIndex = start / limit + 1;
        String hql = " from TrustTerminal where 1=1";
        List paramsList = new ArrayList();
        String countHql = "select count(*) " + hql;
        PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(), pageIndex, limit);
        return ps;
    }

}
