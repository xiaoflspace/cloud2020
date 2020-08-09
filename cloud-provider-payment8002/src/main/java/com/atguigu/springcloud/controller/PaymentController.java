package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String SERVER_PORT;
    @PostMapping(value="/create")
    public CommonResult create(@RequestBody Payment payment){//@RequestBody接受json串并传递给对于的bean对象或者对于参数
        int result = paymentService.create(payment);
        log.info("****插入结果："+result);

        if(result>0){
            return new CommonResult(200,"插入成功",result);
        }else{
            return new CommonResult(444,"插入失败",null);
        }
    }
    @GetMapping(value="/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        System.out.println("1111");
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果："+payment);

        if(payment!=null){
            return new CommonResult(200,"查询成功,"+SERVER_PORT,payment);
        }else{
            return new CommonResult(444,"没有对于纪录，查询ID："+id,null);
        }
    }
    @GetMapping("/lb")
    public String getPaymentLB() {
        return SERVER_PORT;
    }
}
