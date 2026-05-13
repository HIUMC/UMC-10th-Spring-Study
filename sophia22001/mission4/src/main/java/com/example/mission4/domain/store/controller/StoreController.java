package com.example.mission4.domain.store.controller;

import com.example.mission4.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
}
