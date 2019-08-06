package com.norobots.pocos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsResponseData {
    public Accts accts;
}
