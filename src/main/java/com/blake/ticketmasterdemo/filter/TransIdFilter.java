package com.blake.ticketmasterdemo.filter;

import com.blake.ticketmasterdemo.service.BuyTicketService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class TransIdFilter implements Filter {

    private final BuyTicketService buyTicketService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Test: {}", buyTicketService.hashCode());
        if(servletRequest instanceof HttpServletRequest httpServletRequest) {
            String singleProcessId = UUID.randomUUID().toString();
            MDC.put("transId", singleProcessId);
            MDC.put("reqURI", httpServletRequest.getRequestURI());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
