package com.blake.ticketmasterdemo.service;

import com.blake.ticketmasterdemo.model.vo.base.BaseRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;

public interface BaseApiService<T extends BaseRequest, S extends BaseResponse>{
    BaseWebResponse<S> execute(BaseWebRequest<T> webRequest);
}
