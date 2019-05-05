package cn.bookcycle.configclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yesyoungbaby
 * @Title: ConfigInfoProperties
 * @ProjectName config-client
 * @Description: 用于注入远程配置上的信息
 * @date 2018/11/1816:52
 */
@Component
@ConfigurationProperties(prefix = "cn.springcloud.book") //ConfigurationProperties加载配置属性，指定配置前缀。
public class ConfigInfoProperties {
    private  String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
