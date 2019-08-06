package com.norobots.pocos;

public class GenericPayload {
    public String token;
    public String locale;
    public String trace;

    public GenericPayload(String token, String locale, String trace) {
        this.token = token;
        this.locale = locale;
        this.trace = trace;
    }
}
