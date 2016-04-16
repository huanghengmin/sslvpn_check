/*
package com.hzih.sslvpn.web.action.config;

import com.hzih.sslvpn.constant.AppConstant;
import com.hzih.sslvpn.web.SiteContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

*/
/**
 * Created by Administrator on 15-4-24.
 *//*

public class AlertUtils {
    public static List<Node> queryEmailAll(){
        SAXReader reader = new SAXReader();
        String fileName = SiteContext.getInstance().contextRealPath + AppConstant.XML_ALERT_CONFIG_PATH;
        Document document = null;
        try {
            document = reader.read(new File(fileName));
        } catch (DocumentException e) {
            return null;
        }
        List<Node> emails = document.selectNodes("//config/mailconfig/emails/email");
        return emails;
    }
}
*/
