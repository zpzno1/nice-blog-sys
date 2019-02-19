/**
 * 博客后台管理框架js
 */
(function ($, window) {

    /**
     * 博客滚动条处理
     */
    function slimscroll() {
        $('#wrapper').slimscroll({
            height: 'auto'
        });
    }

    /**
     *  自动生成菜单函数
     * @param ctx 应用上下文
     */
    function generate_menue(ctx) {
        var menue_url = ctx + 'assets_admin/server/menue_conf.json';
        var inspinia_url = ctx + "assets_admin/js/inspinia.js";
        $.ajax({
            type: 'get',
            url: menue_url,
            dataType: 'json',
            success: function (res) {
                var menueHtml = auto_generate_menue(ctx, res);
                $('#side-menu').html(menueHtml);
                //动态加载inspinia框架js，并且设置默认选中菜单
                $.getScript(inspinia_url, function () {
                    var default_url = $('.nav-item,.active').attr('data-url');
                    $('#danamic-content').load(default_url);
                    //2)绑定菜单点击事件
                    $('.nav-item').bind('click', function () {
                        var url = $(this).attr('data-url');
                        $('#danamic-content').load(url);
                        //高亮处理
                        $(this).addClass('active');
                        $('.nav-item').removeClass('active');
                    });
                });
            }
        });
    }

    /**
     * 生成菜单
     * @param res 返回属性数据
     */
    function auto_generate_menue(ctx, res) {
        var resultDom = '<li class="nav-item" data-url="home.html"><a href="javascript:;"><i class="fa fa-th-large"></i> <span class="nav-label">首页</span></a></li> ' +
            '<li ><a href="javascript:;"><i class="fa fa-edit"></i> <span class="nav-label">系统功能管理</span><span class="fa arrow"></span></a> ' +
            '   <ul class="nav nav-second-level collapse"> ' +
            '       <li class="nav-item" data-url="system/user/index.html"><a href="javascript:;">网站用户</a></li> ' +
            '       <li class="nav-item" data-url="system/param/index.html"><a href="javascript:;">系统参数</a></li> ' +
            '       <li class="nav-item" data-url="system/log/index.html"><a href="javascript:;">访问日志</a></li> ' +
            '       <li class="nav-item" data-url="system/druid/index.html"><a href="javascript:;">Druid日志</a></li>' +
            '       <li class="nav-item" data-url="system/swagger/index.html"><a href="javascript:;">Swagger文档</a></li>' +
            '   </ul>' +
            '</li> ' +
            '<li><a href="javascript:;"><i class="fa fa-desktop"></i> <span class="nav-label">博客功能管理</span><span class="fa arrow"></span></a>' +
            '  <ul class="nav nav-second-level collapse">' +
            '       <li class="nav-item" data-url="blog/article/index.html"><a href="javascript:;">博文管理</a></li>' +
            '       <li class="nav-item" data-url="blog/tag/index.html"><a href="javascript:;">博客标签</a></li> ' +
            '       <li class="nav-item" data-url="blog/category/index.html"><a href="javascript:;">博客分类</a></li> ' +
            '       <li class="nav-item" data-url="blog/interaction/index.html"><a href="javascript:;"> 评论留言</a></li>' +
            ' </ul>' +
            '</li>';
        return resultDom;
    }


    /**
     *  后台管理系统默认初始化调用
     * @param menue_url 后端菜单请求地址
     */
    function default_init(ctx) {
        //1)系统默认滚动条
        slimscroll();
        //2)初始化系统菜单
        generate_menue(ctx);
    }

    window.blog_admin = {
        default_init: default_init
    };

})(jQuery, window);
