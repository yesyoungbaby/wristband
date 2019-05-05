package cn.bookcycle.wristband.serviceconsumer.hystrix;

import cn.bookcycle.wristband.serviceconsumer.service.HelloService;
import org.springframework.stereotype.Component;

/**
 * @author yesyoungbaby
 * @Title: SchedualServiceHiHystrix
 * @ProjectName MavenModule
 * @Description: feign的熔断器  @Component的作用？
 * @date 2018/10/3017:00
 */
@Component
public class HelloServiceHystrix implements HelloService {

    /**
        这里需要：
        1.配置文件开启feign.hystrix.enabled=true
        2.写这个类
        3.在HelloService的@FeignClient中指定 fallback

        当对特定的服务的调用的不可用达到一个阀值（Hystric 是5秒20次）
        断路器将会被打开。
        断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。
     */

    @Override
    public String sayHiFromClientOne(String name) {
        return "请求的服务不可用";
    }
}
