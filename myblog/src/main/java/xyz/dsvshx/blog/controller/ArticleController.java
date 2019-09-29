package xyz.dsvshx.blog.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.dsvshx.blog.service.ArticleLikesRecordService;
import xyz.dsvshx.blog.entity.Article;
import xyz.dsvshx.blog.service.ArticleService;
import xyz.dsvshx.blog.service.UserService;
import xyz.dsvshx.blog.utils.RedisOperator;
import xyz.dsvshx.blog.utils.Result;
import xyz.dsvshx.blog.utils.ResultUtil;

import java.security.Principal;

@RestController
@Slf4j
@Api(value = "和文章相关的")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleLikesRecordService articleLikesRecordService;
    @Autowired
    UserService userService;
    @Autowired
    RedisOperator redisOperator;

    @PostMapping("/getArticleByArticleId")
    public Result getArticleByArticleId(@RequestParam("articleId") String articleId,
                                        @AuthenticationPrincipal Principal principal) {
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            log.info("this user is not login");
        }
        Article article = articleService.getArticleByArticleId(Integer.valueOf(articleId), username);
        return ResultUtil.success(article);
    }

    @GetMapping("/addArticleLike")
    public int addArticleLike(@RequestParam("articleId") String articleId,
                              @AuthenticationPrincipal Principal principal) {
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            log.error("username " + username + " is not login");
            return -1;
        }
        if (articleLikesRecordService.isLiked(Long.parseLong(articleId), username)) {
            log.info("点过赞了");
            return -2;
        }
        int likes = articleService.updateLikeByArticleId(Long.parseLong(articleId));
        return 0;
    }
}
