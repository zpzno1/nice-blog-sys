/**
 * 首页模块代码,主要功能:初始化首页须要的插件。
 */
(function () {
    console.log('index js');
    //底部分页
    blog_common.defaul_plugin_init();

    blog_common.plugin.pagination.init($("#blog_pagination_div"));

})();