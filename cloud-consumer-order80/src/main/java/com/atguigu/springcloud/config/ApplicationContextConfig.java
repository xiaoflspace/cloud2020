package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced//赋予RestTemplate负载均衡的能力（不然无法通过微服务名称访问对于的服务）
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
