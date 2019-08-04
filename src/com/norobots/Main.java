package com.norobots;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws IOException {
        var loginUrl = "https://login.ingbank.pl/mojeing/rest/renchecklogin";

        var loginData = new LoginData("mattra4682");
        var loginPayLoad = new LoginPayload("", "PL", "", loginData);
        var objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var stringPayload = objectMapper.writeValueAsString(loginPayLoad);

        var stringEntity = new StringEntity(stringPayload);

        var httpClient = HttpClients.createDefault();
        var httpPost = new HttpPost(loginUrl);
        httpPost.addHeader("content-type", "application/json");
        httpPost.setEntity(stringEntity);

        var response = httpClient.execute(httpPost);
        var entity = response.getEntity();

        String responseJson = EntityUtils.toString(entity);
        System.out.println(responseJson);
    }
}
