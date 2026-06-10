package com.example.server.service;
import com.example.server.model.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayService {

    public List<Card> dealShoe(int numDecks) {
        if (numDecks < 1)
            throw new IllegalArgumentException("Must have at least one deck");

        Shoe shoe = new Shoe(numDecks);
        List<Card> sequence = new ArrayList<>();
        while (shoe.hasNext()) {
            sequence.add(shoe.deal());
        }
        return sequence;
    }
}
