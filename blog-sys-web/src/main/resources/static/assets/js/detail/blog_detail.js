/**
 * 博客详情页面的js脚本,主要功能是:初始化详情页面插件和注册博客详情相关的事件。
 */
var blog_detail = {
    plugin: {
        /*博客分页*/
        blog_pagination: {
            settings: {
                items_per_page: 3,
                num_display_entries: 0,
                num_edge_entries: 0,
                next_text: '下一篇',
                prev_text: '上一篇',
                callback: function (new_page_index, pagination_container) {
                    console.log("博客:当前页码:" + new_page_index);
                    return false;
                }
            },
            init: function (target) {
                target.pagination(236, blog_detail.plugin.blog_pagination.settings);
            }

        },
        /*回复分页*/
        reply_pagination: {
            settings: {
                items_per_page: 3,
                num_display_entries: 4,
                num_edge_entries: 2,
                next_text: '上一页',
                prev_text: '下一页',
                callback: function (new_page_index, pagination_container) {
                    console.log("回复:当前页码:" + new_page_index);
                    return false;
                }
            },
            init: function (target) {
                target.pagination(236, blog_detail.plugin.reply_pagination.settings);
            }
        },
        /*文章侧边栏,百度版*/
        baidu_side_catalog_plugin: {
            init: function () {
                var baikeViewInfo = {
                    "title": "北京烤鸭",
                    "cataList": [
                        {"title": "历史缘由", "level": 1, "index": 1, "content": "啦啦啦啦啦"},
                        {"title": "流派分类", "level": 1, "index": 2, "content": "啦啦啦啦啦"},
                        {"title": "三大美味", "level": 1, "index": 3, "content": "啦啦啦啦啦"},
                        {"title": "全聚德", "level": 2, "index": "3_1", "content": "啦啦啦啦啦"},
                        {"title": "便宜坊", "level": 2, "index": "3_2", "content": "啦啦啦啦啦"},
                        {"title": "大董", "level": 2, "index": "3_3", "content": "啦啦啦啦啦"},
                    ]
                };
                $('#sideToolbar').sideToolbar({width: 220, height: 495, showHeight: 700});
                //初始化页面内容显示
                initContentView(baikeViewInfo);

                $('#sideCatalog').sideCatalog({width: 220, height: 380, content: '#content'});
                $(document).scroll(function () {
                    fixSideToolbarPosition();
                });

                //sideToolbar应为fixed定位，根据页面布局计算fixed定位时的top和left
                function fixSideToolbarPosition() {

                    var windowBottom = $(document).scrollTop() + $(window).height();
                    var contentBottom = $("#content-wrap").offset().top + $("#content-wrap").height();

                    var bottom = (windowBottom <= contentBottom) ? 0 : windowBottom - contentBottom;
                    /* var top = $(window).height() - bottom - $("#sideToolbar").height() - 10;*/

                    $('#sideToolbar').css({bottom: bottom});
                    $('#sideToolbar').css({right: 120});
                }

                //初始化页面内容显示
                function initContentView(baikeViewInfo) {
                    var html = '',
                        headlineLevel,
                        headlineClass;
                    for (var i = 0, l = baikeViewInfo.cataList.length; i < l; i++) {
                        var cata = baikeViewInfo.cataList[i];
                        if (cata.level == 1) {
                            headlineLevel = 'h2',
                                headlineClass = 'headline-1';
                        } else if (cata.level == 2) {
                            headlineLevel = 'h3',
                                headlineClass = 'headline-2';
                        }
                        html += '<' + headlineLevel + ' id="' + cata.index + '" class="' + headlineClass + '">' +
                            '<span class="headline-index">' + cata.index + '</span>' +
                            '<span class="headline-title">' + cata.title + '</span>' +
                            '</' + headlineLevel + '>';
                        html += '<div class="para">' + cata.content + '</div>';
                    }
                    $('#content').html(html);
                }
            }
        },

        /*文章侧边栏，bootstrap4版本*/
        bootstrap4_catalog_plugin: {
            /*初始化文章的大纲目录功能*/
            init:function () {
                var $navItems = $('#article-content-div').find('h1,h2,h3,h4,h5,h6'),//文章大纲数组
                    $outline = $('#outline_div'),
                    $outline_items = null;
                $.each($navItems,function (index,item) {
                    console.log(index,'---',$(item));
                    var $tempItem = '<li><a href="#'+$(item).attr('id')+'">$(item).html()</a></li>'
                })
            },
            event:{
                /*绑定文章滚动激活大纲事件*/
                bind_outline_scroll:function () {
                    //文章大纲鼠标监听事件
                    var $navItems = $('#article-content-div').find('h1,h2,h3,h4,h5,h6'),//所有的文章导航标题
                        $topNav=$('#sectionsNav'),//广告栏
                        $outline = $('#outline_div');//大纲容器
                    var outlineTop = $('#outline_div').offset().top;
                    var topNavHeight = $topNav.height();
                    $(window).scroll(function () {
                        //1.监听每一个标题相对文档顶部的offset，获取当前活跃的锚点active_id
                        var scrollTop = $(document).scrollTop(),
                            active_id = null;
                        $navItems.each(function () {
                            var offsetTop = $(this).offset().top;
                            //锚点滚动体验修正
                            if (scrollTop + 100 > offsetTop) {
                                active_id = $(this).attr('id');
                            }
                        });
                        //2.激活锚点样式
                        if (active_id) {
                            $outline.find('a').removeClass('blog_outline_active');
                            $outline.find("[href='#" + active_id + "']").addClass('blog_outline_active');
                        }
                        //3.对大纲的定位进行设置
                        if (scrollTop +35>= outlineTop) {//大纲导航定位体验修复
                            //开始显示文档大纲组件
                            $outline.css({
                                position: 'fixed',
                                top:(topNavHeight+16)+'px'
                            });
                        } else {
                            $outline.css({
                                position: 'relative',
                                top:'0px'
                            });
                        }
                    });
                    $(window).trigger('scroll');
                }
            }
        },
        /*注册步骤*/
        smartwizard: {
            settings: {
                selected: 0,
                theme: 'default',
                transitionEffect: 'fade',
                showStepURLhash: true,
                toolbarSettings: {
                    toolbarPosition: 'bottom',
                    toolbarButtonPosition: 'right',

                },
                lang: {
                    next: "下一步",
                    previous: "上一步"
                }
            },
            init: function (target) {
                // Smart Wizard
                var btnCancel = $('<button class="btn btn-facebook"></button>').text('重置')
                    .addClass('btn btn-danger')
                    .on('click', function () {
                        target.smartWizard("reset");
                    });
                var btnFinish = $('<button class="btn btn-default"></button>').text('完成')
                    .addClass('btn btn-info')
                    .on('click', function () {
                        $('#signupModal').modal('hide');
                    });
                blog_detail.plugin.smartwizard.settings.toolbarSettings.toolbarExtraButtons = [btnCancel, btnFinish];
                target.smartWizard(blog_detail.plugin.smartwizard.settings);
            },
            event: {
                bind_show_step: function (target) {
                    // Step show event
                    target.on("showStep", function (e, anchorObject, stepNumber, stepDirection, stepPosition) {
                        console.log("anchorObject=", anchorObject, " stepNumber=" + stepNumber + " stepDirection=" + stepDirection + " stepPosition=" + stepPosition);
                    });
                }
            }
        },
    },
    evnent: {
        bind_load_comment_btn: function (target) {
            target.bind('click', function () {
                var comment_load_layer = layer.load(0, {shade: false});
                setTimeout(function () {
                    layer.close(comment_load_layer);
                }, 500);
                //alert('load comment event.');
            });
        }
    },

}