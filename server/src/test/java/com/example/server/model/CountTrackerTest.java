package com.example.server.model;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountTrackerTest {

    @Test
    void startsAtZero() {
        assertEquals(0, new CountTracker().getRunningCount());
    }

    @Test
    void recordAddsCardCountValue() {
        CountTracker tracker = new CountTracker();
        tracker.record(new Card(Rank.FIVE, Suit.HEARTS));   // +1
        assertEquals(1, tracker.getRunningCount());
        tracker.record(new Card(Rank.KING, Suit.SPADES));   // -1
        assertEquals(0, tracker.getRunningCount());
        tracker.record(new Card(Rank.EIGHT, Suit.CLUBS));   // 0
        assertEquals(0, tracker.getRunningCount());
        tracker.record(new Card(Rank.TWO, Suit.DIAMONDS));  // +1
        assertEquals(1, tracker.getRunningCount());
    }

    @Test
    void countingAFullDeckReturnsToZero() {
        // A complete deck is balanced, so after seeing every card the running
        // count must be 0. This exercises Shoe + Card + CountTracker together.
        Shoe shoe = new Shoe(1, new Random(7));
        CountTracker tracker = new CountTracker();
        while (shoe.hasNext()) {
            tracker.record(shoe.deal());
        }
        assertEquals(0, tracker.getRunningCount());
    }

    @Test
    void countingFullSixDeckShoeReturnsToZero() {
        Shoe shoe = new Shoe(6, new Random(7));
        CountTracker tracker = new CountTracker();
        while (shoe.hasNext()) {
            tracker.record(shoe.deal());
        }
        assertEquals(0, tracker.getRunningCount());
    }

    @Test
    void trueCountIsRunningCountOverDecksRemaining() {
        CountTracker tracker = new CountTracker();
        for (int i = 0; i < 6; i++) {
            tracker.record(new Card(Rank.FOUR, Suit.HEARTS)); // each +1 -> running 6
        }
        assertEquals(6, tracker.getRunningCount());
        assertEquals(3.0, tracker.getTrueCount(2.0), 0.0001);   // 6 / 2 decks
        assertEquals(1.5, tracker.getTrueCount(4.0), 0.0001);   // 6 / 4 decks
    }

    @Test
    void trueCountGuardsAgainstZeroDecksRemaining() {
        CountTracker tracker = new CountTracker();
        tracker.record(new Card(Rank.TWO, Suit.HEARTS));
        assertEquals(0, tracker.getTrueCount(0.0), 0.0001);
    }

    @Test
    void resetReturnsCountToZero() {
        CountTracker tracker = new CountTracker();
        tracker.record(new Card(Rank.TWO, Suit.HEARTS));
        tracker.record(new Card(Rank.THREE, Suit.HEARTS));
        tracker.reset();
        assertEquals(0, tracker.getRunningCount());
    }
}
