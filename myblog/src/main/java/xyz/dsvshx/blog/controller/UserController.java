package xyz.dsvshx.blog.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.mapper.UserMapper;
import xyz.dsvshx.blog.service.UserService;
import xyz.dsvshx.blog.utils.MD5Util;
import xyz.dsvshx.blog.utils.RedisOperator;
import xyz.dsvshx.blog.utils.Result;
import xyz.dsvshx.blog.utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api("UserController")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisOperator redisOperator;

    @PostMapping("/register")
    public String register(User user,
                           HttpServletRequest request) {

        String authCode = request.getParameter("authCode");

        String trueMsgCode = redisOperator.get(user.getPhone());

        //判断手机号是否正确
        if (trueMsgCode == null) {
            return "5";
        }
        //判断验证码是否正确
        if (!authCode.equals(trueMsgCode)) {
            return "0";
        }
        //判断用户名是否存在
        if(userService.findIdByUsername(user.getUsername())==0){
            return "3";
        }
        //注册时对密码进行MD5加密
        MD5Util md5Util = new MD5Util();
        user.setPassword(md5Util.encode(user.getPassword()));
        return userService.insert(user);

}

    @ApiOperation(value = "getUserByPhone")
    @GetMapping("/getUserByPhone")
    public User getUserByPhone() {
        return userMapper.getUsernameAndRolesByPhone("19940790216");

        //return userService.findUserByPhone("19940790216");

    }

    @ApiOperation(value = "上传文件")

    @PostMapping("/uploadFile")
    public JSONObject uploadFile() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    @PostMapping("/uploadHead")
    @ApiOperation(value = "上传头像")

    public JSONObject uploadHead() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    @GetMapping("/getheadportraiturl")
    @ApiOperation(value = "上传头像")
    public JSONObject getHeadPortraitUrl(@AuthenticationPrincipal Principal principal) {
        String username = principal.getName();
        return userService.getHeadPortraitUrl(userService.findIdByUsername(username));
    }

    @ApiOperation(value = "获得个人信息")
    @PostMapping("/getUserPersonalInfo")
    public Result getUserPersonalInfo(@AuthenticationPrincipal Principal principal) {
        String username = principal.getName();
        return userService.getUserPersonalInfoByUsername(username);
    }

    @ApiOperation(value = "保存个人信息")
    @PostMapping("/savePersonalDate")
    public Result savePersonalDate(User user,
                                   @AuthenticationPrincipal Principal principal) {

        String username;
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            return ResultUtil.success(403, "not log in");
        }
        return userService.savePersonalDate(user, username);
    }

}
