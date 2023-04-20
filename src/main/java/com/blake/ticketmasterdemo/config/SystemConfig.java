package com.blake.ticketmasterdemo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.blake.system")
@Getter
@Setter
public class SystemConfig {
    private Integer maxTicketPurchasable;
}
