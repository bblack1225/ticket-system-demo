package com.blake.ticketmasterdemo.model.vo;

import com.blake.ticketmasterdemo.model.vo.base.BaseResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BuyTicketResponse implements BaseResponse {
    private String result;
    private List<String> ticketNoList;
    private int memberId;
    private String memberEmail;
    private int eventId;
}
