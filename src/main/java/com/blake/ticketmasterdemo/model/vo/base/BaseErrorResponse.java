package com.blake.ticketmasterdemo.model.vo.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonSerialize
public class BaseErrorResponse implements BaseResponse{
}
