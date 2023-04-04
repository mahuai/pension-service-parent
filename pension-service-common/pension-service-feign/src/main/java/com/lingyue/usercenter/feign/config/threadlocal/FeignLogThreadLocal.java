//package com.lingyue.usercenter.feign.config.threadlocal;
//
//import com.lingyue.usercenter.logging.http.RequestLog;
//
///**
// * @author zhanghengrui
// * @date 2022/11/24
// */
//public class FeignLogThreadLocal {
//
//    private FeignLogThreadLocal(){}
//
//    public static ThreadLocal<RequestLog> logThreadLocal = ThreadLocal.withInitial(RequestLog::new);
//
//
//    public static void set(RequestLog requestLog) {
//        logThreadLocal.set(requestLog);
//    }
//    public static RequestLog get() {
//        return logThreadLocal.get();
//    }
//    public static void remove() {
//        logThreadLocal.remove();
//    }
//
//
//
//}
