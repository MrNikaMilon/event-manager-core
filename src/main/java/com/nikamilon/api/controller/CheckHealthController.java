package com.nikamilon.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//поработать и разобраться с актуатором
@RestController
@RequestMapping("/api/check_health")
public class CheckHealthController {

    @GetMapping("")
    public String checkHealth() {
        return String.format("Server is available to request.");
    }
}
