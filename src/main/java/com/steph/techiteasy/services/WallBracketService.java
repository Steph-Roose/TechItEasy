package com.steph.techiteasy.services;

import com.steph.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {
    private final WallBracketRepository repos;

    public WallBracketService(WallBracketRepository repos) {
        this.repos = repos;
    }
}
