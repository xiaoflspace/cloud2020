package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author sunhao
 * @date 2020/7/14 21:09
 * @description:
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderConsulController {
    private static final String INVOKE_URL = "http://consul-provider-payment";
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/payment/consul")
    public String get() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }
}
