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
})(jQuery);

