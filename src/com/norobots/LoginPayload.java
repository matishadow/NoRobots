package com.norobots;

public class LoginPayload extends GenericPayload{
    public LoginPayloadData data;

    public LoginPayload(String token, String locale, String trace, LoginPayloadData data) {
        super(token, locale, trace);
        this.data = data;
    }
}

class LoginPayloadData {
    public String login;

    public LoginPayloadData(String login) {
        this.login = login;
    }
}
