package xyz.dsvshx.blog.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.entity.CommentRecord;
import xyz.dsvshx.blog.entity.CommentRecordExample;
import xyz.dsvshx.blog.mapper.CommentRecordMapper;
import xyz.dsvshx.blog.service.ArticleService;
import xyz.dsvshx.blog.service.CommentLikesRecordService;
import xyz.dsvshx.blog.service.CommentService;
import xyz.dsvshx.blog.service.UserService;
import xyz.dsvshx.blog.utils.RedisOperator;
import xyz.dsvshx.blog.utils.Result;
import xyz.dsvshx.blog.utils.ResultUtil;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRecordMapper commentRecordMapper;
    @Autowired
    CommentLikesRecordService commentLikesRecordService;
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;
    @Autowired
    RedisOperator redisOperator;

    @Override
    public CommentRecord insertComment(CommentRecord commentRecord) {
        if (commentRecord.getAnswererid() == commentRecord.getRespondentid()) {
            commentRecord.setIsread(Boolean.FALSE);
        }
        commentRecordMapper.insert(commentRecord);
        //在redis中保村该用户的未读消息
        addNotReadNews(commentRecord);
        return commentRecord;
    }

    private void addNotReadNews(CommentRecord commentRecord) {
        if (commentRecord.getRespondentid() != commentRecord.getAnswererid()) {
            boolean haskey = redisOperator.haskey(commentRecord.getRespondentid() + "");
            if (!haskey) {
                //    还未完成
            }
        }
    }

    @Override
    public Result findCommentByArticleId(long articleId, String username) {
        JSONArray commentJsonArray = new JSONArray();
        JSONArray replyJsonArray;
        JSONObject commentJsonObject = new JSONObject();
        JSONObject replyJsonObject;
        List<CommentRecord> replyLists;
        JSONObject jsonObject = new JSONObject();
        CommentRecordExample commentRecordExample = new CommentRecordExample();
        CommentRecordExample.Criteria criteria = commentRecordExample.createCriteria();
        criteria.andArticleidEqualTo(articleId).andPidEqualTo(0L);
        commentRecordExample.setOrderByClause("id desc");
        List<CommentRecord> commentRecordList = commentRecordMapper.selectByExampleWithBLOBs(commentRecordExample);
        log.info(commentRecordList.toString());
        for (CommentRecord comment : commentRecordList) {
            CommentRecordExample commentRecordExample2 = new CommentRecordExample();
            CommentRecordExample.Criteria criteria2 = commentRecordExample2.createCriteria();
            criteria2.andArticleidEqualTo(articleId);
            criteria2.andPidEqualTo(comment.getId());
            replyLists = commentRecordMapper.selectByExampleWithBLOBs(commentRecordExample2);
            replyJsonArray = new JSONArray();
            for (CommentRecord reply : replyLists) {
                replyJsonObject = new JSONObject();
                replyJsonObject.put("id", reply.getId());
                replyJsonObject.put("answerer", userService.findUsernameByid(reply.getAnswererid()));
                replyJsonObject.put("commentdate", reply.getCommentdate());
                replyJsonObject.put("commentcontent", reply.getCommentcontent());
                replyJsonObject.put("respondent", userService.findUsernameByid(reply.getRespondentid()));
                replyJsonArray.add(replyJsonObject);
            }
            //封装评论
            commentJsonObject.put("id", comment.getId());
            commentJsonObject.put("answerer", userService.findUsernameByid(comment.getAnswererid()));
            commentJsonObject.put("commentdate", comment.getCommentdate());
            commentJsonObject.put("likes", comment.getLikes());
            commentJsonObject.put("commentcontent", comment.getCommentcontent());
            commentJsonObject.put("replies", replyJsonArray);
            commentJsonObject.put("avatarImgUrl", userService.getHeadPortraitUrl(comment.getAnswererid()));

            if (username == null) {
                commentJsonObject.put("isLiked", 0);
            } else {
                if (commentLikesRecordService.isLiked(articleId, Math.toIntExact(comment.getId()), username)) {
                    commentJsonObject.put("isLiked", 1);
                } else {
                    commentJsonObject.put("isLiked", 0);
                }
            }
            commentJsonArray.add(commentJsonObject);
        }
        Result success = ResultUtil.success(commentJsonObject);

        return success;
    }

    @Override
    public Result findReplyByArticleIdAndPid(long articleId, long pId) {
        CommentRecordExample commentRecordExample = new CommentRecordExample();
        CommentRecordExample.Criteria criteria = commentRecordExample.createCriteria();
        criteria.andArticleidEqualTo(articleId).andPidEqualTo(0L);
        List<CommentRecord> commentList = commentRecordMapper.selectByExample(commentRecordExample);
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        for (CommentRecord comment : commentList) {
            jsonObject = new JSONObject();
            jsonObject.put("answerer", userService.findUsernameByid(comment.getAnswererid()));
            jsonObject.put("respondent", userService.findUsernameByid(comment.getRespondentid()));
            jsonObject.put("commentcontent", comment.getCommentcontent());
            jsonObject.put("commentdate", comment.getCommentdate());
            jsonArray.add(jsonObject);
        }
        Result success = ResultUtil.success(jsonArray);

        return success;
    }

    @Override
    public Result replyReplyReturn(CommentRecord comment, String answerer, String respondent) {
        JSONObject jsonObject1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject1.put("answerer", answerer);
        jsonObject1.put("respondent", respondent);
        jsonObject1.put("commentContent", comment.getCommentcontent());
        jsonObject1.put("commentDate", comment.getCommentdate());

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("status", 200);

        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);

        return ResultUtil.success(jsonArray);
    }

    @Override
    public int updateLikeByArticleIdAndId(long articleId, long pId) {
        CommentRecordExample commentRecordExample = new CommentRecordExample();
        CommentRecordExample.Criteria criteria = commentRecordExample.createCriteria();
        criteria.andArticleidEqualTo(articleId).andPidEqualTo(pId);
        CommentRecord commentRecord = commentRecordMapper.selectByExample(commentRecordExample).get(0);
        commentRecord.setLikes(commentRecord.getLikes() + 1);
        commentRecordMapper.updateByPrimaryKey(commentRecord);
        return commentRecordMapper.selectByExample(commentRecordExample).get(0).getLikes();
    }

    @Override
    public Result getUserComment(int rows, int pageNum, String username) {
        int userId = userService.findIdByUsername(username);
        PageHelper.startPage(pageNum, rows);
        CommentRecordExample commentRecordExample = new CommentRecordExample();
        CommentRecordExample.Criteria criteria = commentRecordExample.createCriteria();
        criteria.andRespondentidEqualTo(userId);
        List<CommentRecord> comments = commentRecordMapper.selectByExample(commentRecordExample);
        PageInfo<CommentRecord> pageInfo = new PageInfo<>(comments);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("comments", comments);
        jsonObject.put("pageInfo", pageInfo);

        return ResultUtil.success(jsonObject);
    }

    @Override
    public int commentNum() {
        return commentRecordMapper.commentNum();
    }

    @Override
    public void deleteCommentByArticleId(long articleId) {
        commentRecordMapper.deleteCommentByArticleId(articleId);
    }

    @Override
    public int readOneCommentRecord(int id) {
        try {
            commentRecordMapper.readCommentRecordById(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Result readAllComment(String username) {
        int respondentId = userService.findIdByUsername(username);
        commentRecordMapper.readCommentRecordByRespondentId(respondentId);
        return ResultUtil.success("success");
    }
}
