package com.github.gin.yunsearch.agama.pipeline;

import com.github.gin.agama.pipeline.Pipeline;
import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.service.jpa.YunDataService;
import com.github.gin.yunsearch.agama.entity.YData;
import com.github.gin.yunsearch.agama.entity.YDataList;
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
public class YDataListPipeline implements Pipeline<YDataList> {

    @Autowired
    private YunDataService yunDataService;

    @Override
    public void process(List<YDataList> entityList) {
        for(YDataList yDataList : entityList) {
            for (YData data : yDataList.getYDatas()) {
                YunData yunData = new YunData();
                BeanUtils.copyProperties(data,yunData);
                yunData.setUpdateTime(new Date());
                yunData.setVersion(1);
                yunDataService.saveIgnore(yunData);
            }
        }
    }
}
