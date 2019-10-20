issueBlog();

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
var noticeBox = $('.notice-box');
var noticeBoxTitle = $('.notice-box-title');
var noticeBoxContent = $('.notice-box-content');

$("#public-button").click(function () {
    var publishBtn = $("#public-button");
    var articleTitle = $("#article-title");
    var articleContent = $("#editormd-html");

    var title = articleTitle.val();
    var content = articleContent.val();
    //清空
    $(".tags-minus").remove();
    if (title.length === 0) {
        noticeBoxTitle.show();
    } else if (content.length === 0) {
        noticeBoxContent.show();
    } else {
        //填充category
        putInCategory();
        $("#myModal").modal("show");
    }

    // 定时关闭错误提示框
    var closeNoticeBox = setTimeout(function () {
        noticeBox.hide();
    }, 2000);

});
//发表博客
var surePublishBtn = $('.surePublishBtn');
var articleCategories = $('#select-categories');
var articleType = $('#select-type');

surePublishBtn.click(function () {
    var tagNum = $('#tags-input').find('.tag-name').length;
    console.log(tagNum);
    var articleTagsValue = [];
    for (var j = 0; j < tagNum; j++) {
        articleTagsValue[j] = $('.tag-name').eq(j).html();
    }
    var articleTitle = $("#article-title");
    var articleContent = $("#editormd-html");

    if (articleTagsValue.length === 0 || articleTagsValue[tagNum - 1] === "") {
        $('.notice-box-tags').show();
    }  else {
        $.ajax({
            type: "POST",
            url: "/publishArticle",
            traditional: true,// 传数组
            data: {
                // id: $('.surePublishBtn').attr("id"),
                articletitle: articleTitle.val(),
                articlecontent: articleContent.val(),
                articleTagsValue: articleTagsValue,
                articletype: articleType.val(),
                articlecategories: articleCategories.val(),
                articleGrade: "5",
                originalauthor: "董仲华",
                articleurl: "123",
                articleHtmlContent: testEditor.getHTML()
            },
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "json",
            success: function (data) {
                if (data['status'] == 200) {
                    $('#my-alert').modal('close');
                    window.removeEventListener('beforeunload', fnClose);
                    publishSuccessPutIn(data);
                } else if (data['status'] == 403) {
                    window.removeEventListener('beforeunload', fnClose);
                    $.get("/toLogin", function (data, status, xhr) {
                        window.location.replace("/login");
                    });
                } else if (data['status'] == 500) {
                    alert("发布失败了，都叫你不要发布了，不听嘛")
                } else {
                    alert("发表博客失败");
                }
            },
            error: function () {
                alert("发表博客请求失败！")
            }
        })
    }

    // 定时关闭错误提示框
    var closeNoticeBox = setTimeout(function () {
        noticeBox.hide();
    }, 2000);
});


function putInCategory() {
    $.ajax({
        type: "GET",
        url: "/findCategoriesName",
        async: false,
        data: {},
        dataType: "json",
        success: function (data) {
            var selectCategories = $('#select-categories');
            selectCategories.empty();
            selectCategories.append($('<option class="categoriesOption" value="choose">请选择</option>'));
            for (var i = 0; i < data[0].length; i++) {
                selectCategories.append($('<option class="categoriesOption" value="' + data[0][i] + '">' + data[0][i] + '</option>'));
            }

        }
    })
}

function issueBlog() {
    var tags = $("#tags-input");
    $("#append-tags").click(function () {
        tags.append('<div class="tags-minus"><p class="tag-name" contenteditable="true"></p>' +
            '<i id="minus-sign" class="glyphicon glyphicon-minus-sign"></i></div>\n');
    });
    $("#tags-input").on('click', '#minus-sign', function () {
        $(this).parent().remove();
    });


}

