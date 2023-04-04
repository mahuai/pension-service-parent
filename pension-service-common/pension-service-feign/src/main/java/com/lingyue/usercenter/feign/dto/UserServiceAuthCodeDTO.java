package com.lingyue.usercenter.feign.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * userservice(cop)/auth/code响应
 * <p/>
 *
 * @author cuiwei
 * @date 2022/7/12 10:44
 */
@Getter
@Setter
public class UserServiceAuthCodeDTO {

    private String value;
    private String expires;
}
