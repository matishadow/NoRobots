package com.norobots;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsResponse {
    public String status;
    public AccountsResponseData data;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class AccountsResponseData {
     AccountTypes accts;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class AccountTypes {
    public CurrentAccounts cur;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrentAccounts {
    public ArrayList<CurrentAccount> accts;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrentAccount {
    public String acct;
    public String plnbal;
}