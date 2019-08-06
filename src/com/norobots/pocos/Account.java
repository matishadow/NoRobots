package com.norobots.pocos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    public String acct;
    public String atrs;
    public  float avbal;
    public String curr;
    public String name;
    public float plnbal;
    public String type;
    public String visible;
}
