package com.lingyue.usercenter.feign.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.HashSet;

@Data
@RefreshScope
@ConfigurationProperties(prefix = "usercenter.feign-client")
public class FeignClientConfigurationProperties {

    /**
     * propagate header names
     */
    private HashSet<String> propagateHeaders;
}
