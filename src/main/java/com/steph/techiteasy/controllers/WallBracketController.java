package com.steph.techiteasy.controllers;

import com.steph.techiteasy.services.WallBracketService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WallBracketController {
    private final WallBracketService service;

    public WallBracketController(WallBracketService service) {
        this.service = service;
    }
}
