package cn.bookcycle.wristband.serviceconsumer.controller;

import cn.bookcycle.wristband.serviceconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yesyoungbaby
 * @Title: HelloController
 * @ProjectName MavenModule
 * @Description: TODO
 * @date 2018/10/3016:19
 */
@RestController
public class HelloController {

    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    HelloService helloService;

    @GetMapping(value = "/feignConsume")
    public String sayHi(@RequestParam(value = "name",defaultValue = "Taylor Swift") String name) {
        return helloService.sayHiFromClientOne(name);
    }
}
