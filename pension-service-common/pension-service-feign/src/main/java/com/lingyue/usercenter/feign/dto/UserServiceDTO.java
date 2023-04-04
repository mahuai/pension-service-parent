package com.lingyue.usercenter.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * userservice响应基类
 * <p/>
 *
 * @author cuiwei
 * @date 2022/7/12 10:39
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceDTO<T> {

    private boolean ok;
    private String msg;
    private T data;
    private int code;
}
