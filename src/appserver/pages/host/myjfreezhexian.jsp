<%@ page language="java"  pageEncoding="utf-8"%>

<%@ page import = "com.hzih.sslvpn.myjfree.CpuBean,
                    com.hzih.sslvpn.myjfree.LiuLiangBean,
                    com.hzih.sslvpn.myjfree.LiuLiangBean2,
                    com.hzih.sslvpn.myjfree.MonitorInfoList,
                    org.jfree.chart.ChartFactory,
                    org.jfree.chart.JFreeChart,
                    org.jfree.chart.axis.DateAxis,
                    org.jfree.chart.axis.DateTickUnit,
                    org.jfree.chart.axis.ValueAxis,
                    org.jfree.chart.plot.XYPlot,
                    org.jfree.chart.renderer.xy.XYLineAndShapeRenderer"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@ page import="org.jfree.data.time.*" %>
<%@ page import="org.jfree.data.xy.XYDataset" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.io.File" %>
<%@ page import="com.hzih.sslvpn.utils.StringContext" %>

<%
    File file = new File(StringContext.tempPath);
    File[] list = file.listFiles();
    for(File f : list ) {
        String fname = f.getName();
        if(fname.indexOf("jfreechart-")==0) {
            f.delete();
        }
    }
//---------------------------------------------------------------------------------CPU---------------------------------------------------------------------------------------------------------------
    TimeSeries timeseries2 = new TimeSeries("CPU", Minute.class);
    GregorianCalendar gc2 = new GregorianCalendar();
    int hour2 = gc2.get(Calendar.HOUR_OF_DAY);
    int miniute2 = gc2.get(Calendar.MINUTE);

    ArrayList<CpuBean> cpuBeanList = MonitorInfoList.cpuBeanList;
    if(cpuBeanList.size()>0&&(cpuBeanList.get(cpuBeanList.size()-1).getCurrentMillis()-cpuBeanList.get(0).getCurrentMillis())<=(1000*60*59)){
        timeseries2.addOrUpdate(new Minute(miniute2,new Hour(hour2-1,new Day())), 0);
        timeseries2.addOrUpdate(new Minute( cpuBeanList.get(0).getMinute()-1, cpuBeanList.get(0).getHour() ), 0);
    }
    session.setAttribute("cpuBeanList",cpuBeanList);
    for(CpuBean cpuBean :cpuBeanList){
        timeseries2.addOrUpdate(new Minute(cpuBean.getMinute(),cpuBean.getHour()), cpuBean.getCpuNum());
    }
    TimeSeriesCollection timeseriescollection2 = new TimeSeriesCollection();
    timeseriescollection2.addSeries(timeseries2);

    XYDataset xydataset2 = timeseriescollection2;
    JFreeChart jfreechart2 = ChartFactory.createTimeSeriesChart("CPU", "time", "CPU%", xydataset2, true, true, true);
    XYPlot xyplot2 = (XYPlot) jfreechart2.getPlot();
    xyplot2.setBackgroundPaint(Color.black);      //设置背景颜色
    xyplot2.setDomainGridlinePaint(Color.green);  //设置网格竖线颜色
    xyplot2.setRangeGridlinePaint(Color.green);   //设置网格横线颜色
    XYLineAndShapeRenderer xylinerenderer2=(XYLineAndShapeRenderer)xyplot2.getRenderer();
    xylinerenderer2.setSeriesPaint(0, new Color(0, 255 ,0 ));

    DateAxis dateaxis2 = (DateAxis) xyplot2.getDomainAxis();//获取x轴
    dateaxis2.setDateFormatOverride(new SimpleDateFormat("HH:mm"));

    dateaxis2.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
    dateaxis2.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题

    ValueAxis rangeAxis2=xyplot2.getRangeAxis();//获取y轴
    rangeAxis2.setRange(0, 100);
    rangeAxis2.setLabelFont(new Font("黑体",Font.BOLD,15));
    jfreechart2.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
    jfreechart2.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
    String filename2 = ServletUtilities.saveChartAsPNG(jfreechart2, 500, 360, null, session);
    String graphURL2 = request.getContextPath() + "/DisplayChart?filename=" + filename2;

//---------------------------------------------------------------------------------内存---------------------------------------------------------------------------------------------------------------
    TimeSeries timeseriesMem = new TimeSeries("internal memory", Minute.class);
    ArrayList<CpuBean> cpuBeanListMem = MonitorInfoList.cpuBeanListMem;
    if(cpuBeanListMem!=null){
        if(cpuBeanListMem.size()>0&&(cpuBeanListMem.get(cpuBeanListMem.size()-1).getCurrentMillis()-cpuBeanListMem.get(0).getCurrentMillis())<=(1000*60*59)){
            timeseriesMem.addOrUpdate(new Minute(miniute2,new Hour(hour2-1,new Day())), 0);
            timeseriesMem.addOrUpdate(new Minute( cpuBeanListMem.get(0).getMinute()-1, cpuBeanListMem.get(0).getHour() ), 0);
        }
    }
    session.setAttribute("cpuBeanListMem",cpuBeanListMem);
    for(CpuBean cpuBean :cpuBeanListMem){
        timeseriesMem.addOrUpdate(new Minute(cpuBean.getMinute(),cpuBean.getHour()), cpuBean.getCpuNum());
    }
    TimeSeriesCollection timeseriescollectionMem = new TimeSeriesCollection();
    timeseriescollectionMem.addSeries(timeseriesMem);

    XYDataset xydatasetMem = timeseriescollectionMem;
    JFreeChart jfreechartMem = ChartFactory.createTimeSeriesChart("internal memory", "time", "memory%", xydatasetMem, true, true, true);
    XYPlot xyplotMem = (XYPlot) jfreechartMem.getPlot();
    xyplotMem.setBackgroundPaint(Color.black);      //设置背景颜色
    xyplotMem.setDomainGridlinePaint(Color.green);  //设置网格竖线颜色
    xyplotMem.setRangeGridlinePaint(Color.green);   //设置网格横线颜色
    XYLineAndShapeRenderer xylinerendererMem=(XYLineAndShapeRenderer)xyplotMem.getRenderer();
    xylinerendererMem.setSeriesPaint(0, new Color(0, 255 ,0 ));

    DateAxis dateaxisMem = (DateAxis) xyplotMem.getDomainAxis();//获取x轴
    dateaxisMem.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
    dateaxisMem.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
    dateaxisMem.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题

    ValueAxis rangeAxisMem = xyplotMem.getRangeAxis();//获取y轴
    rangeAxisMem.setRange(0, 100);
    rangeAxisMem.setLabelFont(new Font("黑体",Font.BOLD,15));
    jfreechartMem.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
    jfreechartMem.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
    String filenameMem = ServletUtilities.saveChartAsPNG(jfreechartMem, 500, 360, null, session);
    String graphURLMem = request.getContextPath() + "/DisplayChart?filename=" + filenameMem;

%>

<html>
<head>
    <title>主机监控</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <script type="text/javascript">
        function setDX() {
            var bodywidth = document.body.clientWidth;
            var imgwidth = bodywidth/2.1;
            var imghight = 360*imgwidth/500;
            document.getElementById("img2").width=imgwidth;
            document.getElementById("img2").height=imghight;
            document.getElementById("img3").width=imgwidth;
            document.getElementById("img3").height=imghight;
            document.getElementById("img5").width=imgwidth;
            document.getElementById("img5").height=imghight;
            document.getElementById("img5").setAttribute("src","myjiframe.jsp");
        }
        setTimeout("document.myform.submit();",60000);
    </script>
</head>

<body   onload="setDX()">

<iframe id="img5" marginwidth="0" marginheight="0" border=0 frameBorder="0" scrolling="no" ></iframe>
<img id="img2" src="<%= graphURL2 %>"  border=0 >
<img id="img3" src="<%= graphURLMem %>"  border=0 >
<%--<form  id="img6" name="myform" method="post" action="myjfreezhexian.jsp">--%>
    <%--<input type="hidden" name="flagts" value="1">--%>
<%--</form>--%>
</body>

</html>