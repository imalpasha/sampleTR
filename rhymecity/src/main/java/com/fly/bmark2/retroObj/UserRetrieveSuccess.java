package com.fly.bmark2.retroObj;

import com.fly.bmark2.api.obj.tryObj;

public class UserRetrieveSuccess {

    private final tryObj userObj;

    public UserRetrieveSuccess(tryObj obj) {
        this.userObj = obj;
    }

    public tryObj getUserObj() {
        return userObj;
    }
}
