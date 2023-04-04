package com.pension.params;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: mashuai
 * @Description 用户注册请求参数
 * @Version 1.0
 * @Date: 2023-04-04 10:12
 */
@Data
@Builder
public class CustomerRegisterParams {

    @NotBlank(message = "用户名称不能为空")
    private String userName;
    @NotBlank(message = "用户密码不能为空")
    private String password;
    @NotBlank(message = "用户地址不能为空")
    private String address;
    @NotBlank(message = "用户电话不能为空")
    private String telephone;
}
