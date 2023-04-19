package com.blake.ticketmasterdemo.model.vo;

import com.blake.ticketmasterdemo.model.vo.base.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateEventRequest implements BaseRequest {

    @NotBlank
    private String eventName;

    @NotNull
    private Integer artistId;

    @NotNull
    private Integer totalTicket;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;
}
