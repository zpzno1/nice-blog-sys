/**
 * 描述: 博客通用部分js[树形菜单、标签云、回到顶部]
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/11/15
 */
(function ($, window) {
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "progressBar": false,
        "preventDuplicates": true,
        "positionClass": "toast-top-center",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "1000",
        "extendedTimeOut": "2000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    //拓展ajax插件
    $.ajaxSetup({
        /*后台服务器异常*/
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            try {
                var res = XMLHttpRequest.responseJSON;
                toastr.error("提示信息：" + res.msg);
            } catch (e) {
                toastr.error("提示信息：" + textStatus + ':' + errorThrown);
            }
        },
        /*后台业务异常处理*/
        dataFilter: function (data, type) {
            var res = new Object();
            if (typeof data == 'string') {
                try {
                    res = JSON.parse(data);
                } catch (e) {
                    return data;
                }
            }
            //系统异常：未登录
            if (res.code == '401') {
                toastr.info('提示信息，当前用户未登录，请进行登陆操作！');
            } else if (res.code == '403') {
                //系统异常：未授权
                toastr.error('你没有访问该资源的权限，请联系管理员(1099501218@qq.com)！');
            } else if (res.code == '4001') {
                return data;
            } else if (res.code != '0') {
                //业务异常：BlogException类型
                toastr.info("提示信息：" + res.msg);
            }
            return data;
        },
        /*所有的请求异步加载*/
        beforeSend: function () {
            this.layerIndex = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
        },
        complete: function () {
            layer.close(this.layerIndex);
            //保险起见
            // layer.closeAll();
        },
    });

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
                    $.ajax({
                        url: '/blogTag/count/query',
                        method: 'get',
                        success: function (res) {
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
                            target.on('itemClick', function (event) {
                                var args = event.args;
                                blog_common.http.showBlogPage({tagName: args.label});
                            });
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
                            // 如果当前节点为叶子节点，那么就要加载分类博客
                            if (!treeNode.isParent) {
                                blog_common.http.showBlogPage({categoryId: treeNode.id})
                            }
                            return true;
                        }
                    }
                },
                init: function (target) {
                    $.ajax({
                        url: '/blogCategory/tree/query',
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

            },
            showBlogPage: function (param) {
                $.ajax({
                    url: '/blog/query',
                    method: 'get',
                    data: param,
                    success: function (res) {
                        var opt = {
                            total: res.data.total,
                            items_per_page: 8,
                            num_display_entries: 3,
                            num_edge_entries: 2,
                            next_text: '下一页',
                            prev_text: '前一页',
                            /*分页回调*/
                            callback: function (new_page_index, pagination_container) {
                                $('#blogContainer').html('');
                                if (new_page_index == 0) {
                                    _render_blog_index_page(res);
                                } else {
                                    var requestData = $.extend(param, {size: 8, current: new_page_index + 1});
                                    $.ajax({
                                        url: '/blog/query',
                                        data: requestData,
                                        method: 'get',
                                        success: function (res) {
                                            _render_blog_index_page(res);
                                        }
                                    });
                                }
                                return false;
                            }
                        };
                        blog_common.plugin.pagination.init($("#blog_pagination_div"), opt);
                    }
                });
            }
        }
    };

    /**
     * 公共部分默认组件初始化
     */
    function defaul_plugin_init() {
        //回到顶端
        blog_common.plugin.uitotop.init();
    }

    /**
     * 获取动态标签
     * @param tags
     * @returns {string} 返回标签html
     * @private
     */
    function _get_tags_dom(tags) {
        if (tags.length == 0) {
            return "";
        }
        var resultHtml = '<label>标签：</label>';
        $.each(tags, function (index, item) {
            var tagBtnHtml = '<button type="button" class="btn btn-default btn-sm btn-round"\n' +
                '                            style="padding: 0 10px 0 10px;">\n' +
                '                                <i class="fa fa-tag"></i>' + item.name + '\n' +
                '                                </button>';
            resultHtml += tagBtnHtml;
        });
        return resultHtml;
    }

    /**
     * 获取置顶图标dom
     * @param isTop 返回置顶html
     * @private
     */
    function _get_top_dom(isTop) {
        if (isTop == "1") {
            var topHtml = '<span class="badge badge-pill badge-danger pull-right">TOP<span\n' +
                '                                        class="fas fa-award fa-lg"></span></span>\n' +
                '                                </div>';
            return topHtml;
        } else {
            return "";
        }
    }

    /**
     * 将博客数据渲染到博客首页页面
     * @param res 记录
     * @private
     */
    function _render_blog_index_page(res) {
        var $blogListContainer = $('#blogContainer');
        if (res.code == '0') {
            if (res.data.records.length > 0) {
                $.each(res.data.records, function (index, item) {
                    $blogListContainer.append(_get_blog_item(item));
                });
            } else {
                $blogListContainer.append('<div class="text-center"><h3>已经没有再多数据了，去别处看看吧,<a href="/">返回首页</a></h3></div>');
            }
        } else {
            blog_util.failMessage(res);
        }
    }

    /**
     * 获取每一条博客的具体内容html
     * @param item 博客项数据
     * @returns {string} 返回整条博客html文本
     * @private
     */
    function _get_blog_item(item) {
        var blogListItem = '' +
            ' <div class="card card-nav-tabs">\n' +
            '                    <div class="card-header" style="font-size: 20px;">\n' +
            '                        <div class="row">\n' +
            '                            <div class="col-md-11">\n' +
            '                                <span class="badge badge-pill badge-dark">' + item.categoryName + '</span>\n' +
            '                                <a href="/blog/detail/' + item.id + '"\n' +
            '                                   class="text-dark">' + item.title + '</a>\n' +
            '                            </div>\n' +
            '                            <div class="col-md-1">\n' +
            '                            ' + _get_top_dom(item.top) +
            '                            </div>\n' +
            '                        </div>\n' +
            '\n' +
            '                    </div>\n' +
            '\n' +
            '                    <div class="card-body animated fadeInDown">\n' +
            '                        <div class="card-text">\n' +
            '                            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n' +
            '                                    class="fa fa-calendar"></i></label>&nbsp;&nbsp;<span>' + item.updateTime + '</span>\n' +
            '                            <label>\n' +
            '                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n' +
            '                                        class="fas fa-user"></i></label>&nbsp;&nbsp;<span>' + item.nickName + '</span>\n' +
            '                            </label>\n' +
            '                            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n' +
            '                                    class="fas fa-thumbs-up"></i></label>&nbsp;&nbsp;<span>' + item.starCount + '</span>\n' +
            '                            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i\n' +
            '                                    class="fa fa-eye"></i></label>&nbsp;&nbsp;<span>' + item.viewCount + '</span>\n' +
            '                            </label>\n' +
            '                        </div>\n' +
            '                        <div class="card-text">\n' +
            '                            <div class="row">\n' +
            '                                <div class="col-md-9">\n' +
            '                                    <div>\n' +
            '                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + item.introduction + '\n' +
            '                                    </div>\n' +
            '                                    <div class="row">\n' +
            '                                        <div class="col-md-10 pull-left">\n' +
            '                                            ' + _get_tags_dom(item.blogTagList) + '\n' +
            '                                        </div>\n' +
            '\n' +
            '                                        <div class="col-md-2 pull-right">\n' +
            '                                            <a href="/blog/detail/' + item.id + '"\n' +
            '                                               class="btn btn-primary btn-round"\n' +
            '                                               style="padding: 3px 15px 3px 15px">阅读全文</a>\n' +
            '                                        </div>\n' +
            '                                    </div>\n' +
            '                                </div>\n' +
            '                                <div class="col-md-3">\n' +
            '                                    <img src="' + item.iconUrl + '"\n' +
            '                                         style="width: 80%;height: 100%;">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </div>';
        //图片光标移动放大体验
        var $blogListItem = $(blogListItem);
        var $blogHeader = $blogListItem.find('img');
        $blogHeader.bind('mouseenter', function () {
            $(this).addClass("animated tada");
        });
        $blogHeader.bind('mouseout', function () {
            $(this).removeClass("animated tada");
        });
        return $blogListItem;
    }


})(jQuery, window);