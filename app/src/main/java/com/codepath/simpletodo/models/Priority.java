package com.codepath.simpletodo.models;

/**
 * Created by pbabu on 6/15/16.
 */
public enum Priority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private String value;

    Priority(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
