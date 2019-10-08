package xyz.dsvshx.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import xyz.dsvshx.blog.component.JavaScriptCheck;
import xyz.dsvshx.blog.constant.SiteOwner;
import xyz.dsvshx.blog.entity.CommentLikesRecord;
import xyz.dsvshx.blog.entity.CommentRecord;
import xyz.dsvshx.blog.service.CommentLikesRecordService;
import xyz.dsvshx.blog.service.CommentService;
import xyz.dsvshx.blog.service.UserService;
import xyz.dsvshx.blog.utils.Result;
import xyz.dsvshx.blog.utils.ResultUtil;
import xyz.dsvshx.blog.utils.TimeUtil;

import java.security.Principal;

@RestController
@Api(value = "和评论相关")
@Slf4j
public class CommentController {

    @Autowired
    UserService userService;

    @Autowired
    CommentLikesRecordService commentLikesRecordService;
    @Autowired
    CommentService commentService;

    @PostMapping("/getAllComment")
    @ResponseBody
    public Result getAllComment(@RequestParam("articleId") Long articleId,
                                @AuthenticationPrincipal Principal principal) {

        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
        }
        return commentService.findCommentByArticleId(articleId, username);

    }

    @PostMapping("/publishComment")
    @ResponseBody
    public Result publishComment(CommentRecord comment,
                                 @AuthenticationPrincipal Principal principal) {
        String publisher;
        try {
            publisher = principal.getName();
        } catch (NullPointerException e) {
            log.error("no principal,please to login");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", 403);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            return ResultUtil.error(403,"",jsonArray);
        }
        TimeUtil timeUtil = new TimeUtil();
        comment.setCommentdate(timeUtil.getFormatDateForFive());
        int userId = userService.findIdByUsername(publisher);
        comment.setAnswererid(userId);
        comment.setRespondentid(userService.findIdByUsername(SiteOwner.SITE_OWNER));
        comment.setCommentcontent(JavaScriptCheck.javaScriptCheck(comment.getCommentcontent()));

        commentService.insertComment(comment);

        return commentService.findCommentByArticleId(comment.getArticleid(), publisher);

    }

    @PostMapping("/publishReply")
    public JSONArray publishReply(CommentRecord comment,
                                  @RequestParam("parentId") String parentId,
                                  @RequestParam("respondent") String respondent,
                                  @AuthenticationPrincipal Principal principal){

        String username = null;
        JSONArray jsonArray = new JSONArray();
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",403);
            jsonArray.add(jsonObject);
            return jsonArray;
        }

        comment.setPid(Long.parseLong(parentId.substring(1)));
        comment.setAnswererid(userService.findIdByUsername(username));
        comment.setRespondentid(userService.findIdByUsername(respondent));
        TimeUtil timeUtil = new TimeUtil();
        comment.setCommentdate(timeUtil.getFormatDateForFive());
        String commentContent = comment.getCommentcontent();
        //去掉评论中的@who
        if('@' == commentContent.charAt(0)){
            comment.setCommentcontent(commentContent.substring(respondent.length() + 1).trim());
        } else {
            comment.setCommentcontent(commentContent.trim());
        }
        //判断用户输入内容是否为空字符串
        if("".equals(comment.getCommentcontent())){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",400);
            jsonArray.add(jsonObject);
            return jsonArray;
        } else {
            //防止xss攻击
            comment.setCommentcontent(JavaScriptCheck.javaScriptCheck(comment.getCommentcontent()));
            comment = commentService.insertComment(comment);
        }
        jsonArray = (JSONArray) commentService.replyReplyReturn(comment, username, respondent).getData();
        return jsonArray;
    }

    @GetMapping("/isLogin")
    public int isLogin(@AuthenticationPrincipal Principal principal){
        String username;
        try {
            username = principal.getName();
            return 1;
        } catch (NullPointerException e){
            log.info("This user is not login");
            return 0;
        }
    }
    @GetMapping("/addCommentLike")
    public int addCommentLike(@RequestParam("articleId") String articleId,
                              @RequestParam("respondentId") String respondentId,
                              @AuthenticationPrincipal Principal principal){

        String username;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            log.info("This user is not login");
            return -1;
        }

        TimeUtil timeUtil = new TimeUtil();
        CommentLikesRecord commentLikesRecord = new CommentLikesRecord(Long.parseLong(articleId),
                Integer.parseInt(respondentId),userService.findIdByUsername(username),timeUtil.getFormatDateForFive());
        if(commentLikesRecordService.isLiked(commentLikesRecord.getArticleid(), commentLikesRecord.getPid(), username)){
            log.info("This user had clicked good for this article");
            return -2;
        }
        int likes = commentService.updateLikeByArticleIdAndId(commentLikesRecord.getArticleid(),commentLikesRecord.getPid());
        commentLikesRecordService.insertCommentLikesRecord(commentLikesRecord);
        return likes;
    }
}
