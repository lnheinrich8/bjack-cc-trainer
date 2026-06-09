package com.example.server.model;

public enum Rank {
    TWO(1),
    THREE(1),
    FOUR(1),
    FIVE(1),
    SIX(1),
    SEVEN(0),
    EIGHT(0),
    NINE(0),
    TEN(-1),
    JACK(-1),
    QUEEN(-1),
    KING(-1),
    ACE(-1);

    private final int countValue;

    Rank(int countValue) {
        this.countValue = countValue;
    }

    public int getCountValue() {
        return countValue;
    }
}
