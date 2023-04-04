//package com.lingyue.usercenter.feign.config.logger;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.lingyue.usercenter.feign.config.threadlocal.FeignLogThreadLocal;
//import com.lingyue.usercenter.logging.enums.ApiOwnerEnum;
//import com.lingyue.usercenter.logging.enums.ResultTypeEnum;
//import com.lingyue.usercenter.logging.http.RequestLog;
//import com.lingyue.usercenter.logging.http.RequestLogUtils;
//import com.lingyue.usercenter.logging.trace.TraceInfo;
//import com.lingyue.usercenter.logging.trace.TraceUtil;
//import com.lingyue.usercenter.logging.utils.CheckUtils;
//import feign.Request;
//import feign.Response;
//import feign.Util;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.util.CollectionUtils;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicReference;
//
///**
// * @author Andy
// * @title: CustomerFeignLogger
// * @description:
// * @date 2020/7/3 16:11
// */
//public class CustomerFeignLogger extends feign.Logger {
//    private final Logger logger;
//
//    public CustomerFeignLogger() {
//        this(feign.Logger.class);
//    }
//
//    public CustomerFeignLogger(Class<?> clazz) {
//        this(LoggerFactory.getLogger(clazz));
//    }
//
//    public CustomerFeignLogger(String name) {
//        this(LoggerFactory.getLogger(name));
//    }
//
//    CustomerFeignLogger(Logger logger) {
//        this.logger = logger;
//    }
//
//
//    @Override
//    protected void logRequest(String configKey, Level logLevel, Request request) {
//        if (logger.isInfoEnabled()) {
//            customerlogRequest(configKey, logLevel, request);
//        }
//    }
//
//    @Override
//    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
//            throws IOException {
//        if (logger.isInfoEnabled()) {
//            return customerLogResponse(configKey, logLevel, response, elapsedTime);
//        }
//        return response;
//    }
//
//    @Override
//    protected void log(String configKey, String format, Object... args) {
//        // Not using SLF4J's support for parameterized messages (even though it
//        // would be more efficient) because it would
//        // require the incoming message formats to be SLF4J-specific.
//        if (logger.isInfoEnabled()) {
//            logger.info(String.format(methodTag(configKey) + format, args));
//        }
//    }
//
//    private void customerLog(String requestType, String configKey, Map<String, Object> requestMap) {
//        if (logger.isInfoEnabled()) {
//            logger.info(requestType + FeignConsts.RECORD_SPLIT + configKey + "-" + JSON.toJSONString(requestMap));
//        }
//    }
//
//
//    protected Response customerLogResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
//        String reason = response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason() : "";
//        int status = response.status();
//
//        Map<String, Object> statusMap = new HashMap<>();
//        statusMap.put(FeignConsts.CODE, status);
//        statusMap.put(FeignConsts.STATUS, reason);
//        statusMap.put(FeignConsts.TIME, elapsedTime);
//        customerLog(FeignConsts.FEIGN_STATUS, configKey, statusMap);
//
//        Map<String, Object> resultMap = new HashMap<>();
//        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
//            Iterator var8 = response.headers().keySet().iterator();
//
//            while (var8.hasNext()) {
//                String field = (String) var8.next();
//                Iterator var10 = Util.valuesOrEmpty(response.headers(), field).iterator();
//
//                while (var10.hasNext()) {
//                    String value = (String) var10.next();
//                    resultMap.put(field, value);
//                }
//            }
//
//            int bodyLength = 0;
//            if (response.body() != null && status != 204 && status != 205) {
//                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
//                bodyLength = bodyData.length;
//                if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyLength > 0) {
//                    try {
//                        resultMap.put(FeignConsts.BODY, JSONObject.parseObject(Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data")));
//                    } catch (Exception e) {
//                        resultMap.put(FeignConsts.BODY, Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data"));
//                    }
//                }
//                resultMap.put(FeignConsts.BODY_LENGTH, bodyLength);
//                customerLog(FeignConsts.FEIGN_RESPONSE, configKey, resultMap);
//                saveFeignResponseLog(configKey,response,bodyData);
//                return response.toBuilder().body(bodyData).build();
//            }
//            resultMap.put(FeignConsts.BODY_LENGTH, bodyLength);
//            customerLog(FeignConsts.FEIGN_RESPONSE, configKey, resultMap);
//            saveFeignResponseLog(configKey,response,null);
//        }
//
//        return response;
//    }
//
//
//    private void customerlogRequest(String configKey, Level logLevel, Request request) {
//        Map<String, Object> requestMap = new HashMap<>();
//        requestMap.put(FeignConsts.METHOD, request.httpMethod().name());
//        requestMap.put(FeignConsts.URL, request.url());
//        customerLog(FeignConsts.FEIGN_STATUS, configKey, requestMap);
//        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
//            Iterator var4 = request.headers().keySet().iterator();
//
//            String bodyText;
//            while (var4.hasNext()) {
//                bodyText = (String) var4.next();
//                Iterator var6 = Util.valuesOrEmpty(request.headers(), bodyText).iterator();
//                while (var6.hasNext()) {
//                    String value = (String) var6.next();
//                    requestMap.put(bodyText, value);
//                }
//            }
//
//            int bodyLength = 0;
//
//            boolean multipart = request.headers().containsKey(HttpHeaders.CONTENT_TYPE) && request.headers().get(HttpHeaders.CONTENT_TYPE).stream().anyMatch((i) -> StringUtils.contains(i, "multipart"));
//            if (request.body() != null) {
//                bodyLength = request.body().length;
//                if (logLevel.ordinal() >= Level.FULL.ordinal() && !multipart) {
//                    bodyText = request.charset() != null ? new String(request.body(), request.charset()) : null;
//                    requestMap.put(FeignConsts.PARAMETERS, bodyText);
//                }
//            }
//            requestMap.put(FeignConsts.BODY_LENGTH, bodyLength);
//            customerLog(FeignConsts.FEIGN_REQUEST, configKey, requestMap);
//            saveFeignRequestLog(configKey,request);
//        }
//
//    }
//
//    @Override
//    protected IOException logIOException(String configKey, Level logLevel, IOException ioe, long elapsedTime) {
//        Map<String, Object> exceptionMap = new HashMap<>();
//        exceptionMap.put(ioe.getClass().getSimpleName(), ioe.getMessage());
//        exceptionMap.put(FeignConsts.ELAPSED_TIME, elapsedTime);
//        if (logLevel.ordinal() >= Level.FULL.ordinal()) {
//            StringWriter sw = new StringWriter();
//            ioe.printStackTrace(new PrintWriter(sw));
//            exceptionMap.put(FeignConsts.STACK, sw.toString());
//            logger.error(String.format(methodTag(configKey) + "%s", sw.toString()));
//        }
//        customerErrorLog(FeignConsts.FEIGN_EXCEPTION, configKey, exceptionMap);
//        return ioe;
//    }
//
//    private void customerErrorLog(String requestType, String configKey, Map<String, Object> requestMap) {
//        if (logger.isInfoEnabled()) {
//            logger.error(requestType + FeignConsts.RECORD_SPLIT + configKey + "-" + JSON.toJSONString(requestMap));
//        }
//    }
//
//    private void saveFeignResponseLog(String configKey, Response response, byte[] bodyData) {
//
//        try {
//            final Boolean flag = checkIsEnableLog(configKey);
//
//            // 打印cop feign client 日志
//            if (flag) {
//                final ApiOwnerEnum apiOwner = getApiOwner(configKey);
//
//                // response 信息
//                final int status = response.status();
//                final Response.Body responseBody = response.body();
//                final Map<String, Collection<String>> responseHeader = response.headers();
//
//                // traceId 以及 requestId
//                final TraceInfo traceInfo = TraceUtil.getTraceInfo();
//
//                final RequestLog requestLog = FeignLogThreadLocal.get();
//                if (traceInfo != null) {
//                    requestLog.setRequestId(traceInfo.getRequestId());
//                    requestLog.setTraceId(traceInfo.getTraceId());
//                }
//
//                // 设置response
//                requestLog.setHttpStatus(String.valueOf(status));
//                requestLog.setResponseHeaders(JSON.toJSONString(responseHeader));
//                if (response.body().isRepeatable() && CheckUtils.checkBodySize(responseBody.length())) {
//                    requestLog.setResponseBody(new String(Util.toByteArray(responseBody.asInputStream()),
//                            StandardCharsets.UTF_8));
//                }else if(!response.body().isRepeatable() && bodyData != null && bodyData.length >0){
//                    requestLog.setResponseBody(new String(bodyData, StandardCharsets.UTF_8));
//                }
//                requestLog.setResponseTime(new Date().getTime() - requestLog.getBeginTime().getTime());
//                requestLog.setResponseResult(status == 200 ? "success" : "failed");
//                requestLog.setEndTime(new Date());
//                requestLog.setApiOwner(apiOwner.name());
//                RequestLogUtils.log(requestLog);
//            }
//        } catch (Exception e) {
//            logger.error("记录 {} 日志发生异常",configKey, e);
//        }finally {
//            FeignLogThreadLocal.remove();
//        }
//
//    }
//
//    /**
//     * 记录request 日志
//     * @param configKey
//     * @param request
//     */
//    private void saveFeignRequestLog(String configKey,Request request){
//
//        final Boolean enableLog = checkIsEnableLog(configKey);
//        if(enableLog){
//            // request 信息
//            final String httpMethod = request.httpMethod().name();
//            final String url = request.url();
//            final Map<String, Collection<String>> headers = request.headers();
//            final Collection<String> userAgentCollection = headers.get(FeignConsts.USER_AGENT);
//            final Collection<String> contentTypeCollection = headers.get(FeignConsts.CONTENT_TYPE);
//            final byte[] body = request.body();
//
//            final RequestLog requestLog = new RequestLog();
//
//            // 设置request
//            requestLog.setRequestType(ResultTypeEnum.grpc.name());
//            requestLog.setRequestUrl(url);
//            requestLog.setHttpMethod(httpMethod);
//            requestLog.setContentType(CollectionUtils.isEmpty(contentTypeCollection) ? "" : contentTypeCollection.toString());
//            requestLog.setUserAgent(CollectionUtils.isEmpty(userAgentCollection) ? "" : userAgentCollection.toString());
//            requestLog.setBeginTime(new Date());
//            requestLog.setRequestHeaders(JSON.toJSONString(headers));
//            if (!request.isBinary() && CheckUtils.checkBodySize(body.length)) {
//                requestLog.setRequestBody(new String(body, StandardCharsets.UTF_8));
//            }
//            FeignLogThreadLocal.set(requestLog);
//        }
//    }
//
//
//    /**
//     * 检查是否记录feign 日志
//     * @param configKey feign标识
//     * @return
//     */
//    private Boolean checkIsEnableLog(String configKey){
//        Boolean flag = Boolean.FALSE;
//        for (String s1 : FeignConsts.COP_FEIGN_CLIENT_LIST) {
//            if (configKey.startsWith(s1)) {
//                flag = Boolean.TRUE;
//                return flag;
//            }
//        }
//
//        for (String s1 : FeignConsts.CHINA_UNICOM_FEIGN_CLIENT_LIST) {
//            if (configKey.startsWith(s1)) {
//                flag = Boolean.TRUE;
//                return flag;
//            }
//        }
//
//        for (String s : FeignConsts.CUSC_FEIGN_CLIENT_LIST) {
//            if (configKey.startsWith(s)) {
//                flag = Boolean.TRUE;
//                return flag;
//            }
//        }
//        return flag;
//    }
//
//    private ApiOwnerEnum getApiOwner(String configKey){
//
//        for (String s1 : FeignConsts.COP_FEIGN_CLIENT_LIST) {
//            if (configKey.startsWith(s1)) {
//                return ApiOwnerEnum.cop;
//            }
//        }
//
//        for (String s1 : FeignConsts.CHINA_UNICOM_FEIGN_CLIENT_LIST) {
//            if (configKey.startsWith(s1)) {
//                return ApiOwnerEnum.chinaunicom;
//            }
//        }
//
//        for (String s : FeignConsts.CUSC_FEIGN_CLIENT_LIST) {
//            if (configKey.startsWith(s)) {
//                return ApiOwnerEnum.cusc;
//            }
//        }
//        return null;
//    }
//
//}
