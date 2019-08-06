package com.norobots.pocos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cur {
    public ArrayList< Account > accts = new ArrayList < Account > ();
}
