package com.hzih.sslvpn.web.action.sslvpn.set;

import com.hzih.sslvpn.domain.Nat;
import com.hzih.sslvpn.domain.User;
import com.hzih.sslvpn.utils.Dom4jUtil;
import com.hzih.sslvpn.utils.StringContext;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 15-6-17.
 */
public class UpdateXMLThread extends Thread {
    private Logger logger = Logger.getLogger(UpdateXMLThread.class);

    private List<Nat> nats;

    public UpdateXMLThread(List<Nat> nats) {
        this.nats = nats;
    }

    public void run() {
        if (nats != null && nats.size() > 0) {
            for (Nat nat : nats) {
                Document doc = Dom4jUtil.getDocument(StringContext.rules_xml);
                if (doc != null) {
                    Element selectSingleNode = (Element) doc.selectSingleNode("/config/tunnel/service[@bindIp='" + nat.getBindIp()
                            + "'][@bindPort='" + nat.getBindPort() + "'][@protocol='" + nat.getProtocol() + "']");
                    if (selectSingleNode != null) {
                        Set<User> userSet = nat.getUserSet();
                        if (userSet != null && userSet.size() > 0) {
                            Element users_e = selectSingleNode.element("users");
                            if (users_e != null) {
                                selectSingleNode.remove(users_e);
                            }
                            Element users_el = selectSingleNode.addElement("users");
                            for (User user : userSet) {
                                Element user_e = users_el.addElement("user");
                                user_e.addAttribute("name", user.getCn());
                                user_e.addAttribute("level", String.valueOf(user.getLevel()));
                            /*
                                Element node = (Element) selectSingleNode.selectSingleNode("/users/user[@name='" + user.getCn() + "']");
                                if (node != null) {
                                    Attribute attribute = node.attribute("level");
                                    if (!attribute.getValue().equals(String.valueOf(user.getLevel()))) {
                                        attribute.setValue(String.valueOf(user.getLevel()));
                                        try {
                                            Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                                        } catch (IOException e) {
                                            logger.error(e.getMessage());
                                        }
                                    }
                                } else {
                                    Element users = selectSingleNode.element("users");
                                    if (users == null) {
                                        Element users_e = selectSingleNode.addElement("users");
                                        Element user_e = users_e.addElement("user");
                                        user_e.addAttribute("name", user.getCn());
                                        user_e.addAttribute("level", String.valueOf(user.getLevel()));
                                        try {
                                            Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                                        } catch (IOException e) {
                                            logger.error(e.getMessage());
                                        }
                                    } else {
                                        Element user_e = users.addElement("user");
                                        user_e.addAttribute("name", user.getCn());
                                        user_e.addAttribute("level", String.valueOf(user.getLevel()));
                                        try {
                                            Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                                        } catch (IOException e) {
                                            logger.error(e.getMessage());
                                        }
                                    }
                                }*/
                            }

                            try {
                                Dom4jUtil.writeDocumentToFile(doc, StringContext.rules_xml);
                            } catch (IOException e) {
                                logger.info(e.getMessage(),e);
                            }
                        }
                    }
                }
            }
        }
    }
}
