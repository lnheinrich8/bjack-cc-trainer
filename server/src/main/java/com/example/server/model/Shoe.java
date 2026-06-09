package com.example.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Shoe {

    private final List<Card> cards;
    private int position;
    private final int numDecks;

    // Production use: random shuffle.
    public Shoe(int numDecks) {
        this(numDecks, new Random());
    }

    // Testable use: pass a seeded Random (e.g. new Random(42)) for a repeatable
    // shuffle that unit tests can assert against.
    public Shoe(int numDecks, Random random) {
        this.numDecks = numDecks;
        this.cards = new ArrayList<>();
        for (int i = 0; i < numDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(rank, suit));
                }
            }
        }
        Collections.shuffle(cards, random);
        this.position = 0;
    }

    // Returns the next card and advances the cursor. Read-then-advance: get the
    // card at the current position, THEN increment.
    public Card deal() {
        if (!hasNext()) {
            throw new IllegalStateException("Cannot deal from an empty shoe");
        }
        return cards.get(position++);
    }

    public boolean hasNext() {
        return position < cards.size();
    }

    public int cardsRemaining() {
        return cards.size() - position;
    }

    public double decksRemaining() {
        return cardsRemaining() / 52.0;
    }
}
