package com.norobots;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        var loginUrl = "https://login.ingbank.pl/mojeing/rest/renchecklogin";

        var loginData = new LoginData("mattra4682");
        var loginPayLoad = new LoginPayload("", "PL", "", loginData);
        var objectMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var stringPayload = objectMapper.writeValueAsString(loginPayLoad);

        var httpClient = HttpClients.createDefault();
        var httpPost = new HttpPost(loginUrl);

        //httpPost.setEntity();
    }
}
