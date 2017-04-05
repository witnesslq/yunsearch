package com.github.gin.yunsearch.agama.pipeline;

import com.github.gin.agama.pipeline.Pipeline;
import com.github.gin.yunsearch.agama.entity.Fans;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.service.YunUserService;
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
public class FansPipeline implements Pipeline<Fans> {

    @Autowired
    private YunUserService userService;

    @Override
    public void process(List<Fans> entityList) {
        for (Fans fans : entityList) {
            YunUser user = new YunUser();
            BeanUtils.copyProperties(fans, user);
            user.setUpdateTime(new Date());
            user.setVersion(1);
            userService.saveIgnore(user);
        }
    }
}
