package com.hzih.sslvpn.monitor.upload;

import java.util.TimerTask;

public class SSLVpnInfoTask extends TimerTask {
    private SSLVpnInfo sslVpnInfo = new SSLVpnInfo();

    @Override
    public void run() {
        //在线用户定时更新任务
        sslVpnInfo.uploadVpnInfo();
    }
}
