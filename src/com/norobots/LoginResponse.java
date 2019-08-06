package com.norobots;

public class LoginResponse {
    public String status;
    public LoginResponseData data;
}

class LoginResponseData {
    public String key;
    public String mask;
    public String mobimask;
    public String salt;
}
