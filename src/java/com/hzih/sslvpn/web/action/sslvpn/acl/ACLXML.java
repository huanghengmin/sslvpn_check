package com.hzih.sslvpn.web.action.sslvpn.acl;

import com.hzih.sslvpn.utils.StringContext;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-18
 * Time: 下午7:59
 * To change this template use File | Settings | File Templates.
 */
public class ACLXML {
    private static Logger logger = Logger.getLogger(ACLXML.class);
    public static final String control = "acl";
    public static final String status = "status";
    public static final String url = "url";
    public static final String port = "port";
    public static final String proxy_ip = "proxy_ip";
    public static final String proxy_port = "proxy_port";

    public static String getAttribute(String attributeName) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(StringContext.acl_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage(),e);
        }
        Element root = doc.getRootElement();
        String result = root.attributeValue(attributeName);
        return result;
    }

    public static boolean saveConfig(String status, String control_url, String port, String proxy_ip, String proxy_port) {
        boolean flag = false;
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement(ACLXML.control);
        root.addAttribute(ACLXML.status, status);
        root.addAttribute(ACLXML.url, control_url);
        root.addAttribute(ACLXML.port, port);
        root.addAttribute(ACLXML.proxy_ip, proxy_ip);
        root.addAttribute(ACLXML.proxy_port, proxy_port);
        OutputFormat outputFormat = new OutputFormat("", true);
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.acl_xml)), outputFormat);
            try {
                xmlWriter.write(doc);
                flag = true;
            } catch (IOException e) {
                logger.info(e.getMessage(),e);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(e.getMessage(),e);
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage(),e);
        }
        return flag;
    }
}
