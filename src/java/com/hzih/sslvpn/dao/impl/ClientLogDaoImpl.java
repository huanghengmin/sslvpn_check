package com.hzih.sslvpn.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.dao.ClientLogDao;
import com.hzih.sslvpn.domain.ClientLog;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientLogDaoImpl extends MyDaoSupport implements ClientLogDao {

    @Override
    public void setEntityClass() {
        this.entityClass = ClientLog.class;
    }

    @Override
    public PageResult listByPage(String CN,String audittype, Date startDate, Date endDate, int pageIndex, int limit) {
        StringBuilder hql = new StringBuilder(" from ClientLog where 1=1 ");
        List paramsList = new ArrayList();
        if (CN != null && CN.length() > 0) {
            hql.append(" and CN like ?");
            paramsList.add("%" + CN + "%");
        }
        if(audittype!=null&&audittype.length()>0){
            hql.append(" and audittype = " +audittype);
        }
        if (startDate != null) {
            hql.append(" and date_format(datetime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
            paramsList.add(startDate);
        }
        if (endDate != null) {
            hql.append(" and date_format(datetime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
            paramsList.add(endDate);
        }
        String countHql = "select count(*) " + hql;
        PageResult ps = this.findByPage(hql.toString(), countHql, paramsList.toArray(),
                pageIndex, limit);
        return ps;
    }

    @Override
    public List<ClientLog> findByCode(String code) {
        String queryString = " from ClientLog c where c.audittype = '" + code + "'";
        Session session = this.getSession();
        Query query = session.createQuery(queryString);
        List list = query.list();
        return list;
    }

    @Override
    public List<ClientLog> findByYearCode(String year, String code) {
        String queryString = " from ClientLog c where date_format (c.datetime,'%Y')= '" + year + "' and c.audittype = '" + code + "'";
        Session session = this.getSession();
        Query query = session.createQuery(queryString);
        List list = query.list();
        return list;
    }

    @Override
    public List<ClientLog> findByYearMonthCode(String year, String month, String code) {
        String queryString = " from ClientLog c where date_format(c.datetime,'%Y-%m')= '" + year + "-" + month + "' and c.audittype='" + code + "'";
        Session session = this.getSession();
        Query query = session.createQuery(queryString);
        List list = query.list();
        return list;
    }

    @Override
    public List<ClientLog> findByYearMonthDayCode(String year, String month, String day, String code) {
        String queryString = "  from ClientLog c where date_format(c.datetime,'%Y-%m-&d')= '" + year + "-" + month + "-" + day + "' and c.audittype='" + code + "'";
        Session session = this.getSession();
        Query query = session.createQuery(queryString);
        List list = query.list();
        return list;
    }

    @Override
    public List<ClientLog> findByYearMonthDayHourCode(String year, String month, String day, String hour, String code) {
        String queryString = "  from ClientLog c where date_format(c.datetime,'%Y-%m-&d %H')= '" + year + "-" + month + "-" + day + " " + hour + "" + "' and c.audittype='" + code + "'";
        Session session = this.getSession();
        Query query = session.createQuery(queryString);
        List list = query.list();
        return list;
    }

    @Override
    public void removeLists(long[] ids) {
        for (long id : ids) {
            ClientLog clientLog = new ClientLog(id);
            this.getHibernateTemplate().delete(clientLog);
        }
    }

    @Override
    public void remove(long id) {
        ClientLog clientLog = new ClientLog(id);
        this.getHibernateTemplate().delete(clientLog);
    }
}
