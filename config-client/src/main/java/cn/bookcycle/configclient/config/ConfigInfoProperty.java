package cn.bookcycle.configclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author yesyoungbaby
 * @Title: ConfigInfoProperty
 * @ProjectName config-client
 * @Description: 用于自动刷新
 * @date 2018/11/2215:17
 */
@Component
@RefreshScope
public class ConfigInfoProperty {
    @Value("${cn.springcloud.book.config}")
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
