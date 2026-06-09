package com.example.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardTest {

    @Test
    void accessorsReturnConstructorValues() {
        Card card = new Card(Rank.ACE, Suit.SPADES);
        assertEquals(Rank.ACE, card.rank());
        assertEquals(Suit.SPADES, card.suit());
    }

    @Test
    void countValueDelegatesToRank() {
        assertEquals(1, new Card(Rank.FIVE, Suit.HEARTS).countValue());
        assertEquals(0, new Card(Rank.EIGHT, Suit.CLUBS).countValue());
        assertEquals(-1, new Card(Rank.KING, Suit.DIAMONDS).countValue());
    }

    @Test
    void cardsWithSameRankAndSuitAreEqual() {
        // Records generate equals()/hashCode() — two identical cards are equal.
        Card a = new Card(Rank.TEN, Suit.HEARTS);
        Card b = new Card(Rank.TEN, Suit.HEARTS);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void cardsWithDifferentRankOrSuitAreNotEqual() {
        Card base = new Card(Rank.TEN, Suit.HEARTS);
        assertNotEquals(base, new Card(Rank.NINE, Suit.HEARTS));
        assertNotEquals(base, new Card(Rank.TEN, Suit.SPADES));
    }
}
