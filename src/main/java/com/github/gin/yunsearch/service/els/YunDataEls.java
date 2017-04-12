package com.github.gin.yunsearch.service.els;

import com.alibaba.fastjson.JSON;
import com.github.gin.yunsearch.model.YunData;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by GinPonson on 4/7/2017.
 */
@Service
public class YunDataEls {

    private static final Logger LOGGER = LoggerFactory.getLogger(YunDataEls.class);

    private static final String INDEX = "yunsearch";

    @Autowired
    private TransportClient client;

    public YunData getLastData() {
        YunData yunData = null;
        SearchResponse response = client.prepareSearch(INDEX)
                .addSort("update_time", SortOrder.DESC).setSize(1).get();
        if (response.getHits().getTotalHits() != 0) {
            SearchHit hit = response.getHits().getAt(0);
            yunData = JSON.parseObject(hit.getSourceAsString(), YunData.class);
        }
        return yunData;
    }

    public void bulkSave(Iterable<YunData> yunDatas) throws IOException {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (YunData data : yunDatas) {
            IndexRequestBuilder iresponse =
                    client.prepareIndex(INDEX, "yun_data", String.valueOf(data.getId()))
                            .setSource(jsonBuilder()
                                    .startObject()
                                    .field("share_id", data.getShareId())
                                    .field("data_id", data.getDataId())
                                    .field("share_name", data.getShareName())
                                    .field("uk", data.getUk())
                                    .field("description", data.getDescription())
                                    .field("share_time", data.getShareTime())
                                    .field("avatar_url", data.getAvatarUrl())
                                    .field("update_time", data.getUpdateTime())
                                    .endObject()
                            );
            bulkRequestBuilder.add(iresponse);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("prepared index {}", JSON.toJSONString(data));
            }
        }
        if (bulkRequestBuilder.numberOfActions() != 0)
            bulkRequestBuilder.get();
    }

    public void bulkDelete(Iterable<YunData> yunDatas) {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (YunData data : yunDatas) {
            DeleteRequestBuilder request = client.prepareDelete(INDEX, "yun_data", String.valueOf(data.getId()));
            bulkRequestBuilder.add(request);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("prepared delete {}", JSON.toJSONString(data));
            }
        }

        if (bulkRequestBuilder.numberOfActions() != 0)
            bulkRequestBuilder.get();
    }

    public List<YunData> findByShareName(String keyword, Integer page, Integer size) {
        SearchRequestBuilder builder = client.prepareSearch(INDEX)
                .setQuery(QueryBuilders.matchQuery("share_name", keyword));
        if (page != null) {
            builder.setFrom((page - 1) * size);
        }
        if (size != null) {
            builder.setSize(size);
        }
        SearchResponse response = builder.get();

        List<YunData> yunDataList = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            YunData data = JSON.parseObject(hit.getSourceAsString(), YunData.class);
            yunDataList.add(data);
        }
        return yunDataList;
    }

    public Long countByShareName(String keyword) {
        SearchRequestBuilder builder = client.prepareSearch(INDEX)
                .setQuery(QueryBuilders.matchQuery("share_name", keyword));
        SearchResponse response = builder.get();
        return response.getHits().getTotalHits();
    }
}
