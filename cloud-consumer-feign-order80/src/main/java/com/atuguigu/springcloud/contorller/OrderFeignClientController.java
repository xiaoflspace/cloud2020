package com.atuguigu.springcloud.contorller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atuguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunhao
 * @date 2020/7/15 21:10
 * @description:
 */
@RestController
@Slf4j
public class OrderFeignClientController {
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeount(){
        return paymentFeignService.paymentFeignTimeount();
    }
}
