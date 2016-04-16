Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;
    var pageSize = 15;

    var resourceRecord = Ext.data.Record.create(
        [
            {name: 'ip', mapping: 'ip'},
            {name: 'id', mapping: 'id'},
            {name: 'startTime', mapping: 'startTime'},
            {name: 'endTime', mapping: 'endTime'},
            {name: 'mac', mapping: 'mac'},
            {name: 'status', mapping: 'status'},
        ]);

    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url: 'TrustTerminalAction_find.action', method: 'POST'}),

        reader: new Ext.data.JsonReader({
            totalProperty: "total",
            root: 'rows'
        }, resourceRecord)
    });

    store.load({
        params: {
            start: start, limit: pageSize
        }
    });


    var state = function (value) {
        if (value == "1") {
            return "<img src='../../img/icon/ok.png'  alt='信任'/> ";
        } else if (value == "0") {
            return "<img src='../../img/icon/off.gif' alt='未信任'/> ";
        }
    }

    var state_action = function (value) {
        if (value == "1") {
            return String.format(
                '<a id="disable.info" href="javascript:void(0);" onclick="disable();return false;" style="color: green;">停用</a>&nbsp;&nbsp;&nbsp;'
            );
        } else if (value == "0") {
            return String.format(
                '<a id="enable.info" href="javascript:void(0);" onclick="enable();return false;" style="color: green;">启用</a>&nbsp;&nbsp;&nbsp;'
            );
        }
    }

    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var resourceCm = new Ext.grid.ColumnModel([
        rowNumber,
        {header: "客户端IP", width: 100, dataIndex: 'ip', align: 'center', sortable: true, menuDisabled: true},
        {header: "客户端MAC", width: 100, dataIndex: 'mac', align: 'center', sortable: true, menuDisabled: true},
        {header: "开始时间", width: 100, dataIndex: 'startTime', align: 'center', sortable: true, menuDisabled: true},
        {header: "结束时间", width: 100, dataIndex: 'endTime', align: 'center', sortable: true, menuDisabled: true},
        {
            header: "当前状态",
            width: 100,
            dataIndex: 'status',
            align: 'center',
            sortable: true,
            menuDisabled: true,
            renderer: state
        }, {
            header: "操作",
            width: 100,
            dataIndex: 'status',
            align: 'center',
            sortable: true,
            menuDisabled: true,
            renderer: state_action
        }
    ]);

    var grid = new Ext.grid.GridPanel({
        id: 'grid.info',
        store: store,
        cm: resourceCm,
        //title: '信任终端管理',
        viewConfig: {
            forceFit: true
        },
        columnLines: true,
        autoScroll: true,
        loadMask: {msg: '正在加载数据，请稍后...'},
        border: false,
        collapsible: false,
        stripeRows: true,
        //autoExpandColumn: 'Position',
        //enableHdMenu: true,
        //enableColumnHide: true,
        //selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
        height: 300,
        frame: true,
        //iconCls: 'icon-grid',
        tbar: [{
            text: '新增信任终端',
            iconCls: 'add',
            handler: function () {
                add(grid, store);
            }
        }, '-', {
            text: '编辑信任终端',
            iconCls: 'upgrade',
            handler: function () {
                mod(grid, store);
            }
        }, '-', {
            text: '删除信任终端',
            iconCls: 'remove',
            handler: function () {
                del(grid, store);
            }
        }]
    });
    new Ext.Viewport({
        border: false,
        renderTo: Ext.getBody(),
        layout: 'fit',
        items: [grid],
        autoScroll: true
    });


});

function add(grid, store) {
    var formPanel = new Ext.form.FormPanel({
        frame: true,
        autoScroll: true,
        labelWidth: 120, // label settings here cascade unless overridden
        border: false,
        bodyStyle: 'padding:5px 5px 0',
        width: 500,
        waitMsgTarget: true,
        defaults: {
            //width: 230,
            anchor:'85%',
            allowBlank: false,
            blankText: '该项不能为空'
        },
        defaultType: 'textfield', //
        items: [
            {
                id: 'ip.info',
                fieldLabel: "客户端IP",
                name: 'trustTerminal.ip',
                regex: /^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText: '请输入正确的ip地址',
                emptyText: '请输入Ip'
            }, {
                id: 'mac.info',
                fieldLabel: '客户端MAC',
                name: 'trustTerminal.mac',
                regex: /([0-9a-fA-F]{2})(([/\s:-][0-9a-fA-F]{2}){5})/,
                regexText: '请输入正确的mac地址',
                emptyText: '请输入网卡mac'
            }, {
                id: 'startTime.info',
                fieldLabel: '开始时间',
                name: 'trustTerminal.startTime',
                value:'00:00:00',
                emptyText: '只能输入00:00:00--23:59:59'
            }, {
                id: 'endTime.info',
                fieldLabel: '结束时间',
                name: 'trustTerminal.endTime',
                value:'23:59:59',
                emptyText: '只能输入00:00:00--23:59:59'
            }]
    });
    var win = new Ext.Window({
        title: '新增信任终端',
        width: 500,
        layout: 'fit',
        height: 250,
        modal: true,
        items: formPanel,
        bbar: [
            '->', {
                id: 'insert_win.info',
                text: '保存',
                handler: function () {
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url: 'TrustTerminalAction_add.action',
                            method: 'POST',
                            waitTitle: '系统提示',
                            waitMsg: '正在保存,请稍后...',
                            success: function (form, action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: msg,
                                    animEl: 'insert_win.info',
                                    buttons: {'ok': '确定', 'no': '取消'},
                                    icon: Ext.MessageBox.INFO,
                                    closable: false,
                                    fn: function (e) {
                                        if (e == 'ok') {
                                            grid.render();
                                            store.reload();
                                            win.close();
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title: '信息',
                            width: 200,
                            msg: '请填写完成再提交!',
                            animEl: 'insert_win.info',
                            buttons: {'ok': '确定'},
                            icon: Ext.MessageBox.ERROR,
                            closable: false
                        });
                    }
                }
            }
        ]
    });
    win.show();
}

function mod(grid, store) {
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    var formPanel = new Ext.form.FormPanel({
        frame: true,
        autoScroll: true,
        labelWidth: 120, // label settings here cascade unless overridden
        border: false,
        bodyStyle: 'padding:5px 5px 0',
        width: 500,
        waitMsgTarget: true,
        defaults: {
            width: 230,
            allowBlank: false
        },
        defaultType: 'textfield', //
        items: [{
            id: 'ip.info',
            fieldLabel: "客户端IP",
            name: 'trustTerminal.ip',
            regex: /^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
            regexText: '请输入正确的Ip地址',
            emptyText: '请输入Ip',
            value: recode.get('ip')
        }, {
            id: 'mac.info',
            fieldLabel: '客户端MAC',
            name: 'trustTerminal.mac',
            regex: /([0-9a-fA-F]{2})(([/\s:-][0-9a-fA-F]{2}){5})/,
            regexText: '请输入正确的mac地址',
            emptyText: '请输入网卡mac',
            value: recode.get('mac')
        },{
            id: 'startTime.info',
            fieldLabel: '开始时间',
            name: 'trustTerminal.startTime',
            value: recode.get('startTime'),
            //value:'00:00:00',
            emptyText: '只能输入00:00:00--23:59:59'
        }, {
            id: 'endTime.info',
            fieldLabel: '结束时间',
            value: recode.get('endTime'),
            name: 'trustTerminal.endTime',
            //value:'23:59:59',
            emptyText: '只能输入00:00:00--23:59:59'
        }]
    });
    var win = new Ext.Window({
        title: '修改信任终端',
        width: 500,
        layout: 'fit',
        height: 250,
        modal: true,
        items: formPanel,
        bbar: [
            '->', {
                id: 'update_win.info',
                text: '保存',
                handler: function () {
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url: 'TrustTerminalAction_mod.action',
                            method: 'POST',
                            params: {ip: recode.get('ip'),mac: recode.get('mac'),startTime:recode.get('startTime'),endTime:recode.get('endTime')},
                            waitTitle: '系统提示',
                            waitMsg: '正在保存,请稍后...',
                            success: function (form, action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: msg,
                                    animEl: 'update_win.info',
                                    buttons: {'ok': '确定', 'no': '取消'},
                                    icon: Ext.MessageBox.INFO,
                                    closable: false,
                                    fn: function (e) {
                                        if (e == 'ok') {
                                            grid.render();
                                            store.reload();
                                            win.close();
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title: '信息',
                            width: 200,
                            msg: '请填写完成再提交!',
                            animEl: 'update_win.info',
                            buttons: {'ok': '确定'},
                            icon: Ext.MessageBox.ERROR,
                            closable: false
                        });
                    }
                }
            }
        ]
    });
    win.show();
}

function del(grid, store) {
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    if (!recode) {
        Ext.Msg.alert("提示", "请选择一条记录!");
    } else {
        Ext.Msg.confirm("提示", "确定删除信任终端？", function (sid) {
            if (sid == "yes") {
                Ext.Ajax.request({
                    url: "../../TrustTerminalAction_del.action",
                    timeout: 20 * 60 * 1000,
                    method: "POST",
                    params: {ip: recode.get('ip'),mac: recode.get('mac'),startTime:recode.get('startTime'),endTime:recode.get('endTime')},
                    success: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                        grid_panel.getStore().reload();
                    },
                    failure: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                    }
                });
            }
        });
    }
}

function enable(grid, store) {
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    if (!recode) {
        Ext.Msg.alert("提示", "请选择一条记录!");
    } else {
        Ext.Msg.confirm("提示", "确定启用信任终端？", function (sid) {
            if (sid == "yes") {
                Ext.Ajax.request({
                    url: "../../TrustTerminalAction_enable.action",
                    timeout: 20 * 60 * 1000,
                    method: "POST",
                    params: {ip: recode.get('ip'),mac: recode.get('mac'),startTime:recode.get('startTime'),endTime:recode.get('endTime')},
                    success: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                        grid_panel.getStore().reload();
                    },
                    failure: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                    }
                });
            }
        });
    }
}

function disable(grid, store) {
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    if (!recode) {
        Ext.Msg.alert("提示", "请选择一条记录!");
    } else {
        Ext.Msg.confirm("提示", "确定停用信任终端？", function (sid) {
            if (sid == "yes") {
                Ext.Ajax.request({
                    url: "../../TrustTerminalAction_disable.action",
                    timeout: 20 * 60 * 1000,
                    method: "POST",
                    params: {ip: recode.get('ip'),mac: recode.get('mac'),startTime:recode.get('startTime'),endTime:recode.get('endTime')},
                    success: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                        grid_panel.getStore().reload();
                    },
                    failure: function (r, o) {
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert("提示", msg);
                    }
                });
            }
        });
    }
}




