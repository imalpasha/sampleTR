package com.fly.bmark2.api.model;

import com.fly.bmark2.api.obj.tryObj;

public class tryResponse {

    private final tryObj rhymes;

    public tryResponse(tryObj userObj) {
        this.rhymes = userObj;
    }

    public tryObj getUserDetailObj2() {
        return rhymes;
    }
}
