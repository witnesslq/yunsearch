package com.github.gin.yunsearch.agama.pipeline;

import com.github.gin.agama.pipeline.Pipeline;
import com.github.gin.yunsearch.agama.entity.Follow;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.service.jpa.YunUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author GinPonson
 * @since 2017/4/2
 */
@Component
public class FollowPipeline implements Pipeline<Follow> {

    @Autowired
    private YunUserService userService;

    @Override
    public void process(List<Follow> entityList) {
        for (Follow follow : entityList) {
            YunUser user = new YunUser();
            BeanUtils.copyProperties(follow, user);
            user.setVersion(1);
            user.setUpdateTime(new Date());

            userService.saveIgnore(user);
        }
    }
}
