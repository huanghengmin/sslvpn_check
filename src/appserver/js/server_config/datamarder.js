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
                    addDataMaker(grid_panel, store);
                }
            }
        ]
    });
    var record = new Ext.data.Record.create([
        {name: 'sourceip', mapping: 'sourceip'},
        {name: 'sourceport', mapping: 'sourceport'},
        {name: 'sourcemask', mapping: 'sourcemask'},
        {name: 'targetip', mapping: 'targetip'},
        {name: 'targetmask', mapping: 'targetmask'},
        {name: 'targetport', mapping: 'targetport'},
        {name: 'protocol', mapping: 'protocol'},
        {name: 'sensitivelevel', mapping: 'sensitivelevel'},
        {name: 'processmode', mapping: 'processmode'}
    ]);
    var proxy = new Ext.data.HttpProxy({
        url: "../../DataMakerAction_find.action"
    });
    var reader = new Ext.data.JsonReader({
        totalProperty: "list",
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
        {header: "源地址", dataIndex: "sourceip", align: 'center', sortable: true, menuDisabled: true},
        {header: "源端口", dataIndex: "sourceport", align: 'center', sortable: true, menuDisabled: true},
        {header: "源掩码", dataIndex: "sourcemask", align: 'center', sortable: true, menuDisabled: true},
        {header: "目标地址", dataIndex: "targetip", align: 'center', sortable: true, menuDisabled: true},
        {header: "目标端口", dataIndex: "targetport", align: 'center', sortable: true, menuDisabled: true},
        {header: "目标掩码", dataIndex: "targetmask", align: 'center', sortable: true, menuDisabled: true},
        {header: "通迅协议", dataIndex: "protocol", align: 'center', sortable: true, menuDisabled: true},
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
        '<a id="update_private.info" href="javascript:void(0);" onclick="update_DataMaker();return false;" style="color: green;">修改</a>&nbsp;&nbsp;&nbsp;'+
        '<a id="delete_private.info" href="javascript:void(0);" onclick="delete_DataMaker();return false;" style="color: green;">删除</a>&nbsp;&nbsp;&nbsp;'
    );
}

function addDataMaker(grid,store){
    var sensitivelevel_data = [
        {'id': '001', 'name': '普通'},
        {'id': '002', 'name': '秘密'},
        {'id': '003', 'name': '机密'},
        {'id': '004', 'name': '绝密'}
    ];
    var sensitivelevel_store = new Ext.data.JsonStore({
        data: sensitivelevel_data,
        fields: ['id', 'name']
    });

    var processmode_data = [
        {'id': '001', 'name': '丢弃'},
        {'id': '002', 'name': '直接转发'},
        {'id': '003', 'name': '加密保护'},
        {'id': '004', 'name': '完整性保护'}
    ];
    var processmode_store = new Ext.data.JsonStore({
        data: processmode_data,
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
                fieldLabel : '源地址',
                name : 'sourceip',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"datamaker.sourceip",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '源端口',
                id:"datamaker.sourceport",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'sourceport',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),

            new Ext.form.TextField({
                fieldLabel : '源掩码',
                name : 'sourcemask',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"datamaker.sourcemask",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '目标地址',
                name : 'targetip',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"datamaker.targetip",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '目标端口',
                id:"datamaker.targetport",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'targetport',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),

            new Ext.form.TextField({
                fieldLabel : '目标掩码',
                name : 'targetmask',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"datamaker.targetmask",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '通迅协议',
                fieldLabel: '通迅协议',
                id: 'datamaker.protocol',
                triggerAction: "all",// 是否开启自动查询功能
                store: protocol_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'protocol',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '敏感等级',
                fieldLabel: '敏感等级',
                id: 'datamaker.sensitivelevel',
                triggerAction: "all",// 是否开启自动查询功能
                store: sensitivelevel_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'sensitivelevel',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '处理方式',
                fieldLabel: '处理方式',
                id: 'datamaker.processmode',
                triggerAction: "all",// 是否开启自动查询功能
                store: processmode_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'processmode',
                allowBlank: false,
                blankText: "请选择"
            })
        ]
    });
    var win = new Ext.Window({
        title:"添加",
        width:500,
        layout:'fit',
        height:350,
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
                            url :'../../DataMakerAction_add.action',
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

function update_DataMaker(){
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();

    var sensitivelevel_data = [
        {'id': '001', 'name': '普通'},
        {'id': '002', 'name': '秘密'},
        {'id': '003', 'name': '机密'},
        {'id': '004', 'name': '绝密'}
    ];
    var sensitivelevel_store = new Ext.data.JsonStore({
        data: sensitivelevel_data,
        fields: ['id', 'name']
    });

    var processmode_data = [
        {'id': '001', 'name': '丢弃'},
        {'id': '002', 'name': '直接转发'},
        {'id': '003', 'name': '加密保护'},
        {'id': '004', 'name': '完整性保护'}
    ];
    var processmode_store = new Ext.data.JsonStore({
        data: processmode_data,
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
                fieldLabel : '源地址',
                name : 'sourceip',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"datamaker.sourceip",
                value:recode.get('sourceip'),
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '源端口',
                id:"datamaker.sourceport",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'sourceport',
                value:recode.get('sourceport'),
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),

            new Ext.form.TextField({
                fieldLabel : '源掩码',
                name : 'sourcemask',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                value:recode.get('sourcemask'),
                id:"datamaker.sourcemask",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '目标地址',
                value:recode.get('targetip'),
                name : 'targetip',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"datamaker.targetip",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : '目标端口',
                value:recode.get('targetport'),
                id:"datamaker.targetport",
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'请输入正确的端口',
                name : 'targetport',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),

            new Ext.form.TextField({
                fieldLabel : '目标掩码',
                name : 'targetmask',
                value:recode.get('targetmask'),
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的IP地址',
                id:"datamaker.targetmask",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '通迅协议',
                fieldLabel: '通迅协议',
                value:recode.get('protocol'),
                id: 'datamaker.protocol',
                triggerAction: "all",// 是否开启自动查询功能
                store: protocol_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'protocol',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '敏感等级',
                fieldLabel: '敏感等级',
                value:recode.get('sensitivelevel'),
                id: 'datamaker.sensitivelevel',
                triggerAction: "all",// 是否开启自动查询功能
                store: sensitivelevel_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'sensitivelevel',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '处理方式',
                value:recode.get('processmode'),
                fieldLabel: '处理方式',
                id: 'datamaker.processmode',
                triggerAction: "all",// 是否开启自动查询功能
                store: processmode_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'processmode',
                allowBlank: false,
                blankText: "请选择"
            })
        ]
    });
    var win = new Ext.Window({
        title:"修改",
        width:500,
        layout:'fit',
        height:350,
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
                            url :'../../DataMakerAction_mod.action',
                            timeout: 20*60*1000,
                            params:{
                                oldSourceip:recode.get("sourceip"),
                                oldSourceport:recode.get("sourceport"),
                                oldSourcemask:recode.get("sourcemask"),
                                oldTargetip:recode.get("targetip"),
                                oldTargetmask:recode.get("targetmask"),
                                oldTargetport:recode.get("targetport"),
                                oldProtocol:recode.get("protocol")
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

function delete_DataMaker(){
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    if(!recode){
        Ext.Msg.alert("提示", "请选择一条记录!");
    }else{
        Ext.Msg.confirm("提示", "确定删除这条记录？", function(sid) {
            if (sid == "yes") {
                Ext.Ajax.request({
                    url : "../../DataMakerAction_del.action",
                    timeout: 20*60*1000,
                    method : "POST",
                    params:{
                        sourceip:recode.get("sourceip"),
                        sourceport:recode.get("sourceport"),
                        sourcemask:recode.get("sourcemask"),
                        targetip:recode.get("targetip"),
                        targetmask:recode.get("targetmask"),
                        targetport:recode.get("targetport"),
                        protocol:recode.get("protocol")
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