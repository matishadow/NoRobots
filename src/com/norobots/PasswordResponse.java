package com.norobots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResponse {
    public PasswordResponseData data;
    public String status;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PasswordResponseData {
    public String token;
}

