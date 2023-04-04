package com.lingyue.usercenter.feign.config;

import com.lingyue.usercenter.feign.env.FeignClientConfigurationProperties;
import com.lingyue.usercenter.logging.core.YamlPropertySourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Feign 装配类
 */
@Slf4j
@EnableFeignClients(basePackages = {"com.lingyue.usercenter.feign"})
@PropertySource(value = "classpath:config/feign-client.yaml", factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties(FeignClientConfigurationProperties.class)
@Configuration
@RefreshScope
public class FeignClientAutoConfiguration {

    /**
     * contructor
     */
    public FeignClientAutoConfiguration() {
        log.info("FeignClientAutoConfiguration inited.");
    }

    /**
     * Retryer Bean
     * @return Retryer
     */
    /*@Bean
    @Scope("prototype")*/
    public DefaultRetryer retryer() {
        return new DefaultRetryer();
    }

    /**
     *  RequestInterceptor Bean
     * @param properties Feign Client Config
     * @return RequestInterceptor instance
     */
    @Bean
    public CurrentRequestHeadersInterceptor headersInterceptor(FeignClientConfigurationProperties properties){
        return new CurrentRequestHeadersInterceptor(properties);
    }
}
