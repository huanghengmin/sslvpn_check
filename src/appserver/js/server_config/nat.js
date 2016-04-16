Ext.onReady(function () {

    Ext.BLANK_IMAGE_URL = '../../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;
    var pageSize = 15;
    var toolbar = new Ext.Toolbar({
        plain: true,
        items: [
            {
                id: 'add.info',
                xtype: 'button',
                text: '添加',
                iconCls: 'add',
                handler: function () {
                    addrule(grid_panel, store);
                }
            }
        ]
    });
    var record = new Ext.data.Record.create([
        {name: 'id', mapping: 'id'},
        {name: 'bindip', mapping: 'bindip'},
        {name: 'bindport', mapping: 'bindport'},
        {name: 'level', mapping: 'level'},
        {name: 'targetip', mapping: 'targetip'},
        {name: 'targetport', mapping: 'targetport'},
        {name: 'protocol', mapping: 'protocol'},
        {name: 'count', mapping: 'count'},
    ]);
    var proxy = new Ext.data.HttpProxy({
        url: "../../NatAction_find.action"
    });
    var reader = new Ext.data.JsonReader({
        totalProperty: "total",
        root: "rows",
        id: 'id'
    }, record);

    var store = new Ext.data.GroupingStore({
        id: "store.info",
        proxy: proxy,
        reader: reader
    });

    store.load({
        params:{
            start:start, limit:pageSize
        }
    });
    //var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var colM = new Ext.grid.ColumnModel([
        //boxM,
        rowNumber,
        {header: "绑定Ip", dataIndex: "bindip", align: 'center', sortable: true, menuDisabled: true},
        {header: "绑定端口", dataIndex: "bindport", align: 'center', sortable: true, menuDisabled: true},
        {header: "通迅协议", dataIndex: "protocol", align: 'center', sortable: true, menuDisabled: true},
        {header: "级别", dataIndex: "level", align: 'center', sortable: true, menuDisabled: true, renderer: show_level},
        {header: "目标Ip", dataIndex: "targetip", align: 'center', sortable: true, menuDisabled: true},
        {header: "目标端口", dataIndex: "targetport", align: 'center', sortable: true, menuDisabled: true},
        {header: "最大连接数", dataIndex: "count", align: 'center', sortable: true, menuDisabled: true},
        {header: '操作标记', dataIndex: "flag", align: 'center', sortable: true, menuDisabled: true, renderer: show_flag, width: 100}
    ]);
    var page_toolbar = new Ext.PagingToolbar({
        pageSize: pageSize,
        store: store,
        displayInfo: true,
        displayMsg: "显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg: "没有记录",
        beforePageText: "当前页",
        afterPageText: "共{0}页"
    });
    var grid_panel = new Ext.grid.GridPanel({
        id: 'grid.info',
        plain: true,
        viewConfig: {
            forceFit: true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
        },
        bodyStyle: 'width:100%',
        loadMask: {msg: '正在加载数据，请稍后...'},
        cm: colM,
        //sm: boxM,
        store: store,
        tbar: toolbar,
        bbar: page_toolbar,
        //title: '资源配置',
        columnLines: true,
        autoScroll: true,
        border: false,
        collapsible: false,
        stripeRows: true,
        autoExpandColumn: 'Position',
        enableHdMenu: true,
        enableColumnHide: true,
        selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
        height: 300,
        frame: true,
        iconCls: 'icon-grid'
    });

    var port = new Ext.Viewport({
        layout: 'fit',
        renderTo: Ext.getBody(),
        items: [grid_panel]
    });

});

function show_flag(){
    return String.format(
            '<a id="update_private.info" href="javascript:void(0);" onclick="update_rule();return false;" style="color: green;">修改</a>&nbsp;&nbsp;&nbsp;'+
            '<a id="delete_private.info" href="javascript:void(0);" onclick="delete_rule();return false;" style="color: green;">删除</a>&nbsp;&nbsp;&nbsp;'
    );
}

function show_level(value, p, r){
    if(r.get("level")=="0"){
        return String.format('一级');
    }else if(r.get("level")=="1"){
        return String.format('二级');
    }else if(r.get("level")=="2"){
        return String.format('三级');
    }
}

function addrule(grid,store){
    var level_data = [
        {'id': 0, 'name': '一级'},
        {'id': 1, 'name': '二级'},
        {'id': 2, 'name': '三级'}
    ];
    var level_store = new Ext.data.JsonStore({
        data: level_data,
        fields: ['id', 'name']
    });

    var protocol_data = [
        {'id': 'tcp', 'name': 'TCP'},
        {'id': 'udp', 'name': 'UDP'}
    ];
    var protocol_store = new Ext.data.JsonStore({
        data: protocol_data,
        fields: ['id', 'name']
    });
    var formPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        labelWidth:150,
        labelAlign:'right',
        defaultWidth:300,
        autoWidth:true,
        layout:'form',
        border:false,
        defaults : {
            width : 250,
            allowBlank : false,
            blankText : '该项不能为空！'
        },
        items:[
            new Ext.form.TextField({
                fieldLabel : '绑定IP',
                name : 'nat.bindIp',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"rule_bindIp",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '绑定端口',
                id:"rule_bindPort",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'nat.bindPort',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),

            new Ext.form.TextField({
                fieldLabel : '目标IP',
                name : 'nat.targetIp',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"rule_targetIp",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '目标端口',
                id:"rule_targetPort",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'nat.targetPort',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '通迅协议',
                fieldLabel: '通迅协议',
                id: 'rule_protocol',
                triggerAction: "all",// 是否开启自动查询功能
                store: protocol_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'nat.protocol',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                //emptyText: '资源级别',
                fieldLabel: '资源级别',
                id: 'rule_level',
                triggerAction: "all",// 是否开启自动查询功能
                store: level_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'nat.level',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.NumberField({
                fieldLabel : '最大连接数',
                id:"rule_count",
                minValue: 1,
                maxValue: 300,
                name : 'nat.count',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            })
        ]
    });
    var win = new Ext.Window({
        title:"添加",
        width:500,
        layout:'fit',
        height:300,
        modal:true,
        items:formPanel,
        bbar:[
            '->',
            {
                id:'add_win.info',
                text:'确定',
                width:50,
                handler:function(){
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url :'../../NatAction_add.action',
                            timeout: 20*60*1000,
                            method :'POST',
                            waitTitle :'系统提示',
                            waitMsg :'正在连接...',
                            success : function(form, action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:msg,
                                    buttons:Ext.MessageBox.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false,
                                    fn:function(e){
                                        store.reload();
                                        win.close();
                                    }
                                });
                            },
                            failure : function(form, action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:msg,
                                    buttons:Ext.MessageBox.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title:'信息',
                            width:200,
                            msg:'请填写完成再提交!',
                            buttons:Ext.MessageBox.OK,
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            },{
                text:'重置',
                width:50,
                handler:function(){
                    formPanel.getForm().reset();
                }
            }
        ]
    }).show();
}

function update_rule(){

    var level_data = [
        {'id': 0, 'name': '一级'},
        {'id': 1, 'name': '二级'},
        {'id': 2, 'name': '三级'}
    ];
    var level_store = new Ext.data.JsonStore({
        data: level_data,
        fields: ['id', 'name']
    });

    var protocol_data = [
        {'id': 'tcp', 'name': 'TCP'},
        {'id': 'udp', 'name': 'UDP'}
    ];
    var protocol_store = new Ext.data.JsonStore({
        data: protocol_data,
        fields: ['id', 'name']
    });

    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    var formPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        labelWidth:150,
        labelAlign:'right',
        defaultWidth:300,
        autoWidth:true,
        layout:'form',
        border:false,
        defaults : {
            width : 250,
            allowBlank : false,
            blankText : '该项不能为空！'
        },
        items:[
            new Ext.form.TextField({
                fieldLabel : '绑定IP',
                name : 'nat.bindIp',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"modify_rule_bindIp",
                value:recode.get('bindip'),
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '绑定端口',
                id:"modify_rule_bindPort",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'nat.bindPort',
                value:recode.get('bindport'),
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),

            new Ext.form.TextField({
                fieldLabel : '目标IP',
                name : 'nat.targetIp',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"modify_rule_targetIp",
                value:recode.get('targetip'),
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '目标端口',
                value:recode.get('targetport'),
                id:"modify_rule_targetPort",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'nat.targetPort',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '通迅协议',
                value:recode.get('protocol'),

                fieldLabel: '通迅协议',
                id: 'modify_rule_protocol',
                triggerAction: "all",// 是否开启自动查询功能
                store: protocol_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'nat.protocol',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                fieldLabel: '资源级别',
                id: 'modify_rule_level',
                //readOnly:true,
                value: recode.get('level'),
                triggerAction: "all",// 是否开启自动查询功能
                store: level_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'nat.level',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.NumberField({
                fieldLabel : '最大连接数',
                id:"modify_rule_count",
                minValue: 1,
                value: recode.get('count'),
                maxValue: 300,
                name : 'nat.count',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            })
        ]
    });
    var win = new Ext.Window({
        title:"修改",
        width:500,
        layout:'fit',
        height:300,
        modal:true,
        items:formPanel,
        bbar:[
            '->',
            {
                id:'add_win.info',
                text:'确定',
                width:50,
                handler:function(){
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url :'../../NatAction_modify.action',
                            timeout: 20*60*1000,
                            params:{
                                id:recode.get("id")
                            },
                            method :'POST',
                            waitTitle :'系统提示',
                            waitMsg :'正在连接...',
                            success : function(form, action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:msg,
                                    buttons:Ext.MessageBox.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false,
                                    fn:function(e){
                                        grid_panel.getStore().reload();
                                        win.close();
                                    }
                                });
                            },
                            failure : function(form, action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:msg,
                                    buttons:Ext.MessageBox.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title:'信息',
                            width:200,
                            msg:'请填写完成再提交!',
                            buttons:Ext.MessageBox.OK,
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            },{
                text:'重置',
                width:50,
                handler:function(){
                    formPanel.getForm().reset();
                }
            }
        ]
    }).show();
}

function delete_rule(){
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    if(!recode){
        Ext.Msg.alert("提示", "请选择一条记录!");
    }else{
        Ext.Msg.confirm("提示", "确定删除这条记录？", function(sid) {
            if (sid == "yes") {
                Ext.Ajax.request({
                    url : "../../NatAction_remove.action",
                    timeout: 20*60*1000,
                    method : "POST",
                    params:{
                        id:recode.get("id")
                    },
                    success : function(r,o){
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                        grid_panel.getStore().reload();
                    },
                    failure : function(r,o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                    }
                });
            }
        });
    }
}