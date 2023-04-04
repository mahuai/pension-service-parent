package com.lingyue.usercenter.feign.config.logger;

import java.util.Arrays;
import java.util.List;

/**
 * @author Andy
 * @title: FeignConsts
 * @description:
 * @date 2020/7/3 16:47
 */
public class FeignConsts {

    public final static String METHOD = "Method-Type";
    public final static String URL = "URL";

    public final static String PARAMETERS = "Parameters";
    public final static String BODY = "BODY";

    public final static String BODY_LENGTH = "Body-Length";

    public final static String STATUS = "status";
    public final static String CODE = "code";
    public final static String TIME = "time";
    public final static String ELAPSED_TIME = "elapsedTime";
    public final static String STACK = "stack";

    public final static String FEIGN_REQUEST = "FEIGNREQUEST";
    public final static String FEIGN_STATUS = "FEIGN_STATUS";
    public final static String FEIGN_RESPONSE = "FEIGNRESPONSE";
    public final static String FEIGN_EXCEPTION = "FEIGNEXCEPTION";

    public final static String RECORD_SPLIT = ":";

    /**
     * cop feign client 客户端集合
     */
    public static final List<String> COP_FEIGN_CLIENT_LIST = Arrays.asList("MessageService", "UserServiceClient");

    /**
     * 联通 T1 unicom feign client 客户端集合
     */
    public static final List<String> CHINA_UNICOM_FEIGN_CLIENT_LIST = Arrays.asList("UniComClient");

    /**
     * 联通 cusc feign client 客户端集合
     */
    public static final List<String> CUSC_FEIGN_CLIENT_LIST = Arrays.asList("CuscClient");

    public static final String USER_AGENT = "User-Agent";

    public static final String CONTENT_TYPE = "Content-Type";

}
