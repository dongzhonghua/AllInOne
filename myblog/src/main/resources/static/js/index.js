//填充
function putInArticle(data) {
    var articles = $("#articles");
    articles.empty();
    $.each(data['articles'], function (index, obj) {
        if (index != (data.length) - 1) {
            var center = $('<div class="article-content center">' +

                '<header class="article-header">' +
                '<h2>' +
                '<a class="article-title" href="' + obj['thisArticleUrl'] + '" target="_blank">' + obj['articleTitle'] + '</a>' +
                '</h2>' +
                '<div class="header-inline article-meta">' +
                '<span class="article-type badge">' + obj['articleType'] + '</span>' +

                '<div><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>' +
                '<a class="link" href="/archives?archive=' + obj['publishDate'] + '"> ' + obj['publishDate'] + '</a></div>' +
                '<div><span class="glyphicon glyphicon-user">' + obj['originalAuthor'] + '</span></div>' +
                '<div><span class="glyphicon glyphicon-folder-close">' +
                '<a class="linkColor" href="/categories?category=' + obj['articleCategories'] + '">' + obj['articleCategories'] + '</a>' + '</span></div>'
                + '</div>' +
                '</header>'
                +
                '<div class="article-entry">' +
                obj['articleTabloid'] +
                '</div>' +
                '<div class="read-all ">' +
                '<a href="' + obj['thisArticleUrl'] + '" target="_blank">阅读全文&raquo;</></a>' +
                '</div>' +
                '<hr>' +
                '<div class="article-tags">' +

                '</div>' +
                '</div>');
            articles.append(center);
            var articleTags = $('.article-tags');
            var tags = obj['articleTags'].split(",");
            for (var i = 0; i < tags.length; i++) {
                var articleTag = $('<i class="glyphicon glyphicon-tag"><a class="tag" href="/tags?tag=' + tags[i] + '">' + tags[i] + '</a></i>');
                articleTags.eq(index).append(articleTag);
            }
        }

    })

}

//发送请求

function getArticle(currentPage) {
    $.ajax({
        type: 'GET',
        url: '/myArticles',
        datatype: 'json',
        data: {
            rows: "1",
            pageNum: currentPage
        },
        success: function (data) {
            putInArticle(data['data']);
            scrollTo(0, 0);
            //分页
            pageNav(data['data']['pageInfo']);

        }
    })
}

function pageNav(result) {
    console.log(result);
    var pageInfo = $("#page-info-area");
    var pageNav = $("#page-nav-area");
    pageInfo.empty();
    pageNav.empty();

    pageInfo.append("当前第" + result.pageNum + "页，共" +
        result.pages + "页，总" + result.total + "篇文章");
    var ul = $("<ul></ul>").addClass("pagination").addClass("nav-cursor");
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.isFirstPage) {
        //判断是否有前一页
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            getArticle(1);
        });
        prePageLi.click(function () {
            getArticle(result.pageNum - 1);
        });
    }
    ul.append(firstPageLi).append(prePageLi);
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页"));
    if (result.isLastPage) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        //为末页和下一页添加点击跳转事件
        nextPageLi.click(function () {
            getArticle(result.pageNum + 1);
        });
        lastPageLi.click(function () {
            getArticle(result.pages);
        });
    }
    $.each(result.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.pageNum == item) {
            //判断是否是当前页，如果是就高亮显示
            numLi.addClass("active");
        }
        ;
        numLi.click(function () {
            //调用ajax请求，跳转到指定页面
            getArticle(item);
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi);
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page-nav-area");
}


getArticle(1);