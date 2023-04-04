//package com.lingyue.usercenter.feign.config;
//
//import com.lingyue.usercenter.feign.env.FeignClientConfigurationProperties;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//
///**
// * 透传当前请求上下文的头信息到FeignClient中
// */
//@Component
//public class CurrentRequestHeadersInterceptor implements RequestInterceptor {
//
//    private final FeignClientConfigurationProperties properties;
//
//    /**
//     * constructor
//     *
//     * @param properties FeignClient Config
//     */
//    public CurrentRequestHeadersInterceptor(FeignClientConfigurationProperties properties) {
//        this.properties = properties;
//    }
//
//    /**
//     * 接口
//     *
//     * @param template RestTemplate
//     */
//    @Override
//    public void apply(RequestTemplate template) {
//        if (CollectionUtils.isEmpty(this.properties.getPropagateHeaders())) {
//            return;
//        }
//
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes instanceof ServletRequestAttributes) {
//            HttpServletRequest webRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
//            Enumeration<String> headerNames = webRequest.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                if (this.properties.getPropagateHeaders().contains(name.toLowerCase())) {
//                    template.header(name, webRequest.getHeader(name));
//                }
//            }
//        }
//    }
//}
