/**
 * 首页模块代码,主要功能:初始化首页须要的插件。
 */
var blog_index = {
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
                displayMember: 'countryName',
                valueMember: 'technologyRating',
                fontSizeUnit: 'px',
                minFontSize: 10,
                maxFontSize: 20,
                minColor: '#B3B5B4',
                maxColor: '#000000',
                displayValue: true
            },
            init: function (target) {
                var data = [
                    {countryName: "Australia", technologyRating: 35},
                    {countryName: "United States", technologyRating: 60},
                    {countryName: "United States", technologyRating: 60},
                    {countryName: "Germany", technologyRating: 55},
                    {countryName: "Germany", technologyRating: 200},
                    {countryName: "Brasil", technologyRating: 20},
                    {countryName: "Brasil", technologyRating: 20},
                    {countryName: "United Kingdom", technologyRating: 50},
                    {countryName: "United Kingdom", technologyRating: 50},
                    {countryName: "Japan", technologyRating: 150}
                ];
                var source =
                    {
                        localdata: data,
                        datatype: "array",
                        datafields: [
                            {name: 'countryName'},
                            {name: 'technologyRating'}
                        ]
                    };
                var dataAdapter = new $.jqx.dataAdapter(source, {});
                blog_index.plugin.jqxtagcloud.settings.source = dataAdapter;
                target.jqxTagCloud(blog_index.plugin.jqxtagcloud.settings);
            }
        },
        /*分页*/
        pagination: {
            settings: {
                items_per_page: 5,
                num_display_entries: 3,
                num_edge_entries: 2,
                next_text: '下一页',
                prev_text: '前一页',
                callback: function (new_page_index, pagination_container) {
                    console.log("当前页码:" + new_page_index)
                    return false;
                }
            },
            init: function (target) {
                target.pagination(236, blog_index.plugin.pagination.settings);
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
                blog_index.plugin.smartwizard.settings.toolbarSettings.toolbarExtraButtons = [btnCancel, btnFinish];
                target.smartWizard(blog_index.plugin.smartwizard.settings);
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
                    addDiyDom: function(treeId, treeNode) {
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
                        enable: true
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
                var zNodes = [
                    {id: 1, pId: 0, name: "JavaEE开发"},
                    {id: 11, pId: 0, name: "收件箱"},
                    {id: 111, pId: 11, name: "收件箱1"},
                    {id: 112, pId: 11, name: "收件箱2"},
                    {id: 113, pId: 112, name: "收件箱3"},
                    {id: 114, pId: 113, name: "收件箱4"},
                    {id: 12, pId: 1, name: "垃圾邮件"},
                    {id: 13, pId: 1, name: "草稿"},
                    {id: 14, pId: 1, name: "已发送邮件"},
                    {id: 15, pId: 1, name: "已删除邮件"},
                    {id: 3, pId: 0, name: "快速视图"},
                    {id: 31, pId: 3, name: "文档"},
                    {id: 32, pId: 3, name: "照片"}
                ];
                $.fn.zTree.init(target, this.setting, zNodes);
                target.hover(function () {
                    if (!target.hasClass("showIcon")) {
                        target.addClass("showIcon");
                    }
                }, function () {
                    target.removeClass("showIcon");
                });
            }
        }
    },

}