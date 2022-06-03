package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

    @GetMapping("")
    public Map<String, String> landing(){
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Welcome to book api");
        return responseMap;
    }

}
