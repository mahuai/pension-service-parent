package com.lingyue.usercenter.feign.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * userserivce(cop)/register/sms响应
 * <p/>
 *
 * @author cuiwei
 * @date 2022/7/12 10:47
 */
@Getter
@Setter
public class UserServiceRegisterDTO {

    private String apiToken;
    private String loginToken;
    private Long apiTokenExpire;
    private Long loginTokenExpire;
}
