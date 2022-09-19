package com.steph.techiteasy.services;

import com.steph.techiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository repos;

    public RemoteControllerService(RemoteControllerRepository repos) {
        this.repos = repos;
    }
}
