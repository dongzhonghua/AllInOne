package xyz.dsvshx.blog.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.Article;
import xyz.dsvshx.blog.entity.ArticleExample;
import xyz.dsvshx.blog.entity.ArticleWithBLOBs;
import xyz.dsvshx.blog.mapper.ArticleMapper;
import xyz.dsvshx.blog.service.*;
import xyz.dsvshx.blog.utils.Result;
import xyz.dsvshx.blog.utils.ResultUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
        int pageNum = Integer.parseInt(pageNo);
        int pageSize = Integer.parseInt(rows);
        PageHelper.startPage(pageNum, pageSize);
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("id desc");
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        List<ArticleWithBLOBs> articles = articleMapper.selectByExampleWithBLOBs(articleExample);
        PageInfo<ArticleWithBLOBs> pageInfo = new PageInfo<>(articles);
        List<Map<String, Object>> newArticles = new ArrayList<>();
        Map<String, Object> map;

        for (ArticleWithBLOBs article : articles) {
            map = new HashMap<>();
            map.put("thisArticleUrl", "/article/" + article.getId());
            map.put("articleTags", article.getArticletags());
            map.put("articleTitle", article.getArticletitle());
            map.put("articleType", article.getArticletype());
            map.put("publishDate", article.getPublishdate());
            map.put("originalAuthor", article.getOriginalauthor());
            map.put("articleCategories", article.getArticlecategories());
            map.put("articleTabloid", article.getArticletabloid());
            map.put("likes", article.getLikes());
            newArticles.add(map);
        }
        Map<String, Object> thisPageInfo = new HashMap<>();
        thisPageInfo.put("pageNum", pageInfo.getPageNum());
        thisPageInfo.put("pageSize", pageInfo.getPageSize());
        thisPageInfo.put("total", pageInfo.getTotal());
        thisPageInfo.put("pages", pageInfo.getPages());
        thisPageInfo.put("isFirstPage", pageInfo.isIsFirstPage());
        thisPageInfo.put("isLastPage", pageInfo.isIsLastPage());
        thisPageInfo.put("navigatepageNums", pageInfo.getNavigatepageNums());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articles",newArticles);
        jsonObject.put("pageInfo",thisPageInfo);
        return ResultUtil.success(jsonObject);

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
