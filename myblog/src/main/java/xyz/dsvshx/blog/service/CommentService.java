package xyz.dsvshx.blog.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.dsvshx.blog.entity.CommentRecord;
import xyz.dsvshx.blog.utils.Result;

public interface CommentService {
    @Transactional
    CommentRecord insertComment(CommentRecord commentRecord);

    Result findCommentByArticleId(long articleId, String username);

    Result findReplyByArticleIdAndPid(long articleId, long pId);

    Result replyReplyReturn(CommentRecord comment, String answerer, String respondent);
    int updateLikeByArticleIdAndId(long articleId, long pId);
    Result getUserComment(int rows, int pageNum, String username);
    int commentNum();
    void deleteCommentByArticleId(long articleId);
    int readOneCommentRecord(int id);
    Result readAllComment(String username);


}
