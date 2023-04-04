package com.lingyue.usercenter.feign.config;

import com.lingyue.usercenter.feign.config.filesvc.FeignSpringFormEncoder;
import feign.Contract;
import feign.codec.Encoder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

public class FileServiceSupportConfig {

    /**
     * 启用feigin自定义注解支持，如 @RequestLine 和 @Param
     * @return
     */
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }

    /**
     * feign 实现多pojo传输与MultipartFile上传 编码器，需配合开启feign自带注解使用
     * @return
     */
    @Bean
    public Encoder feignSpringFormEncoder(){
        //注入自定义编码器
        return new FeignSpringFormEncoder();
    }
}
