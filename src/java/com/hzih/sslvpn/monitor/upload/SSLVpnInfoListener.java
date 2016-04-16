package com.hzih.sslvpn.monitor.upload;

import javax.servlet.ServletContextListener;

import javax.servlet.ServletContextEvent;
import java.util.Timer;

public class SSLVpnInfoListener implements ServletContextListener {
    private Timer timer = null;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        timer.cancel();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        timer = new Timer(true);
        //设置任务计划，启动和间隔时间
        timer.schedule(new SSLVpnInfoTask(), 0, 60*1000*60*24);
        //用户在线检查开始
    }
}
