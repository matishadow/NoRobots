package com.norobots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class PasswordPayload extends GenericPayload{
    public PasswordPayloadData data;

    public PasswordPayload(String token, String locale, String trace, PasswordPayloadData data) {
        super(token, locale, trace);
        this.data = data;
    }
}

class PasswordPayloadData {
    public String di;
    public String login;
    public String pwdhash;

    public PasswordPayloadData(String di, String login, String pwdhash) {
        this.di = di;
        this.login = login;
        this.pwdhash = pwdhash;
    }
}
