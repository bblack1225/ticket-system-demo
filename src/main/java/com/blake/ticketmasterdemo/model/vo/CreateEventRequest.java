package com.blake.ticketmasterdemo.model.vo;

import com.blake.ticketmasterdemo.jsonconverter.deserialize.LocalDateTimeDeserializer;
import com.blake.ticketmasterdemo.model.vo.base.BaseRequest;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CreateEventRequest implements BaseRequest {

    @NotBlank
    private String eventName;

    @NotNull
    private Integer artistId;

    @NotNull
    private Integer totalTicket;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull
    private LocalDateTime eventStartTime;

    @NotNull
    private Integer estimatedDuration;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull
    private LocalDateTime startSaleTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull
    private LocalDateTime endSaleTime;
}
