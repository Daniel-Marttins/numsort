package com.outbrick.numsort.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppProperties {

    @Value("${app.host-url}")
    private String appUrl;

    @Value("${server.port}")
    private String serverPort;
}
