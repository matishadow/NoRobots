package com.norobots.pocos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class PasswordPayload extends GenericPayload {
    public PasswordPayloadData data;

    public PasswordPayload(String token, String locale, String trace, PasswordPayloadData data) {
        super(token, locale, trace);
        this.data = data;
    }
}

