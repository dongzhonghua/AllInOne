package xyz.dsvhsx.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dsvhsx.blog.utils.RedisOperator;

@RestController
@RequestMapping("/")
public class RedisController {
    @Autowired
    private RedisOperator redisOperator;

    @RequestMapping("/hello")
    public String redisTest(){
        redisOperator.set("abcd","abcd");

        return redisOperator.get("abcd")+"success";
    }

    @RequestMapping("/mysql")
    public String mysqlTest(){

    }
}
