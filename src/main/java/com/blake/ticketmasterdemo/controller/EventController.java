package com.blake.ticketmasterdemo.controller;

import com.blake.ticketmasterdemo.model.vo.CreateEventRequest;
import com.blake.ticketmasterdemo.model.vo.CreateEventResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.service.CreateEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventService createEventService;

    @PostMapping("/create")
    public BaseWebResponse<CreateEventResponse> createEvent(
            @Valid @RequestBody BaseWebRequest<CreateEventRequest> webRequest
            ){
        return createEventService.execute(webRequest);
    }
}
