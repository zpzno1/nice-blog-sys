/**
 * 描述:博文发布相关js
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/11/30
 */
(function ($, window) {

    /*****************************************配置部分****************************************/
    var setting = {
        editormd: {
            width: "100%",
            height: "89%",
            syncScrolling: "single",
            path: "../../../../static/assets_admin/js/plugins/editor-md/lib/",
            placeholder: "在这里开始编写您的博客吧",
            emoji: true,
            toolbarIcons: function () {
                var toolBar = [
                    "del", "italic", "quote", "|",
                    "list-ul", "list-ol", "hr", "|",
                    "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "emoji", "html-entities", "pagebreak", "|",
                    "goto-line", "watch", "preview", "fullscreen", "clear", "search", "|",
                    "mdUpload", "mdDownload", "tag", "save", "help"
                ];
                return toolBar;
            },
            toolbarIconsClass: {
                mdUpload: "fa fa-upload",
                mdDownload: "fa fa-download",
                tag: "fa fa-tags",
                save: "fa fa-save"
            },
            lang: {
                toolbar: {
                    mdUpload: "博文导入", /*只允许md格式*/
                    mdDownload: "博文导出", /*考虑格式：md还是pdf*/
                    tag: "博客标签分类",
                    save: "保存"
                }
            }, toolbarHandlers: {
                /**
                 * @param {Object}      cm         CodeMirror对象
                 * @param {Object}      icon       图标按钮jQuery元素对象
                 * @param {Object}      cursor     CodeMirror的光标对象，可获取光标所在行和位置
                 * @param {String}      selection  编辑器选中的文本
                 */
                mdUpload: function (cm, icon, cursor, selection) {
                    console.log('click mdUpload')
                },
                mdDownload: function (cm, icon, cursor, selection) {
                    console.log('click mdDownload')
                },
                tag: function (cm, icon, cursor, selection) {
                    console.log('click tag');
                    this.executePlugin("tagDialog", "tag-dialog/tag-dialog");
                },
                save: function (cm, icon, cursor, selection) {
                    console.log('click save')
                }
            },
        },
    }

    /*****************************************方法部分****************************************/

    /**
     * markdown编辑器初始化
     * @param id
     */
    function init_markdown(id) {
        editormd.emoji = {
            path: "../../../static/js/plugins/editor-md/plugins/emoji-dialog/emojis/",
            ext: ".png"
        };
        testEditor = editormd(id, setting.editormd);
    }

    window.article_send = {
        init_markdown:init_markdown
    }
})(jQuery, window);