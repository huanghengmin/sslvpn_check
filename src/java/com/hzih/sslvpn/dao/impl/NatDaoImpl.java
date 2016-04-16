package com.hzih.sslvpn.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.dao.NatDao;
import com.hzih.sslvpn.dao.SourceNetDao;
import com.hzih.sslvpn.domain.Nat;
import com.hzih.sslvpn.domain.SourceNet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-3
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class NatDaoImpl extends MyDaoSupport implements NatDao {
    @Override
    public void setEntityClass() {
        this.entityClass = Nat.class;
    }

    @Override
    public PageResult listByPage(int pageIndex, int limit) {
        String hql = " from Nat s where 1=1";
        List paramsList = new ArrayList();

        String countHql = "select count(*) " + hql;

        PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),
                pageIndex, limit);
        return ps;
    }

    @Override
    public PageResult listByPage(int pageIndex, int level, int limit) {
        String hql = " from Nat s where 1=1";
        List paramsList = new ArrayList();
        if (level >= 0) {
            hql += " and level <= ?";
            paramsList.add(level);
        }
        String countHql = "select count(*) " + hql;

        PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),
                pageIndex, limit);
        return ps;
    }

    @Override
    public boolean add(Nat nat) throws Exception {
        boolean flag = false;
        super.getHibernateTemplate().save(nat);
        flag = true;
        return flag;
    }

    @Override
    public boolean modify(Nat nat) throws Exception {
        boolean flag = false;
        super.getHibernateTemplate().saveOrUpdate(nat);
        flag = true;
        return flag;
    }

    @Override
    public boolean delete(Nat nat) throws Exception {
        boolean flag = false;
        super.getHibernateTemplate().delete(nat);
        flag = true;
        return flag;
    }

    @Override
    public Nat findById(int id) throws Exception {
        String hql = String.format("from Nat s where s.id =%d", id);
        List<Nat> privateNets = super.getHibernateTemplate().find(hql);
        if (privateNets.size() > 0 && privateNets != null) {
            return privateNets.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Nat findByBind(String bindIp, String bindPort, String protocol) throws Exception {
        String hql= String.format("from Nat n where n.bindIp ='%s' and n.bindPort ='%s' and n.protocol='%s'", bindIp, bindPort, protocol);
        List<Nat> users  = super.getHibernateTemplate().find(hql);
        if(null!=users&&users.size()>0){
            return users.get(0);
        }else {
            return null;
        }
    }
}
