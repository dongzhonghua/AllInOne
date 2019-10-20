package xyz.dsvshx.blog.service;

import com.alibaba.fastjson.JSONArray;
import xyz.dsvshx.blog.entity.ArticleWithBLOBs;
import xyz.dsvshx.blog.utils.Result;
import org.springframework.transaction.annotation.Transactional;
import xyz.dsvshx.blog.entity.Article;

import java.util.Map;

public interface ArticleService {
    Result insertArticle(ArticleWithBLOBs article);
    @Transactional
    Result updateArticleById(Article article);
    Map<String, String> findArticleTitleByArticleId(long id);
    Result findAllArticles(String rows, String pageNo);
    void updateArticleLastOrNextId(String lastOrNext, long lastOrNextArticleId, long articleId);
    Result findArticleByTag(String tag, int rows, int pageNum);

    Result findArticleByCategory(String category, int rows, int pageNum);
    Result findArticleByArchive(String archive, int rows, int pageNum);
    Result getDraftArticle(Article article, String[] articleTags, int articleGrade);

    Article getArticleByArticleId(Integer articleId, String username);
    int updateLikeByArticleId(long articleId);
    Result getArticleManagement(int rows, int pageNum);
    Article findArticleById(int id);
    int countArticleCategoryByCategory(String category);
    int countArticleArchiveByArchive(String archive);
    int countArticle();
    @Transactional
    int deleteArticle(long id);
}
