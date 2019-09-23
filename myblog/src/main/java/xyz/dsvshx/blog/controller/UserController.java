package xyz.dsvshx.blog.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.service.UserService;

@Api("UserController")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "getUserByPhone")
    @GetMapping("/getUserByPhone")
    public User getUserByPhone(){
        return userService.findUserByPhone("19940790216");

    }
}
