package com.public_services.enums;

public enum Rate {

    FIVE_AND_TWO("Пять через два"), TWO_AND_TWO("Два через два");

    private String text;

    Rate(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Rate parseText(String text) {
        for (Rate r : Rate.values()) {
            if (r.text.equals(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }
}
