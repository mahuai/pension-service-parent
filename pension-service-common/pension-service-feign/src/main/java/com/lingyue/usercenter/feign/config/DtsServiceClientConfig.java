package com.lingyue.usercenter.feign.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * DTS service feign client 配置类
 */
public class DtsServiceClientConfig {


    /**
     * Contract Bean
     * @return Contract
     */
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

    /**
     * 日志配置 Bean
     * @return Logger.Level instance
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
