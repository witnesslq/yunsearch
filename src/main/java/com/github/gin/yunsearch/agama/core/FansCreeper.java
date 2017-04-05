package com.github.gin.yunsearch.agama.core;

import com.github.gin.agama.core.CrawlerConfig;
import com.github.gin.agama.core.CrawlerContext;
import com.github.gin.agama.core.JCrawler;
import com.github.gin.agama.site.Request;
import com.github.gin.yunsearch.agama.Constant;
import com.github.gin.yunsearch.agama.YunRequestFactory;
import com.github.gin.yunsearch.agama.entity.Fans;
import com.github.gin.yunsearch.agama.pipeline.FansPipeline;
import com.github.gin.yunsearch.agama.process.FansPageProcess;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.service.YunUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GinPonson
 * @since 2017/4/2
 */
@Component
public class FansCreeper {

    @Autowired
    private FansPipeline pipeline;
    @Autowired
    private FansPageProcess pageProcess;
    @Autowired
    private YunUserService yunUserService;

    @PostConstruct
    public void run(){
        List<Request> requestList = new ArrayList<>();

        List<YunUser> yunUserList = yunUserService.findFansNeedCrawle();
        for (YunUser yunUser : yunUserList){
            Request request = YunRequestFactory.create();
            //获取用户订阅者的链接
            request.setUrl(String.format(Constant.FANS_URL, yunUser.getUk(), Constant.LIMIT, 0));
            requestList.add(request);
        }

        CrawlerContext context = CrawlerContext.create()
                .persistBy(pipeline).processBy(pageProcess)
                .useConfig(new CrawlerConfig().setThreadNum(1).setInterval(10000))
                .build();

        JCrawler.create()
                .crawl(requestList.toArray(new Request[requestList.size()]))
                .prey(Fans.class)
                .context(context)
                .start();
    }
}
