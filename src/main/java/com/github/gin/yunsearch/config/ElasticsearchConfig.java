package com.github.gin.yunsearch.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author GinPonson
 * @since 2017/4/5
 */
@Configuration
public class ElasticsearchConfig {

    @Bean
    public Settings settings(){
        return Settings.EMPTY;
    }

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(settings())
                .addTransportAddress(
                        new InetSocketTransportAddress(
                                new InetSocketAddress(
                                        InetAddress.getByName("localhost"),
                                        9300
                                )
                        )
                );
        return client;
    }

    @PreDestroy
    public void close() throws UnknownHostException {
        transportClient().close();
    }
}
