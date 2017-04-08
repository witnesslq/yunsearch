import com.alibaba.fastjson.JSON;
import com.github.gin.yunsearch.model.YunData;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author GinPonson
 * @since 2017/4/3
 */
public class YunUserTest {

    TransportClient client;

    @Before
    public void before() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(
                        new InetSocketTransportAddress(
                                new InetSocketAddress(
                                        InetAddress.getByName("192.168.153.131"),
                                        9300
                                )
                        )
                );
    }
    @After
    public void after(){
        client.close();
    }

    @Test
    public void test(){
        SearchResponse response = client.prepareSearch("yunsearch")
                .setQuery(QueryBuilders.matchQuery("aa","bb"))
                .addSort("update_time", SortOrder.DESC).setSize(1).get();
        System.out.println(response.getHits().getTotalHits());
        for(SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
            YunData data = JSON.parseObject(hit.getSourceAsString(),YunData.class);
            System.out.println(data);
        }

    }

}
