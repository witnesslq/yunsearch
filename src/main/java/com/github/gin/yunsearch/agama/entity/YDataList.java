package com.github.gin.yunsearch.agama.entity;

import com.github.gin.agama.annotation.JS;
import com.github.gin.agama.annotation.Prey;
import com.github.gin.agama.site.entity.XpathEntity;

import java.util.List;

/**
 * @author GinPonson
 */
@Prey(matchUrl = "http://pan.baidu.com/wap/share/home")
public class YDataList extends XpathEntity{
    @JS(var = "window",jsonpath = "$.yunData.feedata.records")
    private List<YData> yDatas ;

    @JS(var = "window",jsonpath = "$.yunData.feedata.total_count")
    private Integer totalCount;

    public List<YData> getYDatas() {
        return yDatas;
    }

    public void setYDatas(List<YData> yDatas) {
        this.yDatas = yDatas;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
