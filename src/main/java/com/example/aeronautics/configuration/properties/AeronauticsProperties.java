package com.example.aeronautics.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "api")
public class AeronauticsProperties {
    private String create;
    private String get;
    private String update;
    private String delete;
}
