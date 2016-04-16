/**
 * 平台配置
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 11-9-21
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var threshold_value = [
        {'id': '0', 'name': '忽略可审计事件'},
        {'id': '1', 'name': '覆盖最老的审计记录'}
    ];
    var threshold_store = new Ext.data.JsonStore({
        data: threshold_value,
        fields: ['id', 'name']
    });

    var initForm = new Ext.form.FormPanel({
        plain: true,
        border: false,
        loadMask: {msg: '正在加载数据，请稍后.....'},
        labelAlign: 'right',
        labelWidth: 150,
        reader: new Ext.data.JsonReader({}, [{
            name: 'slider_backup'
        }, {
            name: 'slider_process'
        }, {
            name: 'local_backup'
        }, {
            name: 'local_backup_path'
        }, {
            name: 'ftp_backup'
        }, {
            name: 'ftp_host'
        }, {
            name: 'ftp_port'
        }, {
            name: 'ftp_user'
        }, {
            name: 'ftp_pass'
        }, {
            name: 'ftp_path'
        }, {
            name: 'backup_flag'
        }, {
            name: 'conf_time'
        }, {
            name: 'conf_day'
        },{
            name: 'conf_type'
        }, {
            name: 'conf_time2'
        }, {
            name: 'conf_month_day'
        }, {
            name: 'conf_time3'
        }]),
        defaultType: 'textfield',
        defaults: {
            allowBlank: false,
            blankText: '该项不能为空!'
        },
        items: [
            {
                fieldLabel: "存储阀值%",
                xtype: 'numberfield',
                width: 300,
                minValue: 30,
                maxValue: 95,
                //regex: /^[1-9]|[1-2][0-9]|3[0-1]$/,
                //regexText: '只能输入0-31',
                //id: 'storege.info',
                name: 'slider_backup'
            },/* {
                fieldLabel: '滑动设置',
                //id: 'storege.slider.info',
                xtype: 'slider',
                increment: 1,
                minValue: 30,
                width: 300,
                maxValue: 95,
                plugins: new Ext.slider.Tip({
                    getText: function (thumb) {
                        Ext.getCmp('storege.info').setValue(thumb.value );
                        return String.format('<b>{0}% 容量阀值</b>', thumb.value);
                    }
                })
            },*/
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                width: 300,
                emptyText: '存储阀值处理',
                fieldLabel: '存储阀值处理',
                triggerAction: "all",// 是否开启自动查询功能
                store: threshold_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'slider_process',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.FieldSet({
                title: '本地备份',
                checkboxToggle: true,
                autoHeight: true,
                checkboxName: 'local_backup',
                autoWidth: true,
                labelWidth: 100,
                border: false,
                defaultType: 'textfield',
                defaults: {
                    width: 200,
                    allowBlank: false,
                    blankText: '该项不能为空!'
                },
                items: [{
                    fieldLabel: '路径',
                    name: 'local_backup_path',
                    value:'/data/backup/',
                    width: 300,
                    emptyText: '本地备份路径'
                }]
            }),
            new Ext.form.FieldSet({
                title: 'Ftp备份',
                checkboxToggle: true,
                autoHeight: true,
                checkboxName: 'ftp_backup',
                autoWidth: true,
                labelWidth: 100,
                border: false,
                defaultType: 'textfield',
                defaults: {
                    width: 200,
                    allowBlank: false,
                    blankText: '该项不能为空!'
                },
                items: [{
                    fieldLabel: '主机',
                    name: 'ftp_host',
                    width: 300,
                    regex: /^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                    regexText: '这个不是IP(例:1.1.1.1)',
                    emptyText: '请输入IP(例:1.1.1.1)'
                }, {
                    fieldLabel: '端口',
                    name: 'ftp_port',
                    width: 300,
                    regex: /^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                    regexText: '这个不是端口类型1—65536',
                    emptyText: '请输入端口1—65536'
                }, {
                    fieldLabel: '帐号',
                    name: 'ftp_user',
                    width: 300,
                    emptyText: '帐号'
                }, {
                    fieldLabel: '密码',
                    name: 'ftp_pass',
                    inputType: 'password',
                    width: 300,
                    emptyText: '密码'
                }, {
                    fieldLabel: '路径',
                    name: 'ftp_path',
                    width: 300,
                    emptyText: '路径'
                }]
            }),
            new Ext.form.FieldSet({
                title: '启用备份',
                checkboxToggle: true,
                autoHeight: true,
                border: false,
                checkboxName: 'backup_flag',
                defaultType: 'textfield',
                items: [{
                    xtype: 'compositefield',
                    hideLabel: true,
                    items: [{
                        name: 'conf_type',
                        xtype: 'radio',
                        inputValue: 'day',
                        boxLabel: '按日备份'
                    }, {
                        xtype: 'textfield',
                        name: 'conf_time',
                        width: 50,
                        value: '23:00',
                        //regex: /^([0][0-9]|[1][0-9]|[2][0-3]|[0-9]):([0-5][0-9])$/,
                        regexText: '只能输入00:00--23:59'/*,
                        emptyText: '请输入00:00--23:59'*/
                    }]
                }, {
                    xtype: 'compositefield',
                    hideLabel: true,
                    items: [{
                        name: 'conf_type',
                        xtype: 'radio',
                        inputValue: 'week',
                        boxLabel: '按周备份'
                    }, {
                        xtype: 'radiogroup',
                        columns: 4,
                        vertical: false,
                        width: 300,
                        items: [{
                            boxLabel: '周一',
                            inputValue: 1,
                            name: 'conf_day'
                        }, {
                            boxLabel: '周二',
                            inputValue: 2,
                            name: 'conf_day'
                        }, {
                            boxLabel: '周三',
                            inputValue: 3,
                            name: 'conf_day'
                        }, {
                            boxLabel: '周四',
                            inputValue: 4,
                            name: 'conf_day'
                        }, {
                            boxLabel: '周五',
                            inputValue: 5,
                            name: 'conf_day'
                        }, {
                            boxLabel: '周六',
                            inputValue: 6,
                            name: 'conf_day'
                        }, {
                            boxLabel: '周日',
                            inputValue: 7,
                            name: 'conf_day'
                        }],
                        name: 'conf_day'
                    }, {
                        xtype: 'textfield',
                        name: 'conf_time2',
                        width: 50,
                        value: '23:00',
                        //regex: /^([0][0-9]|[1][0-9]|[2][0-3]|[0-9]):([0-5][0-9])$/,
                        regexText: '只能输入00:00--23:59'/*,
                        emptyText: '请输入00:00--23:59'*/
                    }]
                }, {
                    xtype: 'compositefield',
                    hideLabel: true,
                    items: [{
                        name: 'conf_type',
                        xtype: 'radio',
                        inputValue: 'month',
                        boxLabel: '按月备份'
                    }, {
                        xtype: 'textfield',
                        name: 'conf_month_day',
                        width: 40,
                        regex: /^[1-9]|[1-2][0-9]|3[0-1]$/,
                        regexText: '只能输入0-31'/*,
                        emptyText: '请输入0-31'*/
                    }, {
                        xtype: 'displayfield',
                        value: '日'
                    }, {
                        xtype: 'textfield',
                        name: 'conf_time3',
                        width: 50,
                        value: '23:00',
                    //    regex: /^([0][0-9]|[1][0-9]|[2][0-3]|[0-9]):([0-5][0-9])$/,
                        regexText: '只能输入00:00--23:59'/*,
                        emptyText: '请输入00:00--23:59'*/
                    }]
                }]
            })
        ],
        buttons: [  {
            id: 'insert_win.info',
            text: '保存配置',
            autoWidth: true,
            handler: function () {
                if (initForm.form.isValid()) {
                    initForm.getForm().submit({
                        url: "../../AuditStorageAction_update.action",
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
        }]
    });

    // 加载配置数据
    if (initForm) {
        initForm.getForm().load({
            url: '../../AuditStorageAction_find.action',
            success: function (form, action) {

            },
            failure: function (form, action) {
                Ext.Msg.alert('错误', '加载数据出错！');
            }
        });
    }

    var panel = new Ext.Panel({
        plain: true,
        autoWidth: true,
        border: false,
        autoScroll: true,
        items: [{
            xtype: 'fieldset',
            title: '审计存储',
            width: 550,
            items: [initForm]
        }]
    });

    new Ext.Viewport({
        layout: 'fit',
        renderTo: Ext.getBody(),
        items: [{
            frame: true,
            autoScroll: true,
            items: [panel]
        }]
    });
});





