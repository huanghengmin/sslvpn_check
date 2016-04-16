Ext.onReady(function() {

    var store = new Ext.data.JsonStore({
        url:"../../AuditReportAction_getReportCount.action",
        root : 'rows',
        totalProperty:"total",
        fields : [ 'name',  'count','code']
    });

    var chart = new Ext.Panel({
        //title: '审计事件统计图',
        height:300,
        layout:'fit',
        items: {
            xtype: 'columnchart',
            store: store,
            xField: 'name',
            url : '../../js/ext/resources/charts.swf',
            yField: 'count',
            /*listeners: {
                itemclick: function(o){
                    var rec = store.getAt(o.index);
                    Ext.MessageBox.alert('审计事件', '选择 {0}.', rec.get('name'));
                }
            },*/
            tipRenderer : function(chart, record) {
                return  '事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');

            }
        }
    });

    /*var chart2 = new Ext.Panel({
        iconCls:'chart',
        title: 'ExtJS.com Visits and Pageviews, 2007/2008 (Full styling)',
        frame:true,
        height:300,
        layout:'fit',
        items: {
            xtype: 'columnchart',
            store: store,
            url : '../../js/ext/resources/charts.swf',
            xField: 'name',
            yAxis: new Ext.chart.NumericAxis({
                displayName: '数量',
                labelRenderer : Ext.util.Format.numberRenderer('0,0')
            }),
            tipRenderer : function(chart, record, index, series){
                if(series.yField == 'count'){
                    return Ext.util.Format.number(record.data.count, '0,0') + ' count in ' + record.data.name;
                }else{
                    return Ext.util.Format.number(record.data.code, '0,0') + ' page code in ' + record.data.name;
                }
            },
            series: [{
                type:'line',
                displayName: 'Count',
                yField: 'count',
                style: {
                    color: 0x15428B
                }
            },{
                type:'line',
                displayName: 'Code',
                yField: 'code',
                style: {
                    color: 0xffff00
                }
            }]
        }
    });*/

    new Ext.Viewport({
        layout:'fit',
        renderTo: Ext.getBody(),
        items:[chart]
    });

    store.load();
});