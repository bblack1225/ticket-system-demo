package com.blake.ticketmasterdemo.model.vo;


import com.blake.ticketmasterdemo.model.vo.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventResponse implements BaseResponse {
    private int eventId;
    private String eventName;
    private String eventStatus;
    private String startTime;
    private int totalTickets;
}
