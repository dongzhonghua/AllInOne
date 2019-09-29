package xyz.dsvshx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.ArticleLikesRecordExample;
import xyz.dsvshx.blog.service.ArticleLikesRecordService;
import xyz.dsvshx.blog.entity.ArticleLikesRecord;
import xyz.dsvshx.blog.mapper.ArticleLikesRecordMapper;
import xyz.dsvshx.blog.service.ArticleService;
import xyz.dsvshx.blog.service.UserService;
import xyz.dsvshx.blog.utils.Result;

@Service

public class ArticleLikesRecordServiceImpl implements ArticleLikesRecordService {
    @Autowired
    ArticleLikesRecordMapper articleLikesMapper;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    @Override
    public boolean isLiked(long articleId, String username) {
        ArticleLikesRecordExample articleLikesRecordExample = new ArticleLikesRecordExample();
        ArticleLikesRecordExample.Criteria criteria = articleLikesRecordExample.createCriteria();
        criteria.andArticleidEqualTo(articleId).andLikeridEqualTo(userService.findIdByUsername(username));
        int i = articleLikesMapper.countByExample(articleLikesRecordExample);
        return i != 0;
    }

    @Override
    public void insertArticleLikesRecord(ArticleLikesRecord articleLikesRecord) {
        articleLikesMapper.insert(articleLikesRecord);
    }

    @Override
    public void deleteArticleLikesRecordByArticleId(long articleId) {
        articleLikesMapper.deleteByPrimaryKey((int) articleId);
    }

    @Override
    public Result getArticleThumbsUp(String username, int rows, int pageNum) {
        return null;
    }

    @Override
    public int readThisThumbsUp(int id) {
        return 0;
    }

    @Override
    public Result readAllThumbsUp() {
        return null;
    }
}
