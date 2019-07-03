/**
 * 描述: 博客通用部分js[树形菜单、标签云、回到顶部]
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019年3月16日
 */
(function ($, window) {



    window.blog_util = {
        /**
         * 全局异常提示
         * @param res 相应体
         */
        failMessage: function (res) {
            //未登录
            if (res.code == '401') {
                toastr.error('提示信息，当前用户未登录，请进行登陆操作！');
                return;
                // swal("提示信息，当前用户未登录，请进行登陆操作");
            }
            //未授权
            if (res.code == '403') {
                toastr.error('你没有访问该资源的权限，请联系管理员(1099501218@qq.com)！');
                return;
                // swal("你没有访问该资源的权限，请联系管理员(1099501218@qq.com)！");
            }
            //通常服务调用失败错误提示
            toastr.error("提示信息：" + res.msg);
        },
        /**
         * 检测滚动条是否滚动到页面底部
         * @returns {boolean}
         */
        isScrollToPageBottom: function () {
            //文档高度
            var documentHeight = document.documentElement.offsetHeight;
            var viewPortHeight = getViewportSize().h;
            var scrollHeight = window.pageYOffset ||
                document.documentElement.scrollTop ||
                document.body.scrollTop || 0;
            return documentHeight - viewPortHeight - scrollHeight <= 20;
        },
        /**
         *
         * 例如：{ url: qqloginUrl, name: '三方登录窗口授权', iWidth: 800, iHeight: 400 }
         * @param opt
         * @returns {Window}
         */
        openwindow:function (opt) {
            // url, name, iWidth, iHeight
            var url = opt.url;                            //转向网页的地址;
            var name = opt.name;                           //网页名称，可为空;
            var iWidth = opt.iWidth;                         //弹出窗口的宽度;
            var iHeight = opt.iHeight;                        //弹出窗口的高度;
            //window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
            var iTop = (window.screen.height - 30 - iHeight) / 2;       //获得窗口的垂直位置;
            var iLeft = (window.screen.width - 10 - iWidth) / 2;        //获得窗口的水平位置;
            return window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');

        }
    }
})(jQuery, window);

function getViewportSize(w) {
    //使用指定的窗口， 如果不带参数则使用当前窗口
    w = w || window;

    //除了IE8及更早的版本以外，其他浏览器都能用
    if (w.innerWidth != null)
        return {w: w.innerWidth, h: w.innerHeight};

    //对标准模式下的IE（或任意浏览器）
    var d = w.document;
    if (document.compatMode == "CSS1Compat")
        return {w: d.documentElement.clientWidth, h: d.documentElement.clientHeight};

    //对怪异模式下的浏览器
    return {w: d.body.clientWidth, h: d.body.clientHeight};
}