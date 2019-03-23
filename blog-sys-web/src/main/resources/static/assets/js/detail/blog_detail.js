var currentIndex = 1;

/**
 * 博客点赞按钮
 */
function blogAgreeClick(target) {
    alert('点赞:' + JSON.stringify(target));
}

/**
 * 打开博客按钮
 */
function showBlogCommentReplyDialog(target) {
    // 缓存当前需要评论的对象
    $.ajax({
        url: '/user',
        success: function (res) {
            if (res.code == 'USER_NOT_LOGIN') {
                toastr.info("登陆后才能进行评论喔~");
                return;
            } else {
                var requestData = new Object();
                requestData.parentId = $(target).attr('comment-parentId');
                requestData.activeUserId = res.data.openId;
                requestData.passiveUserId = $(target).attr('comment-passiveUserId');
                requestData.commentUrl = $(target).attr('comment-url');
                requestData.blogId = $('[name=blogId]').val();
                requestData.commentCount = $('[name=commentCount]').val();
                sessionStorage.setItem("COMMENT_TARGET", JSON.stringify(requestData));
                // 弹出模态框
                var options = {
                    backdrop: true,
                    keyboard: true,
                };
                $('#commentReplyDialog').modal(options);
            }
        }
    });

}

/**
 * 模态框关闭事件
 */
function commentReplyDialogClose() {
    // 模态框关闭事件
    $('#commentReplyDialog').on('hidden.bs.modal', function (e) {
        // 重置发表界面内容
        $('#commentMessageText').val('');
        $('#commentMessageText').attr('placeholder', '评论支持markdown语法');
        $('#loginTipInfo').html('<h4 class="description text-center" id="loginTipInfo">文明发言，共建和谐社区</h4>');
    })
}

/**
 * 发表按钮事件绑定
 */
function commentReplySendClick() {
    var requestData = JSON.parse(sessionStorage.getItem("COMMENT_TARGET"));
    requestData.content = $('#commentMessageText').val();
    makeBlogCommentRequest(requestData);
}

/**
 * 博客评论回复点击事件
 * @param target
 */
function commentReplyClick(target) {
    var requestData = new Object();
    requestData.parentId = $(target).attr('data-parentId');
    requestData.parentId = $(target).attr('data-parentId');
    makeBlogCommentRequest();
}

/**
 * 博客回复展开
 */
function commentClickCollapse(target) {
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
 * 博客评论滚动加载
 */
function blogCommentScroll(opt) {
    $(window).scroll(function () {
        //如果到达了博客底部，那么继续动态加载博客信息
        if (blog_util.isScrollToPageBottom()) {
            currentIndex = currentIndex + 1;
            console.log("-->" + currentIndex)
            opt.current = currentIndex;
            loadBlogCommentRequest(opt);
        }
    })
}

/**
 * 加载博客评论信息
 */
function loadBlogCommentRequest(opt) {
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
                if (res.data.pages >= res.data.current) {
                    var dnamicHtml = '';
                    $.each(res.data.records, function (index, item) {
                        dnamicHtml += _generate_blog_comment_item(item);
                    })
                    $('#blogCommentContainer').append(dnamicHtml);
                    //到底了，只提醒一次
                } else {
                    if (!fistTime2Bottom) {
                        $('#blogCommentContainer').append('<h4 style="text-align: center" class="animated rubberBand">已经到底啦~~</h4>');
                        fistTime2Bottom = true;
                    }
                }
            } else {
                layer.msg(res.msg);
            }
        }
    })
}


/**
 * 发表博客评论或者回复
 */
function makeBlogCommentRequest(requestData) {
    console.log("评论回复请求数据:", requestData);
    $.ajax({
        url: requestData.commentUrl,
        method: 'post',
        data: requestData,
        success: function (res) {
            if ("0" == res.code) {
                //更新ui
                $('[name=commentCount]').val(parseInt($('[name=commentCount]').val()) + 1);
                $('#commentReplyBtn').html('<i class="fas fa-comment"></i>' + $('[name=commentCount]').val());
                $('#blogCommentContainer').html('');
                currentIndex = 1;
                loadBlogCommentRequest({
                    queryType: 'B_BLOG_COMMENT',
                    parentId: blogId,
                    current: currentIndex,
                    size: 3
                });
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
    });
}


/**
 * 动态生成博客评论内容
 * @param item
 * @returns {string}
 * @private
 */
function _generate_blog_comment_item(item) {
    var commentItem = '  <div class="card">\n' +
        '                <div class="card-body comment-body animated fadeInLeft">\n' +
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
        '                        <a href="#collapseExample" onclick="showBlogCommentReplyDialog(this)" comment-url="/commentReply/reply/create" comment-parentId="' + item.id + '" comment-passiveUserId="' + item.activeUserId + '" class="text-dark" title="回复" data-toggle="collapse"><i class="fa fa-reply"></i>回复&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>\n' +
        '                        <a href="#collapseExample" onclick="commentClickCollapse(this);" class="text-dark blog-comment-collapse" data-toggle="collapse" data-status="closed"><span>查看回复(' + item.replyCount + ')</span><i class="fa fa-chevron-down"></i></a>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>  ';

    return commentItem;
}

