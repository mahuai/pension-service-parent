package com.lingyue.usercenter.feign.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * userservice(cop)/register/sms/send响应
 * <p/>
 *
 * @author cuiwei
 * @date 2022/7/12 10:43
 */
@Getter
@Setter
public class UserServiceSmsSendDTO {

    private String otpID;
    private String validUntil;
}
