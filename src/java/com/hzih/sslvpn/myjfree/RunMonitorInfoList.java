package com.hzih.sslvpn.myjfree;

import com.hzih.sslvpn.snmp.SnmpUtil;
import org.apache.log4j.Logger;
import org.jfree.data.time.Hour;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 13-3-18
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 */
public class RunMonitorInfoList extends Thread{
    private Logger log = Logger.getLogger(RunMonitorInfoList.class);

    @Override
    public void run() {
        while (true) {
            GregorianCalendar gc2 = new GregorianCalendar();
            int miniute2 = gc2.get(Calendar.MINUTE);
            try{
                if(MonitorInfoList.cpuBeanList==null) {
                    MonitorInfoList.cpuBeanList = new ArrayList<CpuBean>();
                }
                //CPU
                double cpuUse = new SnmpUtil().getCpuUse();
                MonitorInfoList.cpuBeanList.add(new CpuBean( miniute2, new Hour(),cpuUse , System.currentTimeMillis() ));
                if(MonitorInfoList.cpuBeanList.size()>1&&(MonitorInfoList.cpuBeanList.get(MonitorInfoList.cpuBeanList.size()-1).getCurrentMillis()- MonitorInfoList.cpuBeanList.get(0).getCurrentMillis())>(1000*60*60)){
                    MonitorInfoList.cpuBeanList.remove(0);
                }

                if(MonitorInfoList.cpuBeanListMem==null) {
                    MonitorInfoList.cpuBeanListMem = new ArrayList<CpuBean>();
                }
                //内存
                double mem = new SnmpUtil().getMem();
                MonitorInfoList.cpuBeanListMem.add(new CpuBean( miniute2, new Hour(), mem, System.currentTimeMillis() ));
                if(MonitorInfoList.cpuBeanListMem.size()>1&&(MonitorInfoList.cpuBeanListMem.get(MonitorInfoList.cpuBeanListMem.size()-1).getCurrentMillis()- MonitorInfoList.cpuBeanListMem.get(0).getCurrentMillis())>(1000*60*60)){
                    MonitorInfoList.cpuBeanListMem.remove(0);
                }
            }catch (Exception e){
                log.error("系统监控服务启动失败！"+e.getMessage(),e);
            }
            try {
                sleep(1000*60);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }

    public static void main(String args[]) {
        new RunMonitorInfoList().start();
    }
}
