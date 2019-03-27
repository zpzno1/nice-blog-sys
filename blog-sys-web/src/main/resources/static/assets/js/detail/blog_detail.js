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
    var dataTarget = $(target).parent();
    $.ajax({
        url: '/user',
        success: function (res) {
            if (res.code == 'USER_NOT_LOGIN') {
                toastr.error("登陆后才能进行评论喔~");
                return;
            } else {
                var requestData = new Object();
                requestData.parentId = $(dataTarget).attr('comment-parentId');
                requestData.activeUserId = res.data.openId;
                requestData.passiveUserId = $(dataTarget).attr('comment-passiveUserId');
                requestData.commentUrl = $(dataTarget).attr('comment-url');
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
        beforeSend: function () {
            this.layerIndex = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
        },
        complete: function () {
            layer.close(this.layerIndex);
        },
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
                            beforeSend: function () {
                                this.dialogIndex = index = layer.load(1, {shade: [0.1, '#fff']});////0.1透明度的白色背景
                            },
                            complete: function () {
                                layer.close(this.dialogIndex);
                            },
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
    var commentItem = '  <div class="card">\n' +
        '                <div class="card-body comment-body animated fadeInLeft">\n' +
        '                    <div class="author">\n' +
        '                        <a href="#pablo">\n' +
        '                            <img src="' + item.activeUserHeadUrl + '"\n' +
        '                                 alt="..." class="avatar img-raised">\n' +
        '                            <span>' + item.activeNickName + '</span>\n' +
        '                            <span>&nbsp;&nbsp;&nbsp;' + item.createTime + '</span>\n' +
        '                        </a>\n' +
        '                    </div>\n' +
        '                    <h6 class="card-title">\n' +
        '                        <a href="#pablo">' + item.content + '</a>\n' +
        '                    </h6>\n' +
        '<div class="collapse">' +
        '<div id="comment-reply-' + item.id + '" class="commentReplyContainer"></div>' +
        '<div class="row"><div id="comment-reply-page-' + item.id + '" class="commentReplyPageContainer" style="margin: 5px auto;"></div></div>' +
        '</div>' +
        '                </div>\n' +
        '                <div class="card-footer ">\n' +
        '                    <div class="stats ml-auto" comment-url="/commentReply/reply/create" comment-parentId="' + item.id + '" comment-passiveUserId="' + item.activeUserId + '" >\n' +
        '                        <a href="javascript:;" class="text-dark">\n' +
        '                            <i class="fa fa-thumbs-up"></i>\n' +
        '                            ' + item.starCount + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>\n' +
        '                        <a onclick="showBlogCommentReplyDialog(this)" class="text-dark" title="回复" data-toggle="collapse"><i class="fa fa-reply"></i>回复&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>\n' +
        '                        <a onclick="commentClickCollapse(this);" class="text-dark blog-comment-collapse" data-toggle="collapse" data-status="closed"><span>查看回复(' + item.replyCount + ')</span><i class="fa fa-chevron-down"></i></a>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>  ';
    var $blogCommentContainer = $(commentItem);
    // debugger
    $blogCommentContainer.find('.collapse').on('shown.bs.collapse', function () {
        var requestData = {
            parentId: item.id,
            queryType: 'B_COMMENT_REPLY'
        };
        loadCommentReplyRequest(requestData, $blogCommentContainer);
    });
    return $blogCommentContainer;
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
    var commentReplyItemDom = '<div class="card" style="background: #f9f7f7;margin: 3px 0px 3px 0px">\n' +
        '                             <div class="card-body" style="padding: 10px 0px 0px 15px">\n' +
        '                                 <div class="author">\n' +
        '                                     <a href="#pablo">\n' +
        '                                         <img src="' + item.activeUserHeadUrl + '"\n' +
        '                                              alt="..." class="avatar img-raised">\n' +
        '                                         <span>' + item.activeNickName + '&nbsp;&nbsp;&nbsp;<label>回复</label>&nbsp;&nbsp;&nbsp;<img class="avatar img-raised" src="' + item.passiveUserHeadUrl + '"/>' + item.passiveNickName + '&nbsp;&nbsp;&nbsp;' + item.createTime + '</span>\n' +
        '                                     </a>\n' +
        '                                 </div>\n' +
        '                                 <h6 class="card-text">\n' +
        '                                     <a href="#pablo" class="text-dark">' + item.content + '</a>\n' +
        '                                 </h6>\n' +
        '                             </div>\n' +
        '                             <div class="card-footer" style="padding: 0px 15px 10px 15px">\n' +
        '                                 <div class="stats ml-auto">\n' +
        '                                     <a href="javascript:;" class="text-dark" title="点赞">\n' +
        '                                         <svg class="alibaba-icon" aria-hidden="true">\n' +
        '                                             <use xlink:href="#icon-agree"></use>\n' +
        '                                         </svg>\n' +
        '                                         ' + item.starCount + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>\n' +
        '                                     <a href="#collapseExample"class="text-dark" title="回复" data-toggle="collapse"><i class="fa fa-reply"></i>回复</a>\n' +
        '                                 </div>\n' +
        '                             </div>\n' +
        '                         </div>';
    return $(commentReplyItemDom);
}
