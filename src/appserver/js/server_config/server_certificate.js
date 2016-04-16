Ext.onReady(function () {

    Ext.BLANK_IMAGE_URL = '../../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var server_certificate_start =0;
    var server_certificate_pageSize = 5;
    var server_certificate_toolbar = new Ext.Toolbar({
        plain:true,
        width:350,
        height:30,
        items:[
            {
                id:'add_server.info',
                xtype:'button',
                text:'服务器证书管理'
            }
        ]
    });
    var server_certificate_record = new Ext.data.Record.create([
        {name:'id',			mapping:'id'},
        {name:'file',			mapping:'file'},
        {name:'name',			mapping:'name'} ,
        {name:'not_before',			mapping:'not_before'} ,
        {name:'not_after',			mapping:'not_after'},
        {name:'cn',			mapping:'cn'}
    ]);
    var server_certificate_proxy = new Ext.data.HttpProxy({
        url:"../../CertificateAction_findPkcsConfig.action"
    });
    var server_certificate_reader = new Ext.data.JsonReader({
        totalProperty:"total",
        root:"rows"/*,
         id:'id'*/
    },server_certificate_record);
    var server_certificate_store = new Ext.data.GroupingStore({
        id:"pkcs.store.info",
        proxy : server_certificate_proxy,
        reader : server_certificate_reader
    });
//    var server_certificate_boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var server_certificate_rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var server_certificate_colM = new Ext.grid.ColumnModel([
//        server_certificate_boxM,
        server_certificate_rowNumber,
//        {header:"证书文件",			dataIndex:"pkcs_name",  align:'center',sortable:true,menuDisabled:true},
        {header:"证书通用名",			dataIndex:"cn",  align:'center',sortable:true,menuDisabled:true},
        {header:"有效起始日期",			dataIndex:"not_before",  align:'center',sortable:true,menuDisabled:true},
        {header:"有效截止日期",			dataIndex:"not_after",  align:'center',sortable:true,menuDisabled:true},
        {header:"证书内容",		dataIndex:"context",      align:'center',sortable:true,menuDisabled:true,renderer:show_server_certificate},
        {header:'动作',		dataIndex:"flag",	  align:'center',sortable:true,menuDisabled:true, renderer:show_server_certificate_flag,	width:100}
    ]);
    var server_certificate_page_toolbar = new Ext.PagingToolbar({
        pageSize : server_certificate_pageSize,
        store:server_certificate_store,
        displayInfo:true,
        displayMsg:"显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"当前页",
        afterPageText:"共{0}页"
    });
    var server_certificate_grid_panel = new Ext.grid.GridPanel({
        id:'pkcs.grid.info',
        //title:'服务器证书',
        plain:true,
        //height:Ext.getBody().getHeight()/2,
        autoHeight:true,
//        height:setHeight(),
        viewConfig:{
            forceFit:true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
        },
        bodyStyle:'width:100%',
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:true,
        cm:server_certificate_colM,
//        sm:server_certificate_boxM,
        store:server_certificate_store,
        /*tbar : server_certificate_toolbar,*//*
         listeners:{
         render:function(){
         tbar.render(this.tbar);
         }
         },*/
        bbar:server_certificate_page_toolbar
    });

    var panel = new Ext.Panel({
        plain:true,
        autoWidth:true,
        autoHeight:true,
        border:false,
        items:[
            {
                id:'certificate.info',
                xtype:'fieldset',
                //title:'证书管理',
                items:[
                    {
                        id:'pkcs.info',
                        xtype:'fieldset',
                        title:'设备证书管理',
                        items:[server_certificate_grid_panel]
                    }]
            }
        ]
    });
    new Ext.Viewport({
        layout:'fit',
        renderTo:Ext.getBody(),
        autoScroll:true,
        items:[
            {
                frame:true,
                autoScroll:true,
                items:[panel]
            }
        ]
    });


    server_certificate_store.load({
        params:{
            start:server_certificate_start,limit:server_certificate_pageSize
        }
    });


    Model.add_pfx_server =   function add_pfx_server(){
        var upload_pfx_form = new Ext.form.FormPanel({
            frame:true,
            labelWidth:150,
            labelAlign:'right',
            fileUpload:true,
            border:false,
            defaults : {
                width : 200,
                allowBlank : false,
                blankText : '该项不能为空！'
            },
            items:[{
                id:'serverPfxFile',
                fieldLabel:"(注:格式为PKCS)",
                width:200,
                name : 'serverPfxFile',
                xtype:'textfield',
                inputType:'file',
                allowBlank:false,
                regexText:'(注:格式为PKCS)',
                listeners:{
                    render:function () {
                        Ext.get('serverPfxFile').on("change", function () {
                            var file = this.getValue();
                            var fs = file.split('.');
                            if (fs[fs.length - 1].toLowerCase() != 'p12' && fs[fs.length - 1].toLowerCase() != 'pfx') {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:200,
                                    msg:'上传文件格式不对,请重新选择!',
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false,
                                    fn:function (e) {
                                        if (e == 'ok') {
                                            Ext.getCmp('serverPfxFile').setValue('');
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            },{
                fieldLabel:'证书密码',
                name:'pwd',
                xtype:'textfield',
                width:200,
                inputType:'password',
                regex:/^\S{4,20}$/
            }]
        });
        var win = new Ext.Window({
            title:'更新网关证书',
            width:500,
            height:200,
            layout:'fit',
            modal:true,
            items:[upload_pfx_form],
            bbar:['->',{
                id:'addpfx_win.info',
                text:'更新',
                width:50,
                handler:function(){
                    if (upload_pfx_form.form.isValid()) {
                        upload_pfx_form.getForm().submit({
                            url :'../../CertificateAction_update_server_cert.action',
                            timeout: 20*60*1000,
                            method :'POST',
                            waitTitle :'系统提示',
                            waitMsg :'正在连接...',
                            success : function() {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'更新成功,点击返回页面!',
                                    buttons:Ext.MessageBox.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false,
                                    fn:function(e){
                                        server_certificate_store.reload();
                                        win.close();
                                    }
                                });
                            },
                            failure : function() {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'更新失败，请与管理员联系!',
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
                    upload_pfx_form.getForm().reset();
                }
            }]
        }).show();
    }
});



function setHeight(){
    var h = document.body.clientHeight-8;
    return h;
}

function setWidth(){
    return document.body.clientWidth-8;
}

function show_server_certificate(){
    return String.format(
        '<a id="show_server.info" href="javascript:void(0);" onclick="show_server_certificate_fun();return false;" style="color: green;">查看</a>&nbsp;&nbsp;&nbsp;'
    );
}

function show_server_certificate_flag(){
    return String.format(
        '<a id="update_server_flag_fun.info" href="javascript:void(0);" onclick="update_server_certificate_flag_fun();return false;" style="color: green;">更新</a>&nbsp;&nbsp;&nbsp;'
    );
}

function  show_server_certificate_fun(){
    var grid = Ext.getCmp('pkcs.grid.info');
    var recode = grid.getSelectionModel().getSelected();
    var server_file  = recode.get("pkcs_file");

    var record = new Ext.data.Record.create([
        {name:'name', mapping:'name'},
        {name:'content', mapping:'content'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        url:"../../CertificateAction_findServerCrt.action?server_file="+server_file
    });

    var reader = new Ext.data.JsonReader({
        totalProperty:"total",
        root:"rows"
    }, record);

    var store = new Ext.data.GroupingStore({
        id:"store.info",
        proxy:proxy,
        reader:reader
    });
    store.load();


//    var boxM = new Ext.grid.CheckboxSelectionModel({singleSelect:true});   //复选框单选

    var rowNumber = new Ext.grid.RowNumberer();         //自动编号

    var colM = new Ext.grid.ColumnModel([
//        boxM,
        rowNumber,
        {header:"证书项", dataIndex:"name", align:'center',width:50, sortable:true, menuDisabled:true} ,
        {header:"详细信息",dataIndex:'content',align:'center', sortable:true, menuDisabled:true}
    ]);

    var page_toolbar = new Ext.PagingToolbar({
        store:store,
        displayInfo:true,
        displayMsg:"显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"当前页",
        afterPageText:"共{0}页"
    });
    var list_grid_panel = new Ext.grid.GridPanel({
        id:'grid.downloadList.info',
        plain:true,
        height:250,
        viewConfig:{
            forceFit:true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
        },
        bodyStyle:'width:100%',
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:true,
        cm:colM,
//        sm:boxM,
        store:store,
        //tbar : tb,
        bbar:page_toolbar
    });
    var win = new Ext.Window({
        title:"服务器证书信息",
        width:500,
        layout:'fit',
        height:250,
        modal:true,
        items:list_grid_panel
    }).show();
}

var Model = new Object;
function update_server_certificate_flag_fun() {Model.add_pfx_server();}

