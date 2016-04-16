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


    var verity_value = [
        {'id': '0', 'name': '记录鉴别错误事件'},
        {'id': '1', 'name': '生成实时报警信息'}
        //{'id': '1', 'name': '通知VPN安全管理员'},
        //{'id': '2', 'name': '终止该用户的访问'},
        //{'id': '3', 'name': '切断与相应主机的通信'}
    ];
    var verity_store = new Ext.data.JsonStore({
        data: verity_value,
        fields: ['id', 'name']
    });

    var audit_value = [
        {'id': '0', 'name': '审计数据入库'},
        {'id': '1', 'name': '生成实时报警信息'}/*,
         {'id': '2', 'name': '终端违例进程'},
         {'id': '3', 'name': '终止VPN隧道'}*/
    ];
    var audit_store = new Ext.data.JsonStore({
        data: audit_value,
        fields: ['id', 'name']
    });

    var formPanel = new Ext.form.FormPanel({
        plain: true,
        border: false,
        loadMask: {msg: '正在加载数据，请稍后.....'},
        labelAlign: 'right',
        labelWidth: 200,
        defaultType: 'textfield',
        reader: new Ext.data.JsonReader({}, [{
            name: 'audit_flag'
        }, {
            name: 'verity_flag'
        }, {
            name: 'client_flag'
        }, {
            name: 'admin_flag'
        }, {
            name: 'build_flag'
        }, {
            name: 'build_num_flag'
        }, {
            name: 'full_flag'
        }, {
            name: 'decode_flag'
        }, {
            name: 'discard_flag'
        }, {
            name: 'storage_flag'
        }, {
            name: 'replay_flag'
        }]),
        defaults: {
            width: 300,
            allowBlank: false,
            blankText: '该项不能为空!'
        },
        items: [
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '审计的启用与关闭',
                fieldLabel: '审计的启用与关闭',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'audit_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '鉴别失败事件处理',
                fieldLabel: '鉴别失败事件处理',
                triggerAction: "all",// 是否开启自动查询功能
                store: verity_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'verity_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '授权用户一般操作',
                fieldLabel: '授权用户一般操作',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'client_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '管理员一般操作',
                fieldLabel: '管理员一般操作',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'admin_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '隧道的建立与删除',
                fieldLabel: '隧道的建立与删除',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'build_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '连续同一隧道的建立与删除',
                fieldLabel: '连续同一隧道的建立与删除',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'build_num_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '数据完整性校验失败',
                fieldLabel: '数据完整性校验失败',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'full_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '数据解密失败',
                fieldLabel: '数据解密失败',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'decode_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '数据包丢弃事件',
                fieldLabel: '数据包丢弃事件',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'discard_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '审计日志存储失败',
                fieldLabel: '审计日志存储失败',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'storage_flag',
                allowBlank: false,
                blankText: "请选择"
            }),
            new Ext.form.ComboBox({
                border: true,
                frame: true,
                editable: false,
                emptyText: '重放攻击事件',
                fieldLabel: '重放攻击事件',
                triggerAction: "all",// 是否开启自动查询功能
                store: audit_store,// 定义数据源
                displayField: "name", // 关联某一个逻辑列名作为显示值
                valueField: "id", // 关联某一个逻辑列名作为显示值
                mode: "local",// 如果数据来自本地用local 如果来自远程用remote默认为remote
//                name: 'protocol',
                hiddenName: 'replay_flag',
                allowBlank: false,
                blankText: "请选择"
            })
        ],
        buttons: [{
            id: 'insert_win.info',
            text: '保存配置',
            autoWidth: true,
            handler: function () {
                if (formPanel.form.isValid()) {
                    formPanel.getForm().submit({
                        url: "../../AuditProcessAction_update.action",
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

    var panel = new Ext.Panel({
        plain: true,
        autoWidth: true,
        border: false,
        autoScroll: true,
        items: [{
            xtype: 'fieldset',
            title: '审计处理',
            width: 550,
            items: [formPanel]
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

    // 加载配置数据
    if (formPanel) {
        formPanel.getForm().load({
            url: '../../AuditProcessAction_find.action',
            success: function (form, action) {

            },
            failure: function (form, action) {
                Ext.Msg.alert('错误', '加载数据出错！');
            }
        });
    }
});





