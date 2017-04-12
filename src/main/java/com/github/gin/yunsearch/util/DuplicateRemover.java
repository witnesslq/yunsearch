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

    /**
     * 去除同一个多次分享的同名资源
     */
    @Scheduled(cron = "5/10 * * * * ?")
    public void removeDuplicate(){
        Set<YunData> duplicateDataSet = new HashSet<>();
        Set<YunData> uniqueDataSet = new HashSet<>();

        List<YunData> yunDatas = dataService.findBySingleShare();
        for(YunData data : yunDatas) {
            //如果已处理，则跳过
            if(duplicateDataSet.contains(data)
                    || uniqueDataSet.contains(data)) {
                continue;
            }
            //获取全部同一个人分享的文件
            List<YunData> duplicateDataList = dataService.findByUkAndShareName(data.getUk(),data.getShareName());

            //分类：最新的资源
            YunData uniqueData = duplicateDataList.remove(0);
            uniqueDataSet.add(uniqueData);

            //重复分享的同名资源
            duplicateDataSet.addAll(duplicateDataList);
        }

        for(YunData data : uniqueDataSet) {
            dataService.setSingleShare(data.getId());
        }

        dataService.delete(duplicateDataSet);

        yunDataEls.bulkDelete(duplicateDataSet);
    }
}
