package com.lingyue.usercenter.feign.config;

import feign.RetryableException;
import feign.Retryer;

import java.util.concurrent.TimeUnit;

/**
 * 定义重试
 */
public class DefaultRetryer implements Retryer {

    /**
     * 异常时的处理机制
     * @param e 异常
     */
    @Override
    public void continueOrPropagate(RetryableException e) {
        throw e;
    }

    /**
     * 克隆请求
     * @return Retryer
     */
    @Override
    public Retryer clone() {
        return new Default(100, TimeUnit.SECONDS.toMillis(1), 5);
    }
}
