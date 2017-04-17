package com.github.gin.yunsearch.util;

import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.service.els.YunDataEls;
import com.github.gin.yunsearch.service.jpa.YunDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by FSTMP on 2016/11/21.
 */
@Component
public class ESSyner {

    @Autowired
    private YunDataService yunDataService;
    @Autowired
    private YunDataEls yunDataEls;

    @Scheduled(cron = "10/20 * * * * ?")
    public void mysql2Elastic() throws IOException {
        List<YunData> yunDatas = null;
        try {
            //如果elasticsearch没有该index，将自动创建
            YunData yunData = yunDataEls.getLastData();
            yunDatas = yunDataService.findByUpdateTime(yunData.getUpdateTime());
        } catch (Exception e) {
            yunDatas = yunDataService.findAll();
        }

        yunDataEls.bulkSave(removeDuplicate(yunDatas));
    }

    private Set<YunData> removeDuplicate(List<YunData> yunDatas) {
        Set<YunData> duplicateDataSet = new HashSet<>();
        Set<YunData> uniqueDataSet = new HashSet<>();

        for (YunData data : yunDatas) {
            //如果已处理，则跳过
            if (duplicateDataSet.contains(data)
                    || uniqueDataSet.contains(data)) {
                continue;
            }
            //获取全部同一个人分享的文件
            List<YunData> duplicateDataList = yunDataService.findByUkAndShareName(data.getUk(), data.getShareName());

            //分类：最新的资源
            YunData uniqueData = duplicateDataList.remove(0);
            uniqueDataSet.add(uniqueData);

            //重复分享的同名资源
            duplicateDataSet.addAll(duplicateDataList);
        }

        for (YunData data : uniqueDataSet) {
            if(!data.isSingleShare())
                yunDataService.setSingleShare(data.getId());
        }

        yunDataService.delete(duplicateDataSet);

        return uniqueDataSet;
    }

}
