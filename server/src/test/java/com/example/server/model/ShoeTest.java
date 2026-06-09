package com.example.server.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoeTest {

    @Test
    void oneDeckHasFiftyTwoCards() {
        Shoe shoe = new Shoe(1);
        assertEquals(52, shoe.cardsRemaining());
    }

    @Test
    void multipleDecksScaleCardCount() {
        assertEquals(104, new Shoe(2).cardsRemaining());
        assertEquals(312, new Shoe(6).cardsRemaining());
    }

    @Test
    void dealingDecrementsCardsRemaining() {
        Shoe shoe = new Shoe(1);
        shoe.deal();
        assertEquals(51, shoe.cardsRemaining());
        shoe.deal();
        assertEquals(50, shoe.cardsRemaining());
    }

    @Test
    void dealingEntireShoeYieldsACompleteDeckWithNoDuplicates() {
        // Regression guard for the off-by-one deal() bug: if the first card were
        // skipped or one were lost, we'd get fewer than 52 unique cards.
        Shoe shoe = new Shoe(1, new Random(42));
        Set<Card> dealt = new HashSet<>();
        int count = 0;
        while (shoe.hasNext()) {
            dealt.add(shoe.deal());
            count++;
        }
        assertEquals(52, count, "should deal exactly 52 cards");
        assertEquals(52, dealt.size(), "every dealt card should be unique");
    }

    @Test
    void hasNextIsFalseWhenEmpty() {
        Shoe shoe = new Shoe(1);
        while (shoe.hasNext()) {
            shoe.deal();
        }
        assertFalse(shoe.hasNext());
        assertEquals(0, shoe.cardsRemaining());
    }

    @Test
    void dealingFromEmptyShoeThrows() {
        Shoe shoe = new Shoe(1);
        while (shoe.hasNext()) {
            shoe.deal();
        }
        assertThrows(IllegalStateException.class, shoe::deal);
    }

    @Test
    void decksRemainingReflectsCardsDealt() {
        Shoe shoe = new Shoe(1);
        assertEquals(1.0, shoe.decksRemaining(), 0.0001);
        for (int i = 0; i < 26; i++) {
            shoe.deal();
        }
        assertEquals(0.5, shoe.decksRemaining(), 0.0001);
    }

    @Test
    void seededShoesProduceIdenticalOrder() {
        // Same seed -> same shuffle, so the dealt sequences match card-for-card.
        Shoe a = new Shoe(1, new Random(123));
        Shoe b = new Shoe(1, new Random(123));
        while (a.hasNext()) {
            assertEquals(a.deal(), b.deal());
        }
    }
}
