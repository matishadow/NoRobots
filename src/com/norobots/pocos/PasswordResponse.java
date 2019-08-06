package com.norobots.pocos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResponse {
    public PasswordResponseData data;
    public String status;
}

