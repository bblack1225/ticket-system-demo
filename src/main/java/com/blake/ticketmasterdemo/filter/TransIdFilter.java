package com.blake.ticketmasterdemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Slf4j
@Component
public class TransIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest httpServletRequest) {
            String singleProcessId = UUID.randomUUID().toString();
            MDC.put("transId", singleProcessId);
            MDC.put("reqURI", httpServletRequest.getRequestURI());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
