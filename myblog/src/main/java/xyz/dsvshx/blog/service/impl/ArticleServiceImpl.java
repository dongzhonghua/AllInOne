package xyz.dsvshx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.Article;
import xyz.dsvshx.blog.mapper.ArticleMapper;
import xyz.dsvshx.blog.service.*;
import xyz.dsvshx.blog.utils.Result;

import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleLikesRecordService articleLikesRecordService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentLikesRecordService commentLikesRecordService;

    @Override
    public Result insertArticle(Article article) {


        return null;
    }

    @Override
    public Result updateArticleById(Article article) {
        return null;
    }

    @Override
    public Map<String, String> findArticleTitleByArticleId(long id) {
        return null;
    }

    @Override
    public Result findAllArticles(String rows, String pageNo) {
        return null;
    }

    @Override
    public void updateArticleLastOrNextId(String lastOrNext, long lastOrNextArticleId, long articleId) {

    }

    @Override
    public Result findArticleByTag(String tag, int rows, int pageNum) {
        return null;
    }

    @Override
    public Result findArticleByCategory(String category, int rows, int pageNum) {
        return null;
    }

    @Override
    public Result findArticleByArchive(String archive, int rows, int pageNum) {
        return null;
    }

    @Override
    public Result getDraftArticle(Article article, String[] articleTags, int articleGrade) {
        return null;
    }

    @Override
    public Article getArticleByArticleId(Integer articleId, String username) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        return article;
    }

    @Override
    public int updateLikeByArticleId(long articleId) {
        articleMapper.updateLikeByArticleId(articleId);
        return articleMapper.selectByPrimaryKey((int) articleId).getLikes();

    }

    @Override
    public Result getArticleManagement(int rows, int pageNum) {
        return null;
    }

    @Override
    public Article findArticleById(int id) {
        return null;
    }

    @Override
    public int countArticleCategoryByCategory(String category) {
        return 0;
    }

    @Override
    public int countArticleArchiveByArchive(String archive) {
        return 0;
    }

    @Override
    public int countArticle() {
        return 0;
    }

    @Override
    public int deleteArticle(long id) {
        return 0;
    }
}
