/**
 * 博客详情页面的js脚本,主要功能是:初始化详情页面插件和注册博客详情相关的事件。
 */

var currentIndex = 1;
(function ($, window) {
    console.log('detail js');
    // 默认事件(回到顶部)
    blog_common.defaul_plugin_init();
    // 默认加载评论信息
    load_blog_comment({
        queryType: 'BLOG_COMMENT',
        parentId: '109950',
        current: currentIndex,
        size: 5
    });
    // 滚动检测加载评论信息
    scroll_load_blog_comment(currentIndex);

    // 加载更多评论按钮
    bind_load_comment_btn();
    // 博客评论按钮
    open_blog_comment_btn();
    // 博客评论发表按钮
    send_blog_comment_btn();
})(jQuery);

/**
 * 检测滚动条事件，并且动态加载评论信息
 */
function scroll_load_blog_comment() {
    currentIndex += 1;
    var opt = {
        queryType: 'BLOG_COMMENT',
        parentId: '109950',
        current: currentIndex,
        size: 3
    };
    $(window).scroll(function () {
        //如果到达了博客底部，那么继续动态加载博客信息
        if (blog_util.isScrollToPageBottom()) {
            load_blog_comment(opt);
        }
    })
}

/**
 * 加载博客评论信息
 */
function load_blog_comment(opt) {
    $.ajax({
        url: '/commentReply/query',
        data: opt,
        beforeSend: function () {
            this.layerIndex = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
        },
        complete: function () {
            layer.close(this.layerIndex);
        },
        success: function (res) {
            if (res.code == '0') {
                if (res.data.pages != res.data.pages.current) {
                    var dnamicHtml = '';
                    $.each(res.data.records, function (index, item) {
                        dnamicHtml += _render_blog_comment_item(item);
                    })
                    $('#blogCommentContainer').append(dnamicHtml);
                } else {
                    layer.msg('没有更多的评论了');
                }
            } else if (res.code == '0') {
                layer.msg(res.msg);
            }
        }
    })
}

/**
 * 博客评论展开打开和关闭事件监听
 */
function blog_comment_collapse_click(target) {
    console.log(target);
    var $show = '';
    if ($(target).attr('data-status') == 'closed') {
        $show = '<i class="fa fa-chevron-up"></i>';
        $(target).attr('data-status', 'open');
    } else {
        $show = '<i class="fa fa-chevron-down"></i>';
        $(target).attr('data-status', 'closed');
    }
    var $span = $(target).find('span').prop("outerHTML");
    $(target).html($span + $show);
    var $collapseExample = $(target).parent().parent().parent().find('.collapse');
    $collapseExample.collapse('toggle');
}

/**
 * 打开博客按钮
 */
function open_blog_comment_btn() {
    $('#commentReplyBtn').bind('click', function () {
        // 缓存当前需要评论的对象
        var commentObj = new Object();
        commentObj.type = $(this).attr('comment-type');
        commentObj.passivePerson = $(this).attr('comment-data');
        commentObj.parentId = $(this).attr('comment-data');
        sessionStorage.setItem("COMMENT_TARGET", JSON.stringify(commentObj));
        // 弹出模态框
        var options = {
            backdrop: true,
            keyboard: true,
        };
        $('#commentReplyDialog').modal(options);
        // 模态框关闭事件
        $('#commentReplyDialog').on('hidden.bs.modal', function (e) {
            // 重置发表界面内容
            $('#commentMessageText').val('');
            $('#commentMessageText').attr('placeholder', '评论支持markdown语法');
            $('#loginTipInfo').html('<h4 class="description text-center" id="loginTipInfo">文明发言，共建和谐社区</h4>');
        })
    });
}

/**
 * 发表博客按钮
 */
function send_blog_comment_btn() {
    $('#sendCommentReplyBtn').bind('click', function () {
        var requestData = JSON.parse(sessionStorage.getItem("COMMENT_TARGET"));
        requestData.content = $('#commentMessageText').val();
        $.ajax({
            url: '/commentReply/comment/create',
            method: 'post',
            data: requestData,
            success: function (res) {
                if ("0" == res.code) {
                    //更新ui
                    var newestCount = (parseInt($('#commentReplyBtn').attr('comment-count')) + 1);
                    $('#commentReplyBtn').html('<i class="fas fa-comment"></i>' + newestCount);
                    $('#commentReplyDialog').modal('hide');
                    var dialogOpt = {
                        title: '您发表评论成功！',
                        text: '支持嵌套评论，不服求干！',
                        icon: "success",
                        // timer: 2000
                    };
                    swal(dialogOpt);
                } else {
                    blog_util.failMessage(res);
                }
            }
        })
    });
}

/**
 * 加载更多评论
 */
function bind_load_comment_btn() {
    $('#load_comment_btn').bind('click', function () {
        var comment_load_layer = layer.load(0, {shade: false});
        setTimeout(function () {
            layer.close(comment_load_layer);
        }, 500);
        //alert('load comment event.');
    });
}

/**
 * 动态生成博客评论内容
 * @param item
 * @returns {string}
 * @private
 */
function _render_blog_comment_item(item) {
    var commentItem = '  <div class="card">\n' +
        '                <div class="card-body comment-body">\n' +
        '                    <div class="author">\n' +
        '                        <a href="#pablo">\n' +
        '                            <img src="' + item.activeUserHeadUrl + '"\n' +
        '                                 alt="..." class="avatar img-raised">\n' +
        '                            <span>' + item.activeNickName + '</span>\n' +
        '                        </a>\n' +
        '                    </div>\n' +
        '                    <h6 class="card-title">\n' +
        '                        <a href="#pablo">' + item.content + '</a>\n' +
        '                    </h6>\n' +
        '                </div>\n' +
        '                <div class="card-footer ">\n' +
        '                    <div class="stats ml-auto">\n' +
        '                        <a href="javascript:;" class="text-dark">\n' +
        '                            <i class="fa fa-thumbs-up"></i>\n' +
        '                            ' + item.starCount + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>\n' +
        '                        <a href="#collapseExample"class="text-dark" title="回复" data-toggle="collapse"><i class="fa fa-reply"></i>回复&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>\n' +
        '                        <a href="#collapseExample" onclick="blog_comment_collapse_click(this);" class="text-dark blog-comment-collapse" data-toggle="collapse" data-status="closed"><span>查看回复(' + item.replyCount + ')</span><i class="fa fa-chevron-down"></i></a>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>  ';

    return commentItem;
}

