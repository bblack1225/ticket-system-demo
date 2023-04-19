package com.blake.ticketmasterdemo.model.vo;

import com.blake.ticketmasterdemo.enums.ArtistType;
import com.blake.ticketmasterdemo.model.vo.base.BaseResponse;
import lombok.Data;

@Data
public class CreateArtistResponse implements BaseResponse {

    private Integer artistId;
    private String artistName;
    private String artistDescription;
    private ArtistType artistType;
}
