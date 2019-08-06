package com.norobots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsResponse {
    public AccountsResponseData data;
    private String status;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class AccountsResponseData {
    public Accts accts;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Accts {
    public Cur cur;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Cur {
    public ArrayList < Account > accts = new ArrayList < Account > ();
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Account {
    public String acct;
    public String atrs;
    public  float avbal;
    public String curr;
    public String name;
    public float plnbal;
    public String type;
    public String visible;
}
