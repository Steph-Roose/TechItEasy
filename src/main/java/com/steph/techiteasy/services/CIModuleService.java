package com.steph.techiteasy.services;

import com.steph.techiteasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class CIModuleService {
    private final CIModuleRepository repos;

    public CIModuleService(CIModuleRepository repos) {
        this.repos = repos;
    }
}
