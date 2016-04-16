package com.hzih.sslvpn.monitor.servlet;

import com.hzih.sslvpn.jsonrpc.JSONRPC2Error;
import com.hzih.sslvpn.jsonrpc.JSONRPC2ParseException;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Request;
import com.hzih.sslvpn.jsonrpc.JSONRPC2Response;
import com.hzih.sslvpn.jsonrpc.server.MessageContext;
import com.hzih.sslvpn.jsonrpc.server.RequestHandler;
import com.hzih.sslvpn.monitor.handler.SSLVpnGetMonitorInfoHandler;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-16
 * Time: 下午5:53
 * To change this template use File | Settings | File Templates.
 */
public class GetMonitorInfoServlet extends HttpServlet {
    protected RequestHandler handler = new SSLVpnGetMonitorInfoHandler();

    private final Logger log = Logger.getLogger(GetMonitorInfoServlet.class);

    @Override
    public void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException {
        MessageContext requestContext = new MessageContext(httpRequest);
        PrintWriter out = null;
        try {
            out = httpResponse.getWriter();
        } catch (IOException e) {
            log.error("HTTP POST I/O exception: " + e.getMessage(), e);
            throw new ServletException("HTTP POST I/O exception: " + e.getMessage(), e);
        }
        String jsonrpc = httpRequest.getHeader("jsonrpc");
  /*      StringBuilder postContent = new StringBuilder(256);
        try {
            BufferedReader reader = httpRequest.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                postContent.append(line);
            }
            reader.close();*/
//        } catch (IOException e) {
//            log.error("HTTP POST I/O exception: " + e.getMessage(), e);
//            throw new ServletException("HTTP POST I/O exception: " + e.getMessage(), e);
//        }
        JSONRPC2Request request = null;
        try {
            request = JSONRPC2Request.parse(jsonrpc.toString());
        } catch (JSONRPC2ParseException e) {
            log.info("Invalid JSON-RPC 2.0 request: " + e.getMessage());
            out.println(new JSONRPC2Response(JSONRPC2Error.PARSE_ERROR, null));
            return;
        }
        JSONRPC2Response response = handler.process(request, requestContext);
        out.println(response);
        out.close();
    }

    @Override
    public void
    doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException {
        doPost(httpRequest, httpResponse);
    }

    @Override
    public void
    destroy() {
        super.destroy();
    }
}
