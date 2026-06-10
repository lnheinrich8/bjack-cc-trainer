package com.example.server.web;
import com.example.server.service.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/play")
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @PostMapping("/shoe")
    public ShoeResponse shoe(@RequestBody ShoeRequest request) {
        return new ShoeResponse(playService.dealShoe(request.numDecks()));
    }
}
