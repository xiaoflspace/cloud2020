package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sunhao
 * @date 2020/7/11 11:17
 */
@SpringBootApplication
@EnableEurekaServer    //此注解不加访问7002时会报404错误
public class EurekaMain7002 {
    public static void main(String[] args){
        SpringApplication.run(EurekaMain7002.class,args);
    }
}
