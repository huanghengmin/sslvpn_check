package com.hzih.sslvpn.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.domain.Nat;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-3
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public interface NatDao extends BaseDao{

    PageResult listByPage(int pageIndex, int limit);

    PageResult listByPage(int pageIndex, int level, int limit);

    public boolean add(Nat nat)throws Exception;

    public boolean modify(Nat nat)throws Exception;

    public boolean delete(Nat nat)throws Exception;
    
    public Nat findById(int id)throws Exception;

    public Nat findByBind(String bindIp,String bindPort,String protocol)throws Exception;

}
