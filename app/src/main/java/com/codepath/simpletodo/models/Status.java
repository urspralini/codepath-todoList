package com.codepath.simpletodo.models;

import com.activeandroid.serializer.TypeSerializer;

/**
 * Created by pbabu on 6/15/16.
 */
public enum Status {
    NEW("New", 1),
    IN_PROGRESS("In Progress", 2),
    DONE("Done",3);

    private String text;
    private int value;

    Status(String text, int value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }

    public static Status fromValue(int value) {
        switch (value) {
            case 3:
                return DONE;
            case 2:
                return IN_PROGRESS;
            default:
                return NEW;
        }
    }
 }
