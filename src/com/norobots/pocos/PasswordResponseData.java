package com.norobots.pocos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordResponseData {
    public String token;
}
