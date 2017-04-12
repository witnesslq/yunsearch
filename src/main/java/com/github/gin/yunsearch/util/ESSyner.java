package com.github.gin.yunsearch.util;

import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.service.els.YunDataEls;
import com.github.gin.yunsearch.service.jpa.YunDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by FSTMP on 2016/11/21.
 */
@Component
public class ESSyner {

    @Autowired
    private YunDataService yunDataService;
    @Autowired
    private YunDataEls yunDataEls;

    @Scheduled(cron = "5/10 * * * * ?")
    public void mysql2Elastic() throws IOException {
        List<YunData> yunDatas = null;
        try {
            //如果elasticsearch没有该index，将自动创建
            YunData yunData = yunDataEls.getLastData();
            yunDatas = yunDataService.findByUpdateTime(yunData.getUpdateTime());
        } catch (Exception e) {
            yunDatas = yunDataService.findAll();
        }

        yunDataEls.bulkSave(yunDatas);
    }

}
