/**
 * 博客详情页面的js脚本,主要功能是:初始化详情页面插件和注册博客详情相关的事件。
 */
(function ($, window) {
    console.log('detail js');
    //文章大纲
    blog_common.plugin.bootstrap4_catalog_plugin.init();
    blog_common.plugin.bootstrap4_catalog_plugin.event.bind_outline_scroll();
    //评论分页
    blog_common.plugin.pagination.init($('#blog_detial_pagination_div'));

    function bind_load_comment(target) {
        target.bind('click', function () {
            var comment_load_layer = layer.load(0, {shade: false});
            setTimeout(function () {
                layer.close(comment_load_layer);
            }, 500);
            //alert('load comment event.');
        });
    }

    bind_load_comment($('#load_comment_btn'));
})(jQuery);

