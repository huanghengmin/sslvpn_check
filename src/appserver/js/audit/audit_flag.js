Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var record = new Ext.data.Record.create([
        {name: 'audit_flag', mapping: 'audit_flag'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        url: "../../AuditFlagAction_find.action"
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: "totalCount",
        root: "root"
    }, record);

    var store = new Ext.data.GroupingStore({
        id: "store.info",
        proxy: proxy,
        reader: reader
    });

    store.load();
    store.on('load', function () {
        var audit_flag = store.getAt(0).get('audit_flag');
        Ext.getCmp('audit_flag').setValue(audit_flag);
    });


    var formPanel = new Ext.form.FormPanel({
        plain: true,
        width: 450,
        labelAlign: 'right',
        labelWidth: 80,
        defaultType: 'textfield',
        defaults: {
            anchor: '95%',
            allowBlank: false,
            blankText: '该项不能为空!'
        },
        items: [
            {
                xtype: 'checkboxgroup',
                //fieldLabel: '审计开关',
                columns: 1,
                allowBlank: true,
                items: [
                    {
                        boxLabel: '开启审计功能', id: 'audit_flag',  inputValue: 1,name: 'audit_flag'
                    }
                ]
            }
        ],
        buttons: [
            '->',
            {
                id: 'insert_win.info',
                text: '保存配置',
                handler: function () {
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url: "../../AuditFlagAction_save.action",
                            method: 'POST',
                            waitTitle: '系统提示',
                            waitMsg: '正在连接...',
                            success: function () {
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: '保存成功,点击返回页面!',
                                    buttons: Ext.MessageBox.OK,
                                    buttons: {'ok': '确定'},
                                    icon: Ext.MessageBox.INFO,
                                    closable: false
                                });
                            },
                            failure: function () {
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: '保存失败，请与管理员联系!',
                                    buttons: Ext.MessageBox.OK,
                                    buttons: {'ok': '确定'},
                                    icon: Ext.MessageBox.ERROR,
                                    closable: false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title: '信息',
                            width: 200,
                            msg: '请填写完成再提交!',
                            buttons: Ext.MessageBox.OK,
                            buttons: {'ok': '确定'},
                            icon: Ext.MessageBox.ERROR,
                            closable: false
                        });
                    }
                }
            }
        ]
    });

    var panel = new Ext.Panel({
        plain: true,
        width: 550,
        border: false,
        items: [{
            id: 'panel.info',
            xtype: 'fieldset',
            title: '审计配置',
            width: 500,
            items: [formPanel]
        }]
    });
    new Ext.Viewport({
        layout: 'fit',
        renderTo: Ext.getBody(),
        autoScroll: true,
        items: [{
            frame: true,
            autoScroll: true,
            items: [panel]
        }]
    });
});


