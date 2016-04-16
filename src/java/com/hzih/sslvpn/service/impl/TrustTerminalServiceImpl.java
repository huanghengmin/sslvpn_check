package com.hzih.sslvpn.service.impl;

import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.dao.TrustTerminalDao;
import com.hzih.sslvpn.domain.TrustTerminal;
import com.hzih.sslvpn.service.TrustTerminalService;

public class TrustTerminalServiceImpl implements TrustTerminalService {

    private TrustTerminalDao trustTerminalDao;

    public TrustTerminalDao getTrustTerminalDao() {
        return trustTerminalDao;
    }

    public void setTrustTerminalDao(TrustTerminalDao trustTerminalDao) {
        this.trustTerminalDao = trustTerminalDao;
    }


    @Override
    public boolean add(TrustTerminal trustTerminal) throws Exception {
        return trustTerminalDao.add(trustTerminal);
    }

    @Override
    public boolean delete(TrustTerminal trustTerminal) throws Exception {
        return trustTerminalDao.delete(trustTerminal);
    }

    @Override
    public boolean modify(TrustTerminal trustTerminal) throws Exception {
        return trustTerminalDao.modify(trustTerminal);
    }

    @Override
    public TrustTerminal findByIpMac(String ip, String mac) throws Exception {
        return trustTerminalDao.findByIpMac(ip,mac);
    }

    @Override
    public PageResult findByPages(int start, int limit) throws Exception {
        return trustTerminalDao.findByPages(start,limit);
    }
}
