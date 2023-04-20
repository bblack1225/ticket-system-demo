package com.blake.ticketmasterdemo.model.vo;

import com.blake.ticketmasterdemo.model.vo.base.BaseRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuyTicketRequest implements BaseRequest {

    @NotNull
    private int eventId;
    @NotNull
    private int memberId;

    @NotNull
    @Max(4)
    @Min(1)
    private int quantity;
}
