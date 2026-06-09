package com.example.server.model;

public record Card(Rank rank, Suit suit) {

    // Ask a card directly for its Hi-Lo value instead of going through Rank
    public int countValue() {
        return rank.getCountValue();
    }
}
