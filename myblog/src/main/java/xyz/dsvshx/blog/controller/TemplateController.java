package xyz.dsvshx.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TemplateController {

    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = "login")
    public String login(){
        return "login";
    }
    @GetMapping("/fileUpload")
    public String fileUpload(){
        return "fileUpload";
    }

    @GetMapping("/video")
    public String video(){
        return "video";
    }

    @GetMapping("/editor")
    public String editor(){
        return "editor";
    }

    @GetMapping("/article/{articleId}")
    public String show(@PathVariable("articleId") long articleid,
                       HttpServletResponse response,
                       Model model,
                       HttpServletRequest request) {
        response.setHeader("articleId",String.valueOf(articleid));
        return "show";
    }
}
