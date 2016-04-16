package com.hzih.sslvpn.monitor.mina.example;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeServerHandler extends IoHandlerAdapter
{
    @Override
    public void sessionCreated(IoSession session) {
//显示客户端的ip和端口
        System.out.println(session.getRemoteAddress().toString());
    }
    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        String str = message.toString();
        System.out.println("穿过来的消息："+str);
        if( str.trim().equalsIgnoreCase("quit") ) {
            session.close();//结束会话
            return;
        }
//        Date date = new Date();
//        session.write( "服务器返回的时间："+date.toString() );//返回当前时间的字符串
//        System.out.println("Message written...");
//        String cc = new UrlHtml().getHtmlString("http://172.16.2.5:5000/pubweb/login.jsp");
//        String cc = new UrlHtml().getHtmlString("http://java.sun.com");
       session.write("abcdefg");
//        session.write(cc);
    }
}

