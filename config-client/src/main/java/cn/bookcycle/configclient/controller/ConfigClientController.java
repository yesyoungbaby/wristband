package cn.bookcycle.configclient.controller;

import cn.bookcycle.configclient.config.ConfigInfoProperties;
import cn.bookcycle.configclient.config.ConfigInfoProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yesyoungbaby
 * @Title: ConfigClientController
 * @ProjectName config-client
 * @Description: TODO
 * @date 2018/11/1816:51
 */
@RefreshScope
@RestController
public class ConfigClientController {

//自动刷新前
    @Autowired
    private ConfigInfoProperties configInfoProperties;
    @RequestMapping("/getConfigInfo")
    public String getConfigInfo(){
        return configInfoProperties.getConfig();
    }

    @Value("${cn.springcloud.book.config}")
    private String config;
    @RequestMapping("/anotherWay")
    public String get(){
        return this.config;
    }

//自动刷新
    @Autowired
    private ConfigInfoProperty configInfoValue;
    @RequestMapping("/getConfigValue")
    public String getInfo(){
        return configInfoValue.getConfig();
    }


}
