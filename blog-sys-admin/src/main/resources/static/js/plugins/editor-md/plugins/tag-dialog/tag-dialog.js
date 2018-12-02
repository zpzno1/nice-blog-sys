/*!
 * tag plugin for Editor.md
 *
 * @file       tag-plugin.js
 * @author      kiwipeach
 * @version     1.0.0
 * @updateTime  2018-12-2
 */

(function () {

    var factory = function (exports) {

        var $ = jQuery;
        var pluginName = "tag-dialog";

        exports.fn.tagDialog = function () {
            layer.open({
                type: 2,
                area: ['800px', '500px'],
                title: this.lang.toolbar.tag,
                content: 'atricle_send_dialog.html',
                btn: ['发布博文', '取消'],
                yes: function (index, layero) {
                    layer.closeAll();
                },
                btn3: function (index, layero) {
                    layer.closeAll();
                },
                cancel: function () {
                    //右上角关闭回调
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        };


    };

    // CommonJS/Node.js
    if (typeof require === "function" && typeof exports === "object" && typeof module === "object") {
        module.exports = factory;
    }
    else if (typeof define === "function")  // AMD/CMD/Sea.js
    {
        if (define.amd) { // for Require.js

            define(["editormd"], function (editormd) {
                factory(editormd);
            });

        } else { // for Sea.js
            define(function (require) {
                var editormd = require("./../../editormd");
                factory(editormd);
            });
        }
    }
    else {
        factory(window.editormd);
    }

})();
