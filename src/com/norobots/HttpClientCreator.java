package com.norobots;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;

public class HttpClientCreator {
    public static CloseableHttpClient CreateHttpClient(){
        return HttpClients.custom().setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE).build();
    }
}
