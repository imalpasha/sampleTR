package com.fly.bmark2.api.obj;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;


public class Version {

    @Expose
    private String Version;

    public ArrayList getResult() {
        return result;
    }

    public void setResult(ArrayList result) {
        this.result = result;
    }

    @Expose
    private ArrayList result;


    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        this.Version = version;
    }




}