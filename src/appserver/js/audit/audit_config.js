Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var record = new Ext.data.Record.create([
        {name:'audit_flag', mapping:'audit_flag'} ,
        {name:'audit_mode', mapping:'audit_mode'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        url:"../../SystemAlertAction_loadConfig.action"
    });

    var reader = new Ext.data.JsonReader({
        totalProperty:"totalCount",
        root:"root"
    }, record);

    var store = new Ext.data.GroupingStore({
        id:"store.info",
        proxy:proxy,
        reader:reader
    });

    store.load();
    store.on('load',function(){
        var audit_flag = store.getAt(0).get('audit_flag');
        var audit_mode = store.getAt(0).get('audit_mode');
        Ext.getCmp('audit.audit_flag').setValue(audit_flag);
        Ext.getCmp('audit.audit_mode').setValue(audit_mode);
    });


    var auditData = [[0,'同步审计'],[1,'异步审计']];
    var auditStore = new Ext.data.SimpleStore({fields:['value','key'],data:activeData});

    var formPanel = new Ext.form.FormPanel({
        plain:true,
        width:500,
        labelAlign:'right',
        labelWidth:180,
        defaultType:'textfield',
        defaults:{
            width:250,
            allowBlank:false,
            blankText:'该项不能为空!'
        },
        items:[
            {
                xtype:"checkbox",
                fieldLabel:"启用审计功能",
                id:"audit.audit_flag",
                name:"audit_flag"
            },
            {
                id:'audit.audit_mode',
                fieldLabel:'审计类型',
                hiddenName:'audit_mode',
                xtype:'combo',
                mode:'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"key",valueField:"value",
                store:auditStore
            }
        ],
        buttons:[
            '->',
            {
                id:'insert_win.info',
                text:'保存配置',
                handler:function () {
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url:"../../SystemAlertAction_saveConfig.action",
                            method:'POST',
                            waitTitle:'系统提示',
                            waitMsg:'正在连接...',
                            success:function () {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'保存成功,点击返回页面!',
                                    buttons:Ext.MessageBox.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false
                                });
                            },
                            failure:function () {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'保存失败，请与管理员联系!',
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
            }
        ]
    });

    var panel = new Ext.Panel({
        plain:true,
        width:600,
        border:false,
        items:[{
            id:'panel.info',
            xtype:'fieldset',
            title:'审计配置',
            width:530,
            items:[formPanel]
        }]
    });
    new Ext.Viewport({
        layout :'fit',
        renderTo:Ext.getBody(),
        autoScroll:true,
        items:[{
            frame:true,
            autoScroll:true,
            items:[panel]
        }]
    });

});


