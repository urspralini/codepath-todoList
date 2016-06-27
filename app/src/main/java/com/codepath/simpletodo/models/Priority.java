package com.codepath.simpletodo.models;

/**
 * Created by pbabu on 6/15/16.
 */
public enum Priority {
    HIGH("High", 1),
    MEDIUM("Medium", 2),
    LOW("Low", 3);

    private String text;
    private int value;

    Priority(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static Priority fromValue(int value) {
        switch (value) {
            case 1:
                return HIGH;
            case 2:
                return MEDIUM;
            default:
                return LOW;
        }
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
}
