package com.blake.ticketmasterdemo.controller;

import com.blake.ticketmasterdemo.model.vo.CreateArtistRequest;
import com.blake.ticketmasterdemo.model.vo.CreateArtistResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.service.CreateArtistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final CreateArtistService createArtistService;

    @PostMapping("/create")
    public BaseWebResponse<CreateArtistResponse> createArtist(
            @Valid @RequestBody BaseWebRequest<CreateArtistRequest> webRequest) {
       return createArtistService.execute(webRequest);
    }
}
