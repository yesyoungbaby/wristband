package cn.bookcycle.wristband.servicedemo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yesyoungbaby
 * @Title: ServiceDemoApplication
 * @ProjectName wristband
 * @Description: TODO
 * @date 2018/11/615:55
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableCircuitBreaker
@EnableHystrix
public class ServiceDemoApplication {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "jayChou") String name) {
        return "hi! " + name + " ,i am from port:" + port+".You are testing the SpringCloud framework.";
    }

    public String hiError(String name){
        return "sorry! "+name+"  服务器发生错误！ 来自Hystrix Dashboard的检测。";
    }

    public static void main(String[] args) {
        SpringApplication.run( ServiceDemoApplication.class, args );
    }
}
