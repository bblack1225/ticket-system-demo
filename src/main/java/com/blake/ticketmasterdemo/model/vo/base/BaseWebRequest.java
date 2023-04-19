package com.blake.ticketmasterdemo.model.vo.base;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class BaseWebRequest<T extends BaseRequest> {

    @Valid
    @JsonProperty("reqHeader")
    private BaseWebReqHeader header;

    @Valid
    private T transReq;
}
