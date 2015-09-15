package com.fly.bmark2.api.model;

import com.fly.bmark2.api.obj.RhymesObject;

public class RhymesResponse {

    private final RhymesObject rhymes;

    public RhymesResponse(RhymesObject rhymesObject) {
        this.rhymes = rhymesObject;
    }

    public RhymesObject getRhymesObject() {
        return rhymes;
    }
}
