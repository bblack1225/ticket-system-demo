package com.blake.ticketmasterdemo.model.vo;

import com.blake.ticketmasterdemo.enums.ArtistType;
import com.blake.ticketmasterdemo.model.vo.base.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateArtistRequest implements BaseRequest {

    @NotBlank
    private String artistName;

    @NotBlank
    private String artistDescription;

    @NotNull
    private ArtistType artistType;
}
