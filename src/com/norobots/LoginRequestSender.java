package com.norobots;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.norobots.pocos.LoginPayload;
import com.norobots.pocos.LoginPayloadData;
import com.norobots.pocos.LoginResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

class LoginRequestSender  extends GenericRequestSender{
    private final String loginUrl = "https://login.ingbank.pl/mojeing/rest/renchecklogin";

    LoginResponse SendLoginRequest(HttpClient httpClient, String login) throws IOException {
        var loginPayload = convertObjectToStringEntity(createLoginPayload(login));
        var httpPost = createHttpPost(loginUrl, loginPayload);
        var response = httpClient.execute(httpPost);
        var entity = response.getEntity();
        String responseJson = EntityUtils.toString(entity);

        return new ObjectMapper().readValue(responseJson, LoginResponse.class);
    }

    private LoginPayload createLoginPayload(String login) throws JsonProcessingException, UnsupportedEncodingException {
        var loginData = new LoginPayloadData(login);

        return new LoginPayload("", "PL", "", loginData);
    }
}
