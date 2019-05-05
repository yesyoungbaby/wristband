package cn.bookcycle.wristband.serviceconsumer.service;

import cn.bookcycle.wristband.serviceconsumer.hystrix.HelloServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yesyoungbaby
 * @Title: HelloService
 * @ProjectName MavenModule
 * @Description: 定义一个feign接口
 *                 通过@ FeignClient（“服务名”），来指定调用哪个服务。
 * @date 2018/10/3016:11
 */
@FeignClient(value = "service-hi",fallback = HelloServiceHystrix.class)
public interface HelloService {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
