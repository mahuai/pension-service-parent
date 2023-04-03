package com.pension.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void register(){

    }
}
