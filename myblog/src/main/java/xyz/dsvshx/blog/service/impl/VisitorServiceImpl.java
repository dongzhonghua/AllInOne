package xyz.dsvshx.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dsvshx.blog.mapper.VisitorMapper;
import xyz.dsvshx.blog.service.VisitorService;
import xyz.dsvshx.blog.utils.RedisOperator;
import xyz.dsvshx.blog.utils.Result;
import xyz.dsvshx.blog.utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@Service

public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorMapper visitorMapper;
    @Autowired
    RedisOperator redisService;

    @Override
    public Result addVisitorNumByPageName(String pageName, HttpServletRequest request) {

        String visitor;
        Long pageVisitor = null;
        JSONObject jsonObject = new JSONObject();

        //visitor = (String) request.getSession().getAttribute(pageName);
        //if(visitor == null){
        //    //先去redis中查找
        //    pageVisitor = redisService.addVisitorNumOnRedis("visitor", pageName, 1);
        //    if(pageVisitor == null){
        //        //redis中未命中则从数据库中获得
        //        pageVisitor = visitorMapper.getVisitorNumByPageName(pageName);
        //        pageVisitor = redisService.putVisitorNumOnRedis("visitor", pageName, pageVisitor+1);
        //    }
        //    //在session中保存该用户访问页面的记录，在一段时间内重复访问时不增加在页面的访问人次
        //    request.getSession().setAttribute(pageName,"yes");
        //} else {
        //    pageVisitor = redisService.addVisitorNumOnRedis("visitor", pageName, 0);
        //    if(pageVisitor == null){
        //        //redis中未命中则从数据库中获得
        //        pageVisitor = visitorMapper.getVisitorNumByPageName(pageName);
        //        pageVisitor = redisService.putVisitorNumOnRedis("visitor", pageName, pageVisitor);
        //    }
        //}
        //
        ////增加总访问人数
        //Long totalVisitor = redisService.addVisitorNumOnRedis("visitor", "totalVisitor", 1);
        //if(totalVisitor == null){
        //    totalVisitor = visitorMapper.getTotalVisitor();
        //    totalVisitor = redisService.putVisitorNumOnRedis("visitor", "totalVisitor", totalVisitor+1);
        //}
        //
        //jsonObject.put("totalVisitor", totalVisitor);
        //jsonObject.put("pageVisitor", pageVisitor);
        return ResultUtil.success(jsonObject, "获得访客量成功");
    }

    @Override
    public long getNumByPageName(String pageName) {
        return visitorMapper.getVisitorNumByPageName(pageName);
    }

    @Override
    public void insertVisitorArticlePage(String pageName) {
        visitorMapper.insertVisitorArticlePage(pageName);
    }

    @Override
    public long getTotalVisitor() {
        return visitorMapper.getTotalVisitor();
    }

    @Override
    public void updateVisitorNumByPageName(String pageName, String visitorNum) {
        visitorMapper.updateVisitorNumByPageName(pageName, visitorNum);
    }
}
