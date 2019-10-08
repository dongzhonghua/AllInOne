package xyz.dsvshx.blog.service;

import xyz.dsvshx.blog.entity.CommentLikesRecord;

public interface CommentLikesRecordService {
    boolean isLiked(long articleId, Integer id, String username);
    void insertCommentLikesRecord(CommentLikesRecord commentLikesRecord);
    void deleteCommentLikesRecordByArticleId(long articleId);

}
