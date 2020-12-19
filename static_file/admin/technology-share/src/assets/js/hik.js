/**
 * video 初始化及配置
 * @param {*video挂载的dom id} id
 * @param {*连接成功的回调} cbConnectSuccess
 * @param {*连接报错的回调} cbConnectError
 * @param {*连接关闭的回调} cbConnectClose
 */
function WebControlInit(id, cbConnectSuccess, cbConnectError, cbConnectClose) {
    var webControl = new WebControl({
        szPluginContainer: id,
        iServicePortStart: 14550, // 对应 LocalServiceConfig.xml 中的ServicePortStart值
        iServicePortEnd: 14559, // 对应 LocalServiceConfig.xml 中的ServicePortEnd值
        cbConnectSuccess,
        cbConnectError,
        cbConnectClose,
    });
    console.log(webControl);
    return webControl;
}

function cbConnectError() {
    console.log('cbConnectError');
    oWebControl = null;
    console.error('确认本地进程是否已安装并开启成功！');
}
function cbConnectClose(bNormalClose) {
    // 连接异常断开：bNormalClose = false
    // JS_Disconnect正常断开：bNormalClose = true
    console.log('cbConnectClose');
    oWebControl = null;
}
function cbConnectSuccess(oWebControl) {
    if (!oWebControl) {
        console.error("oWebControl is not null");
        return;
    }
    // 设置窗口控制回调
    oWebControl.JS_SetWindowControlCallback({
        cbIntegrationCallBack: cbIntegrationCallBack,
    });
    //创建视频窗口
    oWebControl
        .JS_StartService('window', {
            dllPath: './chain/cloudTransform.dll',
        })
        .then(function () {
            oWebControl.JS_CreateWnd('playWnd', 900, 500).then(function () {
                console.log('JS_CreateWnd success');
            });
        });
}

/**
 * 离开页面时调用 断开链接的方法
 * @param {*视频插件实例} oWebControl
 */
function WebControlDistory(oWebControl) {
    if (!oWebControl) {
        console.error("oWebControl is not null");
        return;
    }
    oWebControl.JS_DestroyWnd().then(
        function () {
            console.log('JS_DestroyWnd');
        },
        function () { }
    );
    oWebControl.JS_StopService('window').then(function () {
        oWebControl.JS_Disconnect().then(
            function () {
                console.log('JS_Disconnect');
            },
            function () { }
        );
    });
}
export default {
    WebControlInit,
    cbConnectError,
    cbConnectClose,
    cbConnectSuccess,
    WebControlDistory
}