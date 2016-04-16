package com.hzih.sslvpn.dao;


import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.domain.TrustTerminal;

/**
 * Created by hhm on 2014/12/17.
 */
public interface TrustTerminalDao {
    public boolean add(TrustTerminal terminal)throws Exception;

    public boolean modify(TrustTerminal terminal)throws Exception;

    public boolean delete(TrustTerminal terminal)throws Exception;

    public TrustTerminal findByIpMac(String ip,String mac)throws Exception;

    PageResult findByPages(int start, int limit);
}
