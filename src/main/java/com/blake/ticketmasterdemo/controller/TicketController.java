package com.blake.ticketmasterdemo.controller;

import com.blake.ticketmasterdemo.model.vo.BuyTicketRequest;
import com.blake.ticketmasterdemo.model.vo.BuyTicketResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.service.BuyTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final BuyTicketService buyTicketService;


    @PostMapping("/buy")
    public BaseWebResponse<BuyTicketResponse> buyTicket(
            @Valid @RequestBody BaseWebRequest<BuyTicketRequest> webRequest){
        return buyTicketService.execute(webRequest);
    }
}
