//    调用编辑器
var testEditor;
$(function () {
    testEditor = editormd("editormd", {
        width: "100%",
        height: "800",
        syncScrolling: true,
        path: "../plugin/editormd/lib/",
        // previewTheme : "dark", //代码块使用dark主题
        codeFold: true,
        emoji: true,
        tocm: true, // Using [TOCM]
        tex: true, // 开启科学公式TeX语言支持，默认关闭
        flowChart: true, // 开启流程图支持，默认关闭
        sequenceDiagram: true, // 开启时序/序列图支持，默认关闭,
        htmlDecode: true, //不过滤标签
        imageUpload: true, //上传图片
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp", "JPG", "JPEG", "GIF", "PNG", "BMP", "WEBP"],
        imageUploadURL: "/uploadImage", onload: function () {
            // console.log('onload', this);
        },
        saveHTMLToTextarea: true, //注意3：这个配置，方便post提交表单
        toolbarIcons: function () {
            return ["bold", "del", "italic", "quote", "|", "h1", "h2", "h3", "h4", "h5", "h6", "|", "list-ul", "list-ol", "hr", "|", "link", "image", "code", "code-block", "table", "datetime", "html-entities", "emoji", "|", "watch", "preview", "fullscreen", "clear", "search", "|", "help", "info"]
        }
    });
});
