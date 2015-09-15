package com.fly.bmark2.rhymes;

public class RhymesRequestedEvent {

    private final String word;

    public RhymesRequestedEvent(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
