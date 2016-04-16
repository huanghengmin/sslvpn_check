package com.hzih.sslvpn.web.action.sslvpn.monitor;

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

public class MonitorConfigXML {
    private static Logger logger = Logger.getLogger(MonitorConfigXML.class);
    public static final String config = "config";
    public static final String ip = "ip";
    public static final String port = "port";
    public static final String rpc_port = "rpc_port";
//    private static  final String path = StringContext.systemPath+"/config/monitor_config.xml";

    public static String getAttribute(String attributeName) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            File f = new File(StringContext.monitor_xml);
            if (f.exists())
                doc = saxReader.read(f);
        } catch (DocumentException e) {
            logger.info(e.getMessage(), e);
        }
        if (doc != null) {
            Element root = doc.getRootElement();
            String result = root.attributeValue(attributeName);
            return result;
        } else {
            return null;
        }
    }

    public static void saveConfig(String ip, String port, String rpc_port) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement(MonitorConfigXML.config);
        root.addAttribute(MonitorConfigXML.ip, ip);
        root.addAttribute(MonitorConfigXML.port, port);
        root.addAttribute(MonitorConfigXML.rpc_port, rpc_port);
        OutputFormat outputFormat = new OutputFormat("", true);
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.monitor_xml)), outputFormat);
            try {
                xmlWriter.write(doc);
            } catch (IOException e) {
                logger.info(e.getMessage(),e);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(e.getMessage(),e);
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage(),e);
        }
    }
}
