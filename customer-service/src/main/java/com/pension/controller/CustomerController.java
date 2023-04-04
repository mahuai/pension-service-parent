package com.pension.controller;

import com.pension.params.CustomerRegisterParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: mashuai
 * @Description
 * @Version 1.0
 * @Date: 2023-04-03 17:12
 */
@RestController
@RequestMapping(value = "customer/v1")
public class CustomerController {

    @PostMapping("/register")
    public void register(@Validated  @RequestBody CustomerRegisterParams params){

    }
}
