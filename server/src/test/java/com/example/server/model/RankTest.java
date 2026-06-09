package com.example.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {

    @Test
    void lowCardsArePlusOne() {
        assertEquals(1, Rank.TWO.getCountValue());
        assertEquals(1, Rank.THREE.getCountValue());
        assertEquals(1, Rank.FOUR.getCountValue());
        assertEquals(1, Rank.FIVE.getCountValue());
        assertEquals(1, Rank.SIX.getCountValue());
    }

    @Test
    void middleCardsAreZero() {
        assertEquals(0, Rank.SEVEN.getCountValue());
        assertEquals(0, Rank.EIGHT.getCountValue());
        assertEquals(0, Rank.NINE.getCountValue());
    }

    @Test
    void highCardsAreMinusOne() {
        assertEquals(-1, Rank.TEN.getCountValue());
        assertEquals(-1, Rank.JACK.getCountValue());
        assertEquals(-1, Rank.QUEEN.getCountValue());
        assertEquals(-1, Rank.KING.getCountValue());
        assertEquals(-1, Rank.ACE.getCountValue());
    }

    @Test
    void thereAreThirteenRanks() {
        assertEquals(13, Rank.values().length);
    }

    @Test
    void hiLoIsBalancedAcrossAllRanks() {
        // A balanced counting system: the 13 ranks sum to 0 (5×+1, 3×0, 5×−1).
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getCountValue();
        }
        assertEquals(0, sum);
    }
}
