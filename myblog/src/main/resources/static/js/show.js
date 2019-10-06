var articleId = "";

$.ajax({
    type: 'HEAD', // 获取头信息，type=HEAD即可
    url: window.location.href,
    async: false,
    success: function (data, status, xhr) {
        console.log(xhr);
        articleId = xhr.getResponseHeader("articleId");
    }
});

function showArticle(data) {
    var articleTop = $(".article-top");
    var articleBottom = $(".article-bottom");
    articleTop.html('');
    articleBottom.html('');
    var articleHeader = $('<div class="article-title"><h1>' + data.articletitle + '</h1></div>'
        + '<div class="header-inline article-meta">' +
        '<span class="article-type badge">' + data['articletype'] + '</span>' +

        '<div><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>' +
        '<a class="link" href="/archives?archive=' + data['publishdate'] + '"> ' + data['publishdate'] + '</a></div>' +
        '<div><span class="glyphicon glyphicon-user">' + data['originalauthor'] + '</span></div>' +
        '<div><span class="glyphicon glyphicon-folder-close">' +
        '<a class="linkColor" href="/categories?category=' + data['articlecategories'] + '">' + data['articlecategories'] + '</a>' + '</span></div>'
        + '</div>'
    );
    articleTop.append(articleHeader);
    var articleTags = $('.article-tags');
    var tags = data['articletags'].split(",");
    for (var i = 0; i < tags.length; i++) {
        var articleTag = $('<i class="glyphicon glyphicon-tag"><a class="tag" href="/tags?tag=' + tags[i] + '">' + tags[i] + '</a></i>');
        articleBottom.append(articleTag);
    }


    $("#mdText").text(data.articlecontent);
    var myArticle = editormd.markdownToHTML("my-article", {
        htmlDecode: "true", // you can filter tags decode
        emoji: true,
        taskList: true,
        tex: true,
        flowChart: true,
        sequenceDiagram: true
    });
}

$.ajax({
    type: 'post',
    url: '/getArticleByArticleId/',
    dataType: 'json',
    async: false,
    data: {
        articleId: articleId
    },
    success: function (data) {
        // if (data.code === 200) {
        showArticle(data['data'])
        // } else {

        // }

    }

});