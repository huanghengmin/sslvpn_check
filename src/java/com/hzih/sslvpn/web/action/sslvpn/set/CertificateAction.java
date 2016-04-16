package com.hzih.sslvpn.web.action.sslvpn.set;

import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.dao.*;
import com.hzih.sslvpn.domain.ServerCertificate;
import com.hzih.sslvpn.service.LogService;
import com.hzih.sslvpn.utils.FileUtil;
import com.hzih.sslvpn.utils.StringContext;
import com.hzih.sslvpn.web.SessionUtils;
import com.hzih.sslvpn.web.action.AccountLogUtils;
import com.hzih.sslvpn.web.action.ActionBase;
import com.hzih.sslvpn.syslog.sender.SyslogSender;
import com.hzih.sslvpn.web.action.audit.AuditFlagAction;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-9
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
public class CertificateAction extends ActionSupport {
    private static final Logger logger = Logger.getLogger(CertificateAction.class);
    private LogService logService;
    private int start;
    private int limit;
    private ServerDao serverDao;
    private ServerCertificateDao serverCertificateDao;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public ServerDao getServerDao() {
        return serverDao;
    }

    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    public ServerCertificateDao getServerCertificateDao() {
        return serverCertificateDao;
    }

    public void setServerCertificateDao(ServerCertificateDao serverCertificateDao) {
        this.serverCertificateDao = serverCertificateDao;
    }

    /**
     * ServerCertificate
     */
    private File serverPfxFile;
    private String serverPfxFileFileName;
    private String serverPfxContentType;

    public File getServerPfxFile() {
        return serverPfxFile;
    }

    public void setServerPfxFile(File serverPfxFile) {
        this.serverPfxFile = serverPfxFile;
    }

    public String getServerPfxFileFileName() {
        return serverPfxFileFileName;
    }

    public void setServerPfxFileFileName(String serverPfxFileFileName) {
        this.serverPfxFileFileName = serverPfxFileFileName;
    }

    public String getServerPfxContentType() {
        return serverPfxContentType;
    }

    public void setServerPfxContentType(String serverPfxContentType) {
        this.serverPfxContentType = serverPfxContentType;
    }

    public static boolean execute(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null) {
                logger.info(line);
            }
            return true;
        } catch (java.io.IOException e) {
            logger.info("IOException:" + e);
        }
        return false;
    }

    public static boolean split_server_pfx(String pfxPwd, String pfx, String filename) {
        String pem = StringContext.server_keyPath + "/" + filename + ".pem";
        String cert = StringContext.server_certPath + "/" + filename + ".pem";
        String private_key = StringContext.server_keyPath + "/" + filename + ".pem";
        if (pfxPwd == null || pfxPwd.equals("")) {
            boolean flag = execute("openssl pkcs12 -in " + pfx + " -passin pass: -nocerts -out " + pem + " -passout pass:111111");
            if (flag) {
                boolean ex = execute("openssl pkcs12 -in " + pfx + " -passin pass: -clcerts -nokeys -out " + cert);
                if (ex) {
                    execute("openssl rsa -in " + pem + " -passin pass:111111 -out " + private_key);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        logger.info(e.getMessage(), e);
                    }
                }
            }
        } else {
            boolean flag = execute("openssl pkcs12 -in " + pfx + " -passin pass:" + pfxPwd + " -nocerts -out " + pem + " -passout pass:111111");
            if (flag) {
                boolean ex = execute("openssl pkcs12 -in " + pfx + " -passin pass:" + pfxPwd + " -clcerts -nokeys -out " + cert);
                if (ex) {
                    execute("openssl rsa -in " + pem + " -passin pass:111111 -out " + private_key);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        logger.info(e.getMessage(),e);
                    }
                }
            }
        }
        if (new File(pem).exists() && new File(cert).exists() && new File(private_key).exists()) {
            return true;
        }
        return false;
    }

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        int pageIndex = start / limit + 1;
        PageResult pageResult = serverCertificateDao.listByPage(pageIndex, limit);
        if (pageResult != null) {
            List<ServerCertificate> list = pageResult.getResults();
            int count = pageResult.getAllResultsAmount();
            if (list != null) {
                String json = "{success:true,total:" + count + ",rows:[";
                Iterator<ServerCertificate> raUserIterator = list.iterator();
                while (raUserIterator.hasNext()) {
                    ServerCertificate log = raUserIterator.next();
                    if (raUserIterator.hasNext()) {
                        json += "{" +
                                "id:'" + log.getId() +
                                "',file:'" + log.getCertificate() +
                                "',name:'" + log.getName() +
                                "',status:'" + log.getStatus() + "'" +
                                "},";
                    } else {
                        json += "{" +
                                "id:'" + log.getId() +
                                "',file:'" + log.getCertificate() +
                                "',name:'" + log.getName() +
                                "',status:'" + log.getStatus() + "'" +
                                "}";
                    }
                }
                json += "]}";
                actionBase.actionEnd(response, json, result);
            }
        }
        return null;
    }

    public String remove() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'删除失败'}";
        String msg = null;
        try {
            String id = request.getParameter("id");
            ServerCertificate serverCertificate = serverCertificateDao.findById(Integer.parseInt(id));
            File ca_file = new File(serverCertificate.getCertificate());
            if (ca_file.exists()) {
                ca_file.delete();
            }
            String name = serverCertificate.getName();
            String filename = name;
            if (name.contains(".")) {
                filename = name.substring(0, name.indexOf("."));
            }
            String key = StringContext.server_keyPath + "/" + filename + ".pem";
            String cert = StringContext.server_certPath + "/" + filename + ".pem";
            File file = new File(key);
            File f = new File(cert);
            if(file.exists()){
                file.delete();
            }
            if(f.exists()){
             f.delete();
            }
            boolean flag = serverCertificateDao.delete(serverCertificate);
            if(flag){
                msg = "删除成功";
                json = "{success:true,msg:'"+msg+"'}";
                if(AuditFlagAction.getAuditFlag()) {
                    logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                    logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "证书管理", msg);
                    String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "证书管理", "info", "004", "1", new Date());
                    SyslogSender.sysLog(log);
                }
            }
        } catch (Exception e) {
            msg = "删除失败";
            json = "{success:true,msg:'"+msg+"'}";
            if(AuditFlagAction.getAuditFlag()) {
                logger.info(e.getMessage(),e);
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "证书管理", msg);
                String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "证书管理", "info", "004", "0", new Date());
                SyslogSender.sysLog(log);
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String update() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String id = request.getParameter("id");
        String result = actionBase.actionBegin(request);
        String msg = "启用设备证书失败";
        String json = "{success:false,msg:'" + msg + "'}";
        if (id != null) {
            ServerCertificate serverCertificate = serverCertificateDao.findById(Integer.parseInt(id));
            if (serverCertificate != null) {
                String name = serverCertificate.getName();
                String filename = name;
                if (name.contains(".")) {
                    filename = name.substring(0, name.indexOf("."));
                }
                String key = StringContext.server_keyPath + "/" + filename + ".pem";
                String cert = StringContext.server_certPath + "/" + filename + ".pem";
                File file = new File(key);
                File f = new File(cert);
                if(file.exists()&&file.length()>0&&f.exists()&&f.length()>0){
                    FileUtil.copy(file,StringContext.key_file);
                    FileUtil.copy(f,StringContext.server_file);
                    serverCertificate.setStatus(1);
                    boolean flag = serverCertificateDao.modify(serverCertificate);
                    if(flag){
                        msg = "启用设备证书成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "证书管理", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "证书管理", "info", "004", "1", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                }
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String upload() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String pwd = request.getParameter("pwd");
        String msg = "保存失败";
        String json = "{success:false,msg:'" + msg + "'}";
        ServerCertificate trustCertificate = serverCertificateDao.findByName(serverPfxFileFileName);
        if (trustCertificate != null) {
            msg = "保存失败,文件名称已在在,请改名后重新上传";
            json = "{success:false,msg:'" + msg + "'}";
        } else {
            boolean crtFlag = FileUtil.saveUploadFile(serverPfxFile, StringContext.server_sslPath + "/" + serverPfxFileFileName);
            if (crtFlag) {
                String filename = serverPfxFileFileName;
                if (serverPfxFileFileName.contains(".")) {
                    filename = serverPfxFileFileName.substring(0, serverPfxFileFileName.indexOf("."));
                }
                boolean flag = split_server_pfx(pwd, StringContext.server_sslPath + "/" + serverPfxFileFileName, filename);
                if (flag) {
                    ServerCertificate tr = new ServerCertificate();
                    tr.setCertificate(StringContext.server_sslPath + "/" + serverPfxFileFileName);
                    tr.setName(serverPfxFileFileName);
                    tr.setPwd(pwd);
                    tr.setStatus(0);
                    boolean f = serverCertificateDao.add(tr);
                    if (f) {
                        msg = "保存网关证书成功";
                        json = "{success:true,msg:'" + msg + "'}";
                        if(AuditFlagAction.getAuditFlag()) {
                            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "证书管理", msg);
                            String log = AccountLogUtils.getResult(SessionUtils.getAccount(request).getUserName(), msg, "证书管理", "info", "004", "1", new Date());
                            SyslogSender.sysLog(log);
                        }
                    }
                }
            }
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String download() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false}";
        String id = request.getParameter("id");
        ServerCertificate serverCertificate = serverCertificateDao.findById(Integer.parseInt(id));
        String Agent = request.getHeader("User-Agent");
        StringTokenizer st = new StringTokenizer(Agent, ";");
        st.nextToken();
        //得到用户的浏览器名  MSIE  Firefox
        String userBrowser = st.nextToken();
        File file = new File(serverCertificate.getCertificate());
        FileUtil.downType(response, serverCertificate.getName(), userBrowser);
        response = FileUtil.copy(file, response);
        json = "{success:true}";
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String findServerCertificate()throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String id = request.getParameter("id");
        ServerCertificate serverCertificate = serverCertificateDao.findById(Integer.parseInt(id));
        String name = serverCertificate.getName();
        String filename = name;
        if (name.contains(".")) {
            filename = name.substring(0, name.indexOf("."));
        }
        String certificate = StringContext.server_certPath + "/" + filename + ".pem";
        File file = new File(certificate);
        String json = "{success:true,total:" + 1 + ",rows:[";
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM日dd日HH时mm分ss秒");
        if (file.exists()) {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            FileInputStream server = null;
            X509Certificate cert = null;
            try {
                server = new FileInputStream(file);
                cert = (X509Certificate) certificatefactory.generateCertificate(server);
            } catch (FileNotFoundException e) {
                logger.info(e.getMessage(), e);
            }finally {
                try {
                    server.close();
                } catch (IOException e) {
                    logger.info(e.getMessage(),e);
                }
            }
            String subject = cert.getSubjectDN().getName();
            Date notBefore = cert.getNotBefore();//得到开始有效日期
            Date notAfter = cert.getNotAfter();  //得到截止日期
            String s_notBefore = "";
            String s_notAfter = "";
            if (null != notBefore) {
                s_notBefore = format.format(notBefore);
            }

            if (null != notBefore) {
                s_notAfter = format.format(notAfter);
            }
            String serialNumber = cert.getSerialNumber().toString(16).toUpperCase();//得到序列号   16进制
            int version = cert.getVersion();
            String cn = cert.getIssuerDN().toString();
            json += "{" +
                    "name:'证书版本'," +
                    "content:' V" + version + "'" +
                    "},";
            json += "{" +
                    "name:'证书序列号'," +
                    "content:'" + serialNumber + "'" +
                    "},";
            json += "{" +
                    "name:'证书颁发者'," +
                    "content:'" + cn + "'" +
                    "},";
            json += "{" +
                    "name:'有效起始日期'," +
                    "content:'" + s_notBefore.toString() + "'" +
                    "},";
            json += "{" +
                    "name:'有效终止日期'," +
                    "content:'" + s_notAfter.toString() + "'" +
                    "},";
            json += "{" +
                    "name:'证书主题'," +
                    "content:'" + subject + "'" +
                    "}";
        } else {
            json += "{" +
                    "name:'证书版本'," +
                    "content:'未找到证书,可能已被删除!'" +
                    "},";
            json += "{" +
                    "name:'证书序列号'," +
                    "content:'未找到证书,可能已被删除!'" +
                    "},";
            json += "{" +
                    "name:'证书颁发者'," +
                    "content:'未找到证书,可能已被删除!'" +
                    "},";
            json += "{" +
                    "name:'有效起始日期'," +
                    "content:'未找到证书,可能已被删除!'" +
                    "},";
            json += "{" +
                    "name:'有效终止日期'," +
                    "content:'未找到证书,可能已被删除!'" +
                    "},";
            json += "{" +
                    "name:'证书主题'," +
                    "content:'未找到证书,可能已被删除!'" +
                    "}";
        }
        json += "]}";

        actionBase.actionEnd(response, json, result);
        return null;
    }
}
