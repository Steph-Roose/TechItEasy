package com.steph.techiteasy.controllers;

import com.steph.techiteasy.services.CIModuleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CIModuleController {
    private final CIModuleService service;

    public CIModuleController(CIModuleService service) {
        this.service = service;
    }
}
