package com.norobots.pocos;

public class LoginPayload extends GenericPayload {
    public LoginPayloadData data;

    public LoginPayload(String token, String locale, String trace, LoginPayloadData data) {
        super(token, locale, trace);
        this.data = data;
    }
}

