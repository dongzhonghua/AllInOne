package xyz.dsvshx.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.dsvshx.blog.component.StringAndArray;
import xyz.dsvshx.blog.entity.Article;
import xyz.dsvshx.blog.entity.ArticleWithBLOBs;
import xyz.dsvshx.blog.service.*;
import xyz.dsvshx.blog.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

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
    @Autowired
    TagService tagService;

    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/myArticles")
    public Result myArticles(@RequestParam("rows") String rows,
                             @RequestParam("pageNum") String pageNum) {

        return articleService.findAllArticles(rows, pageNum);

    }


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

    @PostMapping("/publishArticle")
    public Result publishArticle(@AuthenticationPrincipal Principal principal,
                                 ArticleWithBLOBs article,
                                 @RequestParam("articleGrade") String articleGrade,
                                 HttpServletRequest request) {

        String username = null;
        JSONObject returnJson = new JSONObject();
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            //登录超时情况
            log.error("This user is not login，publish article 《" + article.getArticletitle() + "》 fail");
            returnJson.put("status", 403);
            request.getSession().setAttribute("article", article);
            request.getSession().setAttribute("articleGrade", articleGrade);
            request.getSession().setAttribute("articleTags", request.getParameterValues("articleTagsValue"));
            return ResultUtil.success(returnJson);
        }

        String phone = userService.findUserByusername(username).getPhone();
        if (!userService.isSuperAdmin(phone)) {
            returnJson.put("status", 500);
            return ResultUtil.success(returnJson);

        }

        //获得文章html代码并生成摘要
        BuildArticleTabloidUtil buildArticleTabloidUtil = new BuildArticleTabloidUtil();
        String articleHtmlContent = buildArticleTabloidUtil.buildArticleTabloid(request.getParameter("articleHtmlContent"));
        article.setArticletabloid(articleHtmlContent + "...");

        String[] articleTags = request.getParameterValues("articleTagsValue");
        String[] tags = new String[articleTags.length + 1];
        for (int i = 0; i < articleTags.length; i++) {
            //去掉可能出现的换行符
            articleTags[i] = articleTags[i].replaceAll("<br>", "").replaceAll("&nbsp;", "").replaceAll("\\s+", "").trim();
            tags[i] = articleTags[i];
        }
        tags[articleTags.length] = article.getArticletype();
        //添加标签
        tagService.addTags(tags, Integer.parseInt(articleGrade));
        TimeUtil timeUtil = new TimeUtil();
        String id = request.getParameter("id");
        //修改文章
        if (!"".equals(id) && id != null) {
            String updateDate = timeUtil.getFormatDateForThree();
            article.setArticletags(StringAndArray.arrayToString(tags));
            article.setUpdatedate(updateDate);
            article.setId(Integer.parseInt(id));
            article.setAuthor(username);
            return articleService.updateArticleById(article);
        }

        String nowDate = timeUtil.getFormatDateForThree();
        long articleId = timeUtil.getLongTime();

        article.setArticleid(articleId);
        article.setAuthor(username);
        article.setArticletags(StringAndArray.arrayToString(tags));
        article.setPublishdate(nowDate);
        article.setUpdatedate(nowDate);

        return articleService.insertArticle(article);
    }

    @GetMapping("/canYouWrite")
    public int canYouWrite(@AuthenticationPrincipal Principal principal) {

        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            log.info("This user is not login");
        }
        String phone = userService.findUserByusername(username).getPhone();
        if (userService.isSuperAdmin(phone)) {
            return 1;
        }
        return 0;
    }

    @GetMapping("/findCategoriesName")
    @ResponseBody
    public JSONArray findCategoriesName() {
        return categoriesService.findCategoriesName();
    }

    @GetMapping("/getDraftArticle")
    @ResponseBody
    public JSONObject getDraftArticle(HttpServletRequest request) {
        JSONObject returnJson = new JSONObject();
        String id = (String) request.getSession().getAttribute("id");

        //判断是否为修改文章
        if (id != null) {
            request.getSession().removeAttribute("id");
            returnJson.put("status", 201);
            Article article = articleService.findArticleById(Integer.parseInt(id));
            int lastItem = article.getArticletags().lastIndexOf(',');
            String[] articleTags = StringAndArray.stringToArray(article.getArticletags().substring(0, lastItem));
            returnJson.put("result", articleService.getDraftArticle(article, articleTags, tagService.getTagsSizeByTagName(articleTags[0])));
            return returnJson;
        }
        //判断是否为写文章登录超时
        if (request.getSession().getAttribute("article") != null) {
            returnJson.put("status", 201);
            Article article = (Article) request.getSession().getAttribute("article");
            String[] articleTags = (String[]) request.getSession().getAttribute("articleTags");
            String articleGrade = (String) request.getSession().getAttribute("articleGrade");
            returnJson.put("result", articleService.getDraftArticle(article, articleTags, Integer.parseInt(articleGrade)));
            request.getSession().removeAttribute("article");
            request.getSession().removeAttribute("articleTags");
            request.getSession().removeAttribute("articleGrade");
            return returnJson;
        }
        returnJson.put("status", 200);
        return returnJson;
    }

    @RequestMapping("/uploadImage")
    public @ResponseBody
    Map<String, Object> uploadImage(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        return null;
    }
}

