package com.github.gin.yunsearch.util;

import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.service.els.YunDataEls;
import com.github.gin.yunsearch.service.jpa.YunDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by FSTMP on 2017/4/12.
 */
@Component
public class DuplicateRemover {

    @Autowired
    private YunDataService dataService;
    @Autowired
    private YunDataEls yunDataEls;

    @Scheduled(cron = "5/10 * * * * ?")
    public void removeDuplicate(){
        Set<YunData> duplicateDataSet = new HashSet<>();

        List<YunData> yunDatas = dataService.findBySingleShare();
        for(YunData data : yunDatas) {
            List<YunData> duplicateDataList = dataService.findByUkAndShareName(data.getUk(),data.getShareName());

            duplicateDataList.remove(0);
            duplicateDataSet.addAll(duplicateDataList);
        }

        dataService.delete(duplicateDataSet);

        yunDataEls.bulkDelete(duplicateDataSet);
    }
}
