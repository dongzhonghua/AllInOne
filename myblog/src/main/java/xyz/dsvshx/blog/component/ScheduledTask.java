package xyz.dsvshx.blog.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.dsvshx.blog.service.VisitorService;
import xyz.dsvshx.blog.utils.RedisOperator;

import java.util.LinkedHashMap;

/**
 * @author: zhangocean
 * @Date: 2019/5/22 13:22
 * Describe: 定时任务
 */
@Component
public class ScheduledTask {

    @Autowired
    RedisOperator redisOperator;
    @Autowired
    VisitorService visitorService;

    /**
     * cron表达式生成器：http://cron.qqe2.com/
     *
     * 每晚20点清空redis中当日网站访问记录，但保存totalVisitor、visitorVolume、yesterdayVisitor
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void resetVisitorNumber(){
        //long oldTotalVisitor = visitorService.getTotalVisitor();
        //long newTotalVisitor = Long.valueOf(ScheduledTask.redisOperator.get("visitor", "totalVisitor").toString());
        //long yesterdayVisitor = newTotalVisitor - oldTotalVisitor;
        //if(ScheduledTask.redisOperator.hasHashKey("visitor", "yesterdayVisitor")){
        //    ScheduledTask.redisOperator.put("visitor", "yesterdayVisitor", yesterdayVisitor);
        //} else {
        //    ScheduledTask.redisOperator.put("visitor", "yesterdayVisitor", oldTotalVisitor);
        //}
        ////将redis中的所有访客记录更新到数据库中
        //LinkedHashMap map = (LinkedHashMap) ScheduledTask.redisOperator.getAllFieldAndValue("visitor");
        //String pageName;
        //for(Object e : map.keySet()){
        //    pageName = String.valueOf(e);
        //    visitorService.updateVisitorNumByPageName(pageName, String.valueOf(map.get(e)));
        //    if(!"totalVisitor".equals(pageName) && !"visitorVolume".equals(pageName) && !"yesterdayVisitor".equals(pageName)){
        //        ScheduledTask.redisOperator.hashDelete("visitor", pageName);
        //    }
        //}
    }

}
