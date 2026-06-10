package com.example.server.service;

import com.example.server.model.Card;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayServiceTest {

    private final PlayService service = new PlayService();

    @Test
    void dealsFullShoeForOneDeck() {
        List<Card> shoe = service.dealShoe(1);
        assertEquals(52, shoe.size());
    }

    @Test
    void dealsFullShoeForMultipleDecks() {
        assertEquals(104, service.dealShoe(2).size());
        assertEquals(312, service.dealShoe(6).size());
    }

    @Test
    void rejectsZeroDecks() {
        assertThrows(IllegalArgumentException.class, () -> service.dealShoe(0));
    }

    @Test
    void rejectsNegativeDecks() {
        assertThrows(IllegalArgumentException.class, () -> service.dealShoe(-3));
    }

    @Test
    void singleDeckHasNoDuplicateCards() {
        // A one-deck shoe must be the 52 distinct cards, each exactly once.
        List<Card> shoe = service.dealShoe(1);
        Set<Card> unique = new HashSet<>(shoe);
        assertEquals(52, unique.size());
    }

    @Test
    void singleDeckIsHiLoCountNeutral() {
        // A complete deck has twenty +1 cards (2-6), twenty -1 cards (10-A) and
        // twelve neutral cards (7-9), so its Hi-Lo running count sums to zero.
        int sum = service.dealShoe(1).stream().mapToInt(Card::countValue).sum();
        assertEquals(0, sum);
    }

    @Test
    void multiDeckShoeIsHiLoCountNeutral() {
        // Whole shoes of any deck count are balanced too.
        int sum = service.dealShoe(6).stream().mapToInt(Card::countValue).sum();
        assertEquals(0, sum);
    }
}
