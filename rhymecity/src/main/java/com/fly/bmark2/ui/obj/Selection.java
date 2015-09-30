package com.fly.bmark2.ui.obj;

public class Selection {

    public String name;
    public String id;
    public String refId;
    public String level;

    public Selection(String name, String id, String level) {
        this.name = name;
        this.id = id;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Selection() {

    }



}