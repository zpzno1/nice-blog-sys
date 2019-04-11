/**
 * 归档模块代码,主要功能:初始化首页须要的插件。
 */
(function ($) {
    //默认插件初始化
    blog_common.defaul_plugin_init();
    //加载更多归档博客事件
    $('#load_more_archive_btn').bind('click', function () {
        var current = sessionStorage.getItem("archive_page_current");
        var next = parseInt(current) + 1;
        //清空旧的归档博客信息
        loadArchiveContent({current: next});
    });
    //归档查询条件切换事件
    $('input[name=queryTypeRadioOptions]').bind('click', function () {
        sessionStorage.removeItem("archive_page_current");
        $('#archiveContainer').html('');
        loadArchiveContent({current: 1});
    });

    //归档博客默认加载事件
    loadArchiveContent();


    /**
     * 动态加载博客归档内容
     * @param pattern
     */
    function loadArchiveContent(opt) {
        //获取选中单选按钮，然后动态加载归档博客
        var patternValue = $('input[name=queryTypeRadioOptions]:checked').val();
        // var targetPattern = patternValue == 'yyyy' ? 'yyyy"年"' : patternValue == 'yyyyQ' ? 'yyyy"年"Q"季"' : 'yyyy"年"MM"月"';
        var defaultData = {pattern: patternValue, size: 8, current: "1"};
        var requestData = $.extend(defaultData, opt);
        $.ajax({
            url: '/blog/archive/query',
            data: requestData,
            method: 'get',
            success: function (res) {
                if (res.code == '0') {
                    sessionStorage.setItem("archive_page_current", requestData.current);
                    var records = res.data.records;
                    if (records.length > 0) {
                        var archiveHtml = '';
                        $.each(records, function (index, timelineRow) {
                            if (timelineRow.isTop == 'true') {
                                archiveHtml += _get_row_header_dom(timelineRow);
                            }
                            archiveHtml += _get_row_blog_dom(timelineRow);
                        });
                        $('#archiveContainer').append(archiveHtml);
                    } else {
                        layer.msg('沒有更多的数据了。');
                    }
                } else {
                    layer.msg(res.msg);
                }
            }
        });
    }


    /**
     * 获取归档时间节点
     * @param row 记录
     * @private
     */
    function _get_row_header_dom(timelineRow) {
        var resultHtml = '                        <div class="timeline-row timeline-row-major">\n' +
            '                            <span class="node"><i class="fa fa-calendar"></i></span>\n' +
            '                            <a href="javascript:;"><h1 class="title">' + timelineRow.archiveTime + '</h1></a>\n' +
            '                        </div>\n';
        return resultHtml;
    }

    /**
     * 获取归档节点下面的博客内容
     * @param blog 博客信息
     * @private
     */
    function _get_row_blog_dom(blog) {
        var resultHtml = '<div class="timeline-row animated fadeInDown">\n' +
            '                                <span class="node"></span>\n' +
            '                                <div class="content">\n' +
            '                                    <h1 itemprop="name">\n' +
            '                                        <span class="badge badge-pill badge-dark">' + blog.categoryName + '</span><a class="timeline-article-title" href="/blog/detail/' + blog.id + '"\n' +
            '                                           target="_blank" itemprop="url">' + blog.title + '</a>\n' +
            '                                    </h1>\n' +
            '\n' +
            '\n' +
            '                                    <div class="article-meta">\n' +
            '\n' +
            '                                        <div class="article-date">\n' +
            '                                            <i class="fa fa-calendar"></i>\n' +
            '                                            <a href="javascript:;">\n' +
            '                                                <time datetime="2018-04-07T14:23:00.000Z" itemprop="datePublished">\n' +
            '                                                    ' + blog.createTime + '\n' +
            '                                                </time>\n' +
            '                                            </a>\n' +
            '                                        </div>\n' +
            '\n' +
            '\n' +
            '                                        <div class="article-category" style="text-transform: none;">\n' +
            '  ' + _get_tag_dom(blog.tagVOList) +
            '                                        </div>\n' +
            '                                    </div>\n' +
            '                                </div>\n' +
            '                            </div>';
        return resultHtml;
    }

    /**
     * 获取博客列表标签
     * @param tagList 标签元素
     * @private
     */
    function _get_tag_dom(tagList) {
        var resultHtml = "";
        $.each(tagList, function (index, item) {
            resultHtml += '<span> <i class="fa fa-tag"></i><a class="article-category-link" href="javascript:;">' + item.name + '</a></span>';
        });
        return resultHtml;
    }

})(jQuery);
