var currentIndex = 1;

/**
 * 博客点赞按钮
 */
function blogAgreeClick(target) {
    var blogId = $(target).attr('comment-blogId');
    $.ajax({
        url: '/blog/agree/' + blogId,
        method: 'post',
        success: function (res) {
            if (res.code == '0') {
                var count = $(target).attr('comment-count');
                var newCount = parseInt(count) + 1;
                $(target).attr('comment-count', newCount);
                $(target).find('span').removeClass('animated fadeInDown').addClass('animated fadeInDown').html(newCount);
                var dialogOpt = {
                    title: '点赞成功！',
                    icon: "success",
                    timer: 1000
                };
                swal(dialogOpt);
            }
        }
    })

}

/**
 * 博客评论点击
 */
function blogCommentClick(target) {
    $.ajax({
        url: '/user',
        success: function (res) {
            if (res.data == null) {
                toastr.error("登陆后才能进行评论喔~");
            } else {
                var requestData = new Object();
                requestData.parentId = $(target).attr('comment-parentId');
                requestData.activeUserId = res.data.openId;
                requestData.passiveUserId = $(target).attr('comment-passiveUserId');
                requestData.commentUrl = $(target).attr('comment-url');
                requestData.blogId = $('[name=blogId]').val();
                requestData.commentCount = $('[name=commentCount]').val();
                // 缓存当前需要评论的对象
                sessionStorage.setItem("COMMENT_TARGET", JSON.stringify(requestData));
                _showBlogCommentReplyDialog();
            }
        }
    });
}

/**
 * 博客评论点赞
 */
function commentReplyStartClick(target) {
    var realTarget = $(target).parent().parent().parent();
    var commentId = $(realTarget).attr('comment-id');
    $.ajax({
        url: '/commentReply/agree/' + commentId,
        method: 'post',
        success: function (res) {
            if (res.code == '0') {
                var count = $(realTarget).attr('comment-count');
                var newCount = parseInt(count) + 1;
                $(target).attr('comment-count', newCount);
                $(target).find('span').removeClass('animated fadeInDown').addClass('animated fadeInDown').html(newCount);
                var dialogOpt = {
                    title: '点赞成功！',
                    icon: "success",
                    timer: 1000
                };
                swal(dialogOpt);
            }
        }
    })
}

/**
 * 博客评论回复点击
 */
function commentReplyClick(target) {
    $.ajax({
        url: '/user',
        success: function (res) {
            if (res.data == null) {
                toastr.error("登陆后才能进行评论喔~");
            } else {
                var realTarget = $(target).parent().parent().parent();
                var requestData = new Object();
                requestData.parentId = $(realTarget).attr('comment-parentId');
                requestData.activeUserId = res.data.openId;
                requestData.passiveUserId = $(realTarget).attr('comment-passiveUserId');
                requestData.commentUrl = $(realTarget).attr('comment-url');
                requestData.blogId = $('[name=blogId]').val();
                requestData.commentCount = $('[name=commentCount]').val();
                // 缓存当前需要评论的对象
                sessionStorage.setItem("COMMENT_TARGET", JSON.stringify(requestData));
                _showBlogCommentReplyDialog();
            }
        }
    });
}

/**
 * 回复的回复点击事件
 * @param target
 */
function replyReplyClick(target) {
    $.ajax({
        url: '/user',
        success: function (res) {
            if (res.data == null) {
                toastr.error("登陆后才能进行评论喔~");
            } else {
                var realTarget = $(target).parent().parent().parent();
                debugger
                var requestData = new Object();
                requestData.parentId = $(realTarget).parent().parent().parent().parent().attr('comment-parentId');
                requestData.activeUserId = res.data.openId;
                requestData.passiveUserId = $(realTarget).attr('comment-passiveUserId');
                requestData.commentUrl = $(realTarget).attr('comment-url');
                requestData.blogId = $('[name=blogId]').val();
                requestData.commentCount = $('[name=commentCount]').val();
                // 缓存当前需要评论的对象
                sessionStorage.setItem("COMMENT_TARGET", JSON.stringify(requestData));
                _showBlogCommentReplyDialog();
            }
        }
    });
}


/**
 * 弹出发表评论的模态框
 */
function _showBlogCommentReplyDialog() {
    var options = {
        backdrop: true,
        keyboard: true,
    };
    $('#commentReplyDialog').modal(options);
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
 * 博客回复展开图标处理
 */
function commentClickCollapse(target) {
    var replyTarget = $(target).parent();
    var $collapseExample = $(target).parent().parent().parent().find('.collapse');

    console.log(replyTarget);
    var $show = '';
    if ($(target).attr('data-status') == 'closed') {
        $show = '<i class="fa fa-chevron-up"></i>';
        $(target).attr('data-status', 'open');
        $collapseExample.collapse('show');
    } else {
        $show = '<i class="fa fa-chevron-down"></i>';
        $(target).attr('data-status', 'closed');
        $collapseExample.collapse('hide');
    }
    var $span = $(target).find('span').prop("outerHTML");
    $(target).html($span + $show);
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
        success: function (res) {
            if (res.code == '0') {
                if (res.data.pages >= res.data.current) {
                    $.each(res.data.records, function (index, item) {
                        $('#blogCommentContainer').append(_generate_blog_comment_item(item));
                    })
                } else {
                    //到底了，只提醒一次
                    if (!fistTime2Bottom) {
                        $('#blogCommentContainer').append('<h4 style="text-align: center" class="animated rubberBand">已经到底啦~~</h4>');
                        fistTime2Bottom = true;
                    }
                }
            } else {
                blog_util.failMessage(res);
            }
        }
    })
}


/**
 * 加载评论回复功能
 */
function loadCommentReplyRequest(param, $blogCommentContainer) {
    var requestData = $.extend({size: 4}, param);
    var replyCommentId = requestData.parentId;
    $.ajax({
        url: '/commentReply/query',
        method: 'get',
        data: requestData,
        success: function (res) {
            var opt = {
                total: res.data.total,
                items_per_page: 4,
                num_display_entries: 3,
                num_edge_entries: 2,
                next_text: '下一页',
                prev_text: '前一页',
                /*分页回调*/
                callback: function (new_page_index, pagination_container) {
                    var $commentContainer = $blogCommentContainer.find('#comment-reply-' + replyCommentId);
                    $commentContainer.html('');
                    if (new_page_index == 0) {
                        _renderCommentReplyContaner(res, $commentContainer);
                    } else {
                        var requestData = $.extend(param, {size: 4, current: new_page_index + 1});
                        $.ajax({
                            url: '/commentReply/query',
                            data: requestData,
                            method: 'get',
                            success: function (res) {
                                _renderCommentReplyContaner(res, $commentContainer);
                            }
                        });
                    }
                    return false;
                }
            };
            //TODO 这里获取的回复插件中的ID和当前操作的数据对不上
            var $replyPagePlugin = $blogCommentContainer.find('#comment-reply-page-' + replyCommentId);
            var total = opt.total;
            $replyPagePlugin.pagination(total, opt);
        }
    });
}


/**
 * 发表博客评论或者回复
 */
function makeBlogCommentRequest(requestData) {
    console.log("评论回复请求数据:", requestData);
    debugger
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
    //FIXME 需要动态的在此绑定事件
    var commentItem = '<div  class="card" comment-id="' + item.id + '" comment-url="/commentReply/reply/create" comment-parentId="' + item.id + '" comment-passiveUserId="' + item.activeUserId + '" comment-count="'+item.starCount+'">' +
        '                <div class="card-body comment-body animated fadeInLeft">' +
        '                    <div class="author">' +
        '                        <a href="#pablo">' +
        '                            <img src="' + item.activeUserHeadUrl + '"' +
        '                                 alt="..." class="avatar img-raised">' +
        '                            <span>' + item.activeNickName + '</span>' +
        '                            <span>&nbsp;&nbsp;&nbsp;' + item.createTime + '</span>' +
        '                        </a>' +
        '                    </div>' +
        '                    <h6 class="card-title">' +
        '                        <a href="#pablo">' + item.content + '</a>' +
        '                    </h6>' +
        '<div class="collapse">' +
        '<div id="comment-reply-' + item.id + '" class="commentReplyContainer"></div>' +
        '<div class="row"><div id="comment-reply-page-' + item.id + '" class="commentReplyPageContainer" style="margin: 5px auto;"></div></div>' +
        '</div>' +
        '                </div>' +
        '                <div class="card-footer ">' +
        '                    <div class="stats ml-auto">' +
        '                        <a onclick="commentReplyStartClick(this)" class="text-dark">' +
        '                            <i class="fa fa-thumbs-up"></i>' +
        '                           <span>' + item.starCount + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>' +
        '                        <a onclick="commentReplyClick(this)" class="text-dark" title="回复" data-toggle="collapse"><i class="fa fa-reply"></i>回复&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>' +
        '' + _getReplyPermissionDom(item) +
        '                    </div>' +
        '                </div>' +
        '            </div>  ';
    var $blogCommentContainer = $(commentItem);
    if (item.replyCount != 0) {
        $blogCommentContainer.find('.collapse').on('shown.bs.collapse', function () {
            var requestData = {
                parentId: item.id,
                queryType: 'B_COMMENT_REPLY'
            };
            loadCommentReplyRequest(requestData, $blogCommentContainer);
        });
    }
    return $blogCommentContainer;
}

/**
 * 获取回复dom元素
 * @param item
 * @returns {string}
 * @private
 */
function _getReplyPermissionDom(item) {
    var dataToggle = item.replyCount > 0 ? 'data-toggle=collapse' : '';
    if (item.replyCount > 0) {
        return '<a onclick="commentClickCollapse(this);" data-status="closed" class="text-dark blog-comment-collapse"' + dataToggle + '><span>更多回复(' + item.replyCount + ')</span><i class="fa fa-chevron-down"></i></a>';
    } else {
        return '<a data-status="closed" class="text-dark blog-comment-collapse"' + dataToggle + '><span>更多回复(' + item.replyCount + ')</span></a>';
    }
}


/**
 * 渲染评论视图
 * @private
 */
function _renderCommentReplyContaner(res, $target) {
    if (res.code == '0') {
        if (res.data.records.length > 0) {
            $.each(res.data.records, function (index, item) {
                $target.append(_generate_comment_reply_item(item));
            });
        } else {
            $target.append('<div class="text-center"><h3>已经没有更多的数据了</h3></div>');
        }
    } else {
        blog_util.failMessage(res);
    }
}

/**
 * 生成评论回复html
 * @returns {string}
 * @private
 */
function _generate_comment_reply_item(item) {
    var commentReplyItemDom = '<div class="card" comment-url="/commentReply/reply/create" comment-id="'+item.id+'" comment-passiveUserId="' + item.activeUserId + '" comment-count="'+item.starCount+'" style="background: #f9f7f7;margin: 3px 0px 3px 0px">' +
        '                             <div class="card-body" style="padding: 10px 0px 0px 15px">' +
        '                                 <div class="author">' +
        '                                     <a href="#pablo">' +
        '                                         <img src="' + item.activeUserHeadUrl + '"' +
        '                                              alt="..." class="avatar img-raised">' +
        '                                         <span>' + item.activeNickName + '&nbsp;&nbsp;&nbsp;<label>回复</label>&nbsp;&nbsp;&nbsp;<img class="avatar img-raised" src="' + item.passiveUserHeadUrl + '"/>' + item.passiveNickName + '&nbsp;&nbsp;&nbsp;' + item.createTime + '</span>' +
        '                                     </a>' +
        '                                 </div>' +
        '                                 <h6 class="card-text">' +
        '                                     <a href="#pablo" class="text-dark">' + item.content + '</a>' +
        '                                 </h6>' +
        '                             </div>' +
        '                             <div class="card-footer" style="padding: 0px 15px 10px 15px">' +
        '                                 <div class="stats ml-auto">' +
        '                                     <a onclick="commentReplyStartClick(this)" class="text-dark" title="点赞">' +
        '                                         <svg class="alibaba-icon" aria-hidden="true">' +
        '                                             <use xlink:href="#icon-agree"></use>' +
        '                                         </svg>' +
        '                                         <span>' + item.starCount + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>' +
        '                                     <a onclick="replyReplyClick(this)" class="text-dark" title="回复" data-toggle="collapse"><i class="fa fa-reply"></i>回复</a>' +
        '                                 </div>' +
        '                             </div>' +
        '                         </div>';
    return $(commentReplyItemDom);
}
