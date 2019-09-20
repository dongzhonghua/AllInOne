package xzy.dsvshx.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xzy.dsvshx.redis.utils.RedisOperator;

@RestController
@RequestMapping("/")
public class RedisController {
    @Autowired
    private RedisOperator redisOperator;

    @RequestMapping("/hello")
    public String redisTest(){
        redisOperator.set("abcd","abcd");
        return redisOperator.get("abcd");
    }
}
