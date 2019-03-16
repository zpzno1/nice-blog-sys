/**
 * 描述: 博客通用部分js[树形菜单、标签云、回到顶部]
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019年3月16日
 */
(function ($, window) {
    window.blog_util = {
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
        }
    }
})(jQuery, window);