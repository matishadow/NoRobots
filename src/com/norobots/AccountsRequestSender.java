package com.norobots;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.norobots.pocos.AccountsResponse;
import com.norobots.pocos.GenericPayload;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AccountsRequestSender extends GenericRequestSender {
    final String accountsUrl = "https://login.ingbank.pl/mojeing/rest/rengetallingprds";

    public AccountsResponse SendAccountsRequest(HttpClient httpClient, String token) throws IOException {
        var accountsPayload = convertObjectToStringEntity(new GenericPayload(token, "PL", ""));
        var httpPost = createHttpPost(accountsUrl, accountsPayload);
        var response = httpClient.execute(httpPost);
        var entity = response.getEntity();
        var responseJson = EntityUtils.toString(entity);

        return new ObjectMapper().readValue(responseJson, AccountsResponse.class);
    }
}
