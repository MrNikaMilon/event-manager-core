package com.nikamilon.api.controller;

import com.nikamilon.api.domain.dto.EventDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events/")
public class EventController {

    @GetMapping
    public List<EventDto> getEvents() {
        return null;
    }

    @PostMapping
}
