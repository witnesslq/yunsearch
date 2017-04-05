package com.github.gin.yunsearch.agama.process;

import com.alibaba.fastjson.JSON;
import com.github.gin.agama.core.ContextHolder;
import com.github.gin.agama.processer.PageProcess;
import com.github.gin.agama.site.Page;
import com.github.gin.agama.site.Request;
import com.github.gin.yunsearch.agama.Constant;
import com.github.gin.yunsearch.agama.YunRequestFactory;
import com.github.gin.yunsearch.agama.entity.Fans;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.service.YunUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;

/**
 * @author GinPonson
 * @since 2017/4/2
 */
@Component
public class FansPageProcess implements PageProcess<Fans> {

    @Autowired
    private YunUserService yunUserService;

    @Override
    public void process(Page page, List<Fans> fansList) {
        Matcher m = Constant.USER_PATTERN.matcher(page.getUrl());
        if (m.find()) {
            long uk = Long.parseLong(m.group(1));
            int start = Integer.parseInt(m.group(3));

            //设置用户的订阅者是否获取完毕
            int fansCount = JSON.parseObject(page.getRawText()).getInteger("total_count");
            if ((start + Constant.LIMIT) >= fansCount) {
                yunUserService.setFansCrawledComplete(uk);

                List<YunUser> yunUserList = yunUserService.findFansNeedCrawle();
                System.out.println("fans :" + yunUserList.size());
                for(YunUser yunUser : yunUserList) {
                    Request request = YunRequestFactory.create();
                    request.setUrl(String.format(Constant.FANS_URL, yunUser.getUk(), Constant.LIMIT, 0));

                    ContextHolder.getContext().getScheduler().push(request);
                }

            } else {
                Request request = YunRequestFactory.create();
                request.setUrl(String.format(Constant.FANS_URL, uk, Constant.LIMIT, start + Constant.LIMIT));
                ContextHolder.getContext().getScheduler().push(request);
            }
        }
    }
}
