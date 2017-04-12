package com.github.gin.yunsearch.agama.core;

import com.github.gin.agama.core.CrawlerContext;
import com.github.gin.agama.core.JCrawler;
import com.github.gin.agama.site.Request;
import com.github.gin.yunsearch.agama.Constant;
import com.github.gin.yunsearch.agama.YunRequestFactory;
import com.github.gin.yunsearch.agama.entity.YDataList;
import com.github.gin.yunsearch.agama.pipeline.YDataListPipeline;
import com.github.gin.yunsearch.agama.process.YDataListPageProcess;
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
public class YDataListCreeper {

    @Autowired
    private YDataListPipeline pipeline;
    @Autowired
    private YDataListPageProcess pageProcess;
    @Autowired
    private YunUserService yunUserService;
    @Value("${data.run}")
    private boolean runEnabled;

    @PostConstruct
    public void run(){
        if(!runEnabled) return;

        List<Request> requestList = new ArrayList<>();

        List<YunUser> yunUserList = yunUserService.findPubshareNeedCrawled();
        for(YunUser yunUser : yunUserList) {
            Request request = YunRequestFactory.create();
            request.setUrl(String.format(Constant.YUN_URL, yunUser.getUk(), 0));

            requestList.add(request);
        }

        CrawlerContext context = CrawlerContext.create()
                .persistBy(pipeline).processBy(pageProcess).build();

        JCrawler.create("WangPan")
                .crawl(requestList.toArray(new Request[requestList.size()]))
                .prey(YDataList.class)
                .context(context)
                .start();
    }
}
