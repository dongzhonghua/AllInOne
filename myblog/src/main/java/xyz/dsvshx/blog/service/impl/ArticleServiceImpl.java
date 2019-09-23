package xyz.dsvshx.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.Article;
import xyz.dsvshx.blog.mapper.ArticleMapper;
import xyz.dsvshx.blog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public JSONObject insertArticle(Article article) {
        JSONObject articleReturn = new JSONObject();

        return null;
    }

    @Override
    public JSONObject getArticleByArticleId(Integer articleId, String username) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","200");
        jsonObject.put("author",article.getAuthor());
        jsonObject.put("articleId",articleId);
        jsonObject.put("originalAuthor",article.getOriginalauthor());
        jsonObject.put("articleTitle",article.getArticletitle());
        jsonObject.put("publishDate",article.getPublishdate());
        jsonObject.put("updateDate",article.getUpdatedate());
        jsonObject.put("articleType",article.getArticletype());
        jsonObject.put("articleCategories",article.getArticlecategories());
        jsonObject.put("articleUrl",article.getArticleurl());
        jsonObject.put("likes",article.getLikes());
        return jsonObject;
    }
}
