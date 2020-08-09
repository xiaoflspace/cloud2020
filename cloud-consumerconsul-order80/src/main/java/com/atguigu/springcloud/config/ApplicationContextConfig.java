package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author sunhao
 * @date 2020/7/14 21:06
 * @description: 配置类
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced //开启负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
