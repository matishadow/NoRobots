package com.norobots.pocos;

public class PasswordPayloadData {
    public String di;
    public String login;
    public String pwdhash;

    public PasswordPayloadData(String di, String login, String pwdhash) {
        this.di = di;
        this.login = login;
        this.pwdhash = pwdhash;
    }
}
