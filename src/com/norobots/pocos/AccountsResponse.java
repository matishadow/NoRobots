package com.norobots.pocos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsResponse {
    public AccountsResponseData data;
    private String status;
}

