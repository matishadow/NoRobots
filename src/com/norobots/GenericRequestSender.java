package com.norobots;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class GenericRequestSender {
    protected StringEntity convertObjectToStringEntity(Object object) throws JsonProcessingException, UnsupportedEncodingException {
        var objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var stringPayload = objectMapper.writeValueAsString(object);

        return new StringEntity(stringPayload);
    }

    protected HttpPost createHttpPost(String url, StringEntity object) {
        var httpPost = new HttpPost(url);
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("connection", "keep-alive");
        httpPost.addHeader("cookies_accepted", "true");
        httpPost.addHeader("X-Wolf-Protection", "0.5259627313757991");
        httpPost.setEntity(object);

        return httpPost;
    }
}
