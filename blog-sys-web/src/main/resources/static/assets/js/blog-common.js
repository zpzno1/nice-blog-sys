/**
 * 描述: 博客通用部分js[树形菜单、标签云、回到顶部]
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/11/15
 */
(function ($, window) {
    /**
     * 公共部分默认组件初始化
     */
    function defaul_plugin_init() {
        //回到顶端
        blog_common.plugin.uitotop.init();
    }

    window.blog_common = {
        /**
         * 常用插件
         */
        plugin: {
            /*回到顶部*/
            uitotop: {
                init: function () {
                    $().UItoTop({easingType: 'easeOutQuart'});
                }
            },
            /*标签云*/
            jqxtagcloud: {
                settings: {
                    displayMember: 'tagName',
                    valueMember: 'count',
                    fontSizeUnit: 'px',
                    minFontSize: 10,
                    maxFontSize: 20,
                    minColor: '#B3B5B4',
                    maxColor: '#000000',
                    displayValue: true
                },
                init: function (target) {
                    // var data = [
                    //     {countryName: "Australia", technologyRating: 35},
                    //     {countryName: "United States", technologyRating: 60},
                    //     {countryName: "United States", technologyRating: 60},
                    //     {countryName: "Germany", technologyRating: 55},
                    //     {countryName: "Germany", technologyRating: 200},
                    //     {countryName: "Brasil", technologyRating: 20},
                    //     {countryName: "Brasil", technologyRating: 20},
                    //     {countryName: "United Kingdom", technologyRating: 50},
                    //     {countryName: "United Kingdom", technologyRating: 50},
                    //     {countryName: "Japan", technologyRating: 150}
                    // ];
                    $.ajax({
                        url: 'blogTag/count/query',
                        method: 'get',
                        success: function (res) {
                            debugger;
                            var data = res.data;
                            var source =
                                {
                                    localdata: data,
                                    datatype: "array"
                                };
                            var dataAdapter = new $.jqx.dataAdapter(source, {});
                            var setting = blog_common.plugin.jqxtagcloud.settings
                            setting.source = dataAdapter;
                            target.jqxTagCloud(setting);
                        }
                    });

                }
            },
            /*分页*/
            pagination: {
                init: function (target, opt) {
                    target.pagination(opt.total, opt);
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
                    blog_common.plugin.smartwizard.settings.toolbarSettings.toolbarExtraButtons = [btnCancel, btnFinish];
                    target.smartWizard(blog_common.plugin.smartwizard.settings);
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
            /*树形分类*/
            blog_category_tree: {
                setting: {
                    edit: {
                        enable: true,
                        showRemoveBtn: false,
                        showRenameBtn: false
                    },
                    view: {
                        showLine: false,
                        showIcon: false,
                        selectedMulti: false,
                        dblClickExpand: false,
                        addDiyDom: function (treeId, treeNode) {
                            var spaceWidth = 5;
                            var switchObj = $("#" + treeNode.tId + "_switch"),
                                icoObj = $("#" + treeNode.tId + "_ico");
                            switchObj.remove();
                            icoObj.before(switchObj);
                            if (treeNode.level > 1) {
                                var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
                                switchObj.before(spaceStr);
                            }
                        }
                    },
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: 'id',
                            pIdKey: 'parentId',
                            rootPid: null
                        }
                    },
                    callback: {
                        addDiyDom: function (treeId, treeNode) {
                            var spaceWidth = 5;
                            var switchObj = $("#" + treeNode.tId + "_switch"),
                                icoObj = $("#" + treeNode.tId + "_ico");
                            switchObj.remove();
                            icoObj.before(switchObj);
                            if (treeNode.level > 1) {
                                var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
                                switchObj.before(spaceStr);
                            }
                        },
                        beforeClick: function (treeId, treeNode) {
                            if (treeNode.level == 0) {
                                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                                zTree.expandNode(treeNode);
                                return false;
                            }
                            return true;
                        }
                    }
                },
                init: function (target) {
                    // var zNodes = [
                    //     {id: 1, pId: 0, name: "JavaEE开发"},
                    //     {id: 11, pId: 0, name: "收件箱"},
                    //     {id: 111, pId: 11, name: "收件箱1"},
                    //     {id: 112, pId: 11, name: "收件箱2"},
                    //     {id: 113, pId: 112, name: "收件箱3"},
                    //     {id: 114, pId: 113, name: "收件箱4"},
                    //     {id: 12, pId: 1, name: "垃圾邮件"},
                    //     {id: 13, pId: 1, name: "草稿"},
                    //     {id: 14, pId: 1, name: "已发送邮件"},
                    //     {id: 15, pId: 1, name: "已删除邮件"},
                    //     {id: 3, pId: 0, name: "快速视图"},
                    //     {id: 31, pId: 3, name: "文档"},
                    //     {id: 32, pId: 3, name: "照片"}
                    // ];
                    $.ajax({
                        url: 'blogCategory/tree/query',
                        method: 'get',
                        success: function (res) {
                            var zNodes = res.data;
                            var setting = blog_common.plugin.blog_category_tree.setting;
                            $.fn.zTree.init(target, setting, zNodes);
                            target.hover(function () {
                                if (!target.hasClass("showIcon")) {
                                    target.addClass("showIcon");
                                }
                            }, function () {
                                target.removeClass("showIcon");
                            });
                        }
                    });

                }
            },
            /*将要废除：文章侧边栏，bootstrap4版本*/
            tocbot_catalog_plugin: {
                /*初始化文章的大纲目录功能*/
                init: function () {
                    tocbot.init({
                        // Where to render the table of contents.
                        tocSelector: '.js-toc',
                        // Where to grab the headings to build the table of contents.
                        contentSelector: '.js-toc-content',
                        // Which headings to grab inside of the contentSelector element.
                        headingSelector: 'h1, h2, h3,h4,h5,h6',
                    })
                },
                event: {
                    /*绑定文章滚动激活大纲事件*/
                    bind_outline_scroll: function () {
                        //文章大纲鼠标监听事件
                        var $topNav = $('#sectionsNav'),//广告栏
                            $outline = $('#outline_div');//大纲容器
                        var outlineTop = $('#outline_div').offset().top;
                        var topNavHeight = $topNav.height();
                        $(window).scroll(function () {
                            var scrollTop = $(document).scrollTop();
                            //3.对大纲的定位进行设置
                            if (scrollTop + 35 >= outlineTop) {//大纲导航定位体验修复
                                //开始显示文档大纲组件
                                $outline.css({
                                    position: 'fixed',
                                    top: (topNavHeight + 40) + 'px'
                                });
                            } else {
                                $outline.css({
                                    position: 'relative',
                                    top: '0px'
                                });
                            }
                        });
                        $(window).trigger('scroll');
                    }
                }
            },
        },
        /**
         * 公共默认初始化
         */
        defaul_plugin_init: defaul_plugin_init,
        /**
         * blog所有关于http的请求
         */
        http: {
            initProfile: function () {

            }
        }
    };

})(jQuery, window);