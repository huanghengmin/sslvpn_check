Ext.onReady(function () {

    Ext.BLANK_IMAGE_URL = '../../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var panel = new Ext.Panel({
        border:true,
        autoScroll: true,
        autoLoad:{
            url: '../../DigestAction_find.action',
            discardUrl: false,
            nocache: true,
            timeout: 30,
            scripts: true
        }
    });

    new Ext.Viewport({
        layout :'fit',
        renderTo:Ext.getBody(),
        autoScroll:true,
        items:[
            panel
        ]
    });

});





