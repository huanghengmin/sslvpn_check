package com.hzih.sslvpn.service;

import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.domain.TrustTerminal;

public interface TrustTerminalService {
    public boolean add(TrustTerminal trustTerminal)throws Exception;

    public boolean delete(TrustTerminal trustTerminal)throws Exception;

    public boolean modify(TrustTerminal trustTerminal)throws Exception;

    public TrustTerminal findByIpMac(String ip,String mac) throws Exception;

    public PageResult findByPages(int start, int limit)throws Exception;
}
