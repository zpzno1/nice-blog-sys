/**
 * 博客详情页面的js脚本,主要功能是:初始化详情页面插件和注册博客详情相关的事件。
 */
(function ($, window) {
    console.log('detail js');
    // 默认事件(回到顶部)
    blog_common.defaul_plugin_init();

    // 加载更多评论
    bind_load_comment($('#load_comment_btn'));

    // 博客评论按钮
    // debugger;

})(jQuery);

function bind_load_comment(target) {
    target.bind('click', function () {
        var comment_load_layer = layer.load(0, {shade: false});
        setTimeout(function () {
            layer.close(comment_load_layer);
        }, 500);
        //alert('load comment event.');
    });
}
