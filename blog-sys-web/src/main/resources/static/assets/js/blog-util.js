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
                swal("提示信息，当前用户未登录，请进行登陆操作");
            }
            //未授权
            if (res.code == '403') {
                swal("你没有访问该资源的权限，请联系管理员(1099501218@qq.com)！");
            }
            //通常服务调用失败错误提示
            swal("提示信息：" + res.msg);
        },
        /**
         * 检测滚动条是否滚动到页面底部
         * @returns {boolean}
         */
        isScrollToPageBottom:function () {
            //文档高度
            var documentHeight = document.documentElement.offsetHeight;
            var viewPortHeight = getViewportSize().h;
            var scrollHeight = window.pageYOffset ||
                document.documentElement.scrollTop ||
                document.body.scrollTop || 0;

            return documentHeight - viewPortHeight - scrollHeight < 20;
        }
    }
})(jQuery, window);

function getViewportSize(w){
    //使用指定的窗口， 如果不带参数则使用当前窗口
    w = w || window;

    //除了IE8及更早的版本以外，其他浏览器都能用
    if(w.innerWidth != null)
        return {w: w.innerWidth, h: w.innerHeight};

    //对标准模式下的IE（或任意浏览器）
    var d = w.document;
    if(document.compatMode == "CSS1Compat")
        return {w: d.documentElement.clientWidth, h: d.documentElement.clientHeight};

    //对怪异模式下的浏览器
    return {w: d.body.clientWidth, h: d.body.clientHeight};
}