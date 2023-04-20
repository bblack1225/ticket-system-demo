package com.blake.ticketmasterdemo.controller;

import com.blake.ticketmasterdemo.model.vo.CreateEventRequest;
import com.blake.ticketmasterdemo.model.vo.CreateEventResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.service.CreateEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventService createEventService;

    @GetMapping("/test")
    public String test(@RequestParam(required = false) String name){
        Objects.requireNonNull(name);
        return "Test";
    }

    @PostMapping("/create")
    public BaseWebResponse<CreateEventResponse> createEvent(
            @Valid @RequestBody BaseWebRequest<CreateEventRequest> webRequest
            ){
        return createEventService.execute(webRequest);
    }
}
