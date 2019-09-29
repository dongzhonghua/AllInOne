package xyz.dsvshx.blog.service;

import io.swagger.models.auth.In;
import xyz.dsvshx.blog.entity.ArticleLikesRecord;
import xyz.dsvshx.blog.utils.Result;

public interface ArticleLikesRecordService {
    boolean isLiked(long articleId, String username);
    void insertArticleLikesRecord(ArticleLikesRecord articleLikesRecord);
    void deleteArticleLikesRecordByArticleId(long articleId);
    Result getArticleThumbsUp(String username, int rows, int pageNum);
    int readThisThumbsUp(int id);
    Result readAllThumbsUp();

}
