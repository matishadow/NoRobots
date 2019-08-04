package com.norobots;

public class LoginPayload {
    public String token;
    public String locale;
    public String trace;
    public LoginData data;

    public LoginPayload(String token, String locale, String trace, LoginData data) {
        this.token = token;
        this.locale = locale;
        this.trace = trace;
        this.data = data;
    }
}

class LoginData {
    public String login;

    public LoginData(String login) {
        this.login = login;
    }
}
