package com.maksymmykytiuk.elephants.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Configuration
@ConfigurationProperties(prefix = "ftp")
@Data
public class FTPProperties {

    private String server;
    private String username;
    private String password;
    @Min(0)
    @Max(65535)
    private int port;
    private int keepAliveTimeout;
    private boolean autoStart;

    @PostConstruct
    public void init() {
        if (port == 0) {
            port = 21;
        }
    }
}
