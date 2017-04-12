package com.github.gin.yunsearch.agama.core;

import com.github.gin.agama.core.CrawlerConfig;
import com.github.gin.agama.core.CrawlerContext;
import com.github.gin.agama.core.JCrawler;
import com.github.gin.agama.site.Request;
import com.github.gin.yunsearch.agama.Constant;
import com.github.gin.yunsearch.agama.YunRequestFactory;
import com.github.gin.yunsearch.agama.entity.Follow;
import com.github.gin.yunsearch.agama.pipeline.FollowPipeline;
import com.github.gin.yunsearch.agama.process.FollowPageProcess;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.service.jpa.YunUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GinPonson
 * @since 2017/4/2
 */
@Component
public class FollowCreeper {

    @Autowired
    private FollowPipeline pipeline;
    @Autowired
    private FollowPageProcess pageProcess;
    @Autowired
    private YunUserService yunUserService;
    @Value("${follow.run}")
    private boolean runEnabled;

    @PostConstruct
    public void run() throws InterruptedException {
        if(!runEnabled) return;

        List<Request> requestList = new ArrayList<>();

        List<YunUser> yunUserList = yunUserService.findFollowNeedCrawled();
        for(YunUser yunUser : yunUserList) {
            Request request = YunRequestFactory.create();
            //获取用户订阅者的链接
            request.setUrl(String.format(Constant.FOLLOW_URL, yunUser.getUk(), Constant.LIMIT, 0));

            requestList.add(request);
        }


        CrawlerContext context = CrawlerContext.create()
                .persistBy(pipeline).processBy(pageProcess)
                .useConfig(new CrawlerConfig().setThreadNum(1).setInterval(10000))
                .build();

        Thread.sleep(15000);
        JCrawler.create("Follow")
                .crawl(requestList.toArray(new Request[requestList.size()]))
                .prey(Follow.class)
                .context(context)
                .start();
    }
}
