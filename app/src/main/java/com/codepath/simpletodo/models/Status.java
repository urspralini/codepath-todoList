package com.codepath.simpletodo.models;

/**
 * Created by pbabu on 6/15/16.
 */
public enum Status {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
