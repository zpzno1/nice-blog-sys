/**
 * 归档模块代码,主要功能:初始化首页须要的插件。
 */
(function ($) {
    console.log('archive js');
    blog_common.defaul_plugin_init();
    /**
     * 加载更多历史数据数据
     * @param target
     */
    function bind_load_more(target) {
        target.bind('click', function () {
            var comment_load_layer = layer.load(0, {shade: false});
            setTimeout(function () {
                layer.close(comment_load_layer);
            }, 500);
        });
    }
    bind_load_more($('#load_more_archive_btn'));
})(jQuery);
