package xyz.dsvshx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.CommentLikesRecord;
import xyz.dsvshx.blog.entity.CommentLikesRecordExample;
import xyz.dsvshx.blog.mapper.CommentLikesRecordMapper;
import xyz.dsvshx.blog.service.CommentLikesRecordService;
import xyz.dsvshx.blog.service.UserService;

@Service

public class CommentLikesRecordServiceImpl implements CommentLikesRecordService {
    @Autowired
    UserService userService;
    @Autowired
    CommentLikesRecordMapper commentLikesRecordMapper;

    @Override
    public boolean isLiked(long articleId, Integer id, String username) {
        CommentLikesRecordExample commentLikesRecordExample = new CommentLikesRecordExample();
        CommentLikesRecordExample.Criteria criteria = commentLikesRecordExample.createCriteria();
        criteria.andArticleidEqualTo(articleId)
                .andPidEqualTo(Math.toIntExact(id))
                .andLikeridEqualTo(userService.findIdByUsername(username));
        return commentLikesRecordMapper.selectByExample(commentLikesRecordExample).size() != 0;
    }

    @Override
    public void insertCommentLikesRecord(CommentLikesRecord commentLikesRecord) {
        commentLikesRecordMapper.insert(commentLikesRecord);
    }

    @Override
    public void deleteCommentLikesRecordByArticleId(long articleId) {
        CommentLikesRecordExample commentLikesRecordExample = new CommentLikesRecordExample();
        CommentLikesRecordExample.Criteria criteria = commentLikesRecordExample.createCriteria();
        criteria.andArticleidEqualTo(articleId);
        commentLikesRecordMapper.deleteByExample(commentLikesRecordExample);
    }
}
