package xyz.dsvshx.blog.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.Article;

public interface ArticleService {
    JSONObject insertArticle(Article article);
    JSONObject getArticleByArticleId(Integer articleId, String username);
}
