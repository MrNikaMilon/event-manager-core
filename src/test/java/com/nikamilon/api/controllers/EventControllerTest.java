package com.nikamilon.api.controllers;

import com.nikamilon.api.mappers.EventMapper;
import com.nikamilon.api.service.EventService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@AllArgsConstructor
@AutoConfigureTestDatabase
@Profile("dev")
public class EventControllerTest {

    private MockMvc mockMvc;

    private EventMapper eventMapper;

    private EventService eventService;


}
