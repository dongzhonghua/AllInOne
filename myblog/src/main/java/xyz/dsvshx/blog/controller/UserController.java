package xyz.dsvshx.blog.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dsvshx.blog.entity.SecurityUser;
import xyz.dsvshx.blog.entity.User;
import xyz.dsvshx.blog.mapper.SecurityUserMapper;
import xyz.dsvshx.blog.mapper.UserMapper;
import xyz.dsvshx.blog.service.UserService;

@Api("UserController")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;
    @ApiOperation(value = "getUserByPhone")
    @GetMapping("/getUserByPhone")
    public User getUserByPhone(){
        return userMapper.getUsernameAndRolesByPhone("19940790216");

        //return userService.findUserByPhone("19940790216");

    }


}
