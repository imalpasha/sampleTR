package com.fly.bmark2.retroObj;

import com.fly.bmark2.api.obj.tryObj;

/**
 * Created by Dell on 10/1/2015.
 */

public class UpdateDB {

    private String version;
    private tryObj obj;

    public UpdateDB(tryObj obj) {

        this.obj = obj;
    }


    public tryObj getUserObj() {
        return obj;
    }

}
