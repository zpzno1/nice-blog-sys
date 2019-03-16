/**
 * 博客详情页面的js脚本,主要功能是:初始化详情页面插件和注册博客详情相关的事件。
 */
(function ($, window) {
    console.log('detail js');
    // 默认事件(回到顶部)
    blog_common.defaul_plugin_init();

    // 加载更多评论按钮
    bind_load_comment_btn();
    // 博客评论按钮
    open_blog_comment_btn();
    // 博客评论发表按钮
    send_blog_comment_btn();


})(jQuery);

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
        // 模态框关闭事件坚挺
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
        debugger
        $.ajax({
            url: '/commentReply/comment/create',
            method: 'post',
            data: requestData,
            success: function (res) {
                debugger
                if ("0" == res.code) {
                    $('#commentReplyDialog').modal('hide');
                    swal("您发表评论成功！", "支持嵌套评论，不服求干！", "success")
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

