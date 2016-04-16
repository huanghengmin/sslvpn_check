/*
package com.hzih.sslvpn.web.action.audit;

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

public class ProcessXMLUtils {
    private static Logger logger = Logger.getLogger(ProcessXMLUtils.class);
    public static final String config = "config";
    public static final String audit_flag = "audit_flag";
    public static final String verity_flag = "verity_flag";
    public static final String client_flag = "client_flag";
    public static final String admin_flag = "admin_flag";
    public static final String build_flag = "build_flag";
    public static final String build_num_flag = "build_num_flag";
    public static final String full_flag = "full_flag";
    public static final String decode_flag = "decode_flag";
    public static final String discard_flag = "discard_flag";
    public static final String storage_flag = "storage_flag";
    public static final String replay_flag = "replay_flag";
    public static final String charset = "utf-8";

    public static String find() {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {
            doc = saxReader.read(new File(StringContext.process_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        if (doc != null) {
            Element config = doc.getRootElement();
            Element audit_flag_el = config.element(audit_flag);
            Element verity_flag_el = config.element(verity_flag);
            Element client_flag_el = config.element(client_flag);
            Element admin_flag_el = config.element(admin_flag);
            Element build_flag_el = config.element(build_flag);
            Element build_num_flag_el = config.element(build_num_flag);
            Element full_flag_el = config.element(full_flag);
            Element decode_flag_el = config.element(decode_flag);
            Element discard_flag_el = config.element(discard_flag);
            Element storage_flag_el = config.element(storage_flag);
            Element replay_flag_el = config.element(replay_flag);
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("audit_flag:'" + audit_flag_el.getText() + "',");
            sb.append("verity_flag:'" + verity_flag_el.getText() + "',");
            sb.append("client_flag:'" + client_flag_el.getText() + "',");
            sb.append("admin_flag:'" + admin_flag_el.getText() + "',");
            sb.append("build_flag:'" + build_flag_el.getText() + "',");
            sb.append("build_num_flag:'" + build_num_flag_el.getText() + "',");
            sb.append("full_flag:'" + full_flag_el.getText() + "',");
            sb.append("decode_flag:'" + decode_flag_el.getText() + "',");
            sb.append("discard_flag:'" + discard_flag_el.getText() + "',");
            sb.append("storage_flag:'" + storage_flag_el.getText() + "',");
            sb.append("replay_flag:'" + replay_flag_el.getText() + "'");
            sb.append("}");
            return sb.toString();
        }
        return null;
    }


    public static String getValue(String name) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        String result = null;
        try {
            doc = saxReader.read(new File(StringContext.process_xml));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        if(doc!=null){
            Element ldap = doc.getRootElement();
            Element el = ldap.element(name);
            result = el.getText();
        }
        return result;
    }

    public static boolean save(
            String audit_flag,
            String verity_flag,
            String client_flag,
            String admin_flag,
            String build_flag,
            String build_num_flag,
            String full_flag,
            String decode_flag,
            String discard_flag,
            String storage_flag,
            String replay_flag) {
        boolean flag = false;
        Document doc = DocumentHelper.createDocument();
        Element config = doc.addElement(ProcessXMLUtils.config);

        Element audit_flag_el = config.addElement(ProcessXMLUtils.audit_flag);
        audit_flag_el.setText(audit_flag);

        Element verity_flag_el = config.addElement(ProcessXMLUtils.verity_flag);
        verity_flag_el.setText(verity_flag);

        Element client_flag_el = config.addElement(ProcessXMLUtils.client_flag);
        client_flag_el.setText(client_flag);

        Element admin_flag_el = config.addElement(ProcessXMLUtils.admin_flag);
        admin_flag_el.setText(admin_flag);

        Element build_flag_el = config.addElement(ProcessXMLUtils.build_flag);
        build_flag_el.setText(build_flag);

        Element build_num_flag_el = config.addElement(ProcessXMLUtils.build_num_flag);
        build_num_flag_el.setText(build_num_flag);

        Element full_flag_el = config.addElement(ProcessXMLUtils.full_flag);
        full_flag_el.setText(full_flag);

        Element decode_flag_el = config.addElement(ProcessXMLUtils.decode_flag);
        decode_flag_el.setText(decode_flag);

        Element discard_flag_el = config.addElement(ProcessXMLUtils.discard_flag);
        discard_flag_el.setText(discard_flag);

        Element storage_flag_el = config.addElement(ProcessXMLUtils.storage_flag);
        storage_flag_el.setText(storage_flag);

        Element replay_flag_el = config.addElement(ProcessXMLUtils.replay_flag);
        replay_flag_el.setText(replay_flag);

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        format.setIndent(true);
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(StringContext.process_xml)), format);
            try {
                xmlWriter.write(doc);
                flag = true;
            } catch (IOException e) {
                logger.info(e.getMessage());
            } finally {
                try {
                    xmlWriter.flush();
                    xmlWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
        }
        return flag;
    }
}


*/
