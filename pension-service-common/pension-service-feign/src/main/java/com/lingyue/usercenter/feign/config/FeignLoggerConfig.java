package com.lingyue.usercenter.feign.config;

import com.lingyue.usercenter.feign.config.logger.CustomerFeignLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 * <p/>
 *
 * @author cuiwei
 * @date 2022/7/12 13:25
 */
@Configuration
public class FeignLoggerConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    Logger feignLogger() {
        return new CustomerFeignLogger();
    }
}
