package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.rules.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String SERVER_PORT;
    @Resource
    private EurekaDiscoveryClient discoveryClient;//服务发现。就是关于服务，获取服务相关信息的对象
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
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果："+payment);

        if(payment!=null){
            return new CommonResult(200,"查询成功,"+SERVER_PORT,payment);
        }else{
            return new CommonResult(444,"没有对于纪录，查询ID："+id,null);
        }
    }
    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******element: "+ service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/lb")
    public String getPaymentLB() {
        return SERVER_PORT;
    }
    @GetMapping("/feign/timeout")
    public String paymentFeignTimeount(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return SERVER_PORT;
    }
}
