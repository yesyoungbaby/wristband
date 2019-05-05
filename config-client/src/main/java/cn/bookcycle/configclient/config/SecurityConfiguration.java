package cn.bookcycle.configclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yesyoungbaby
 * @Title: SecurityConfiguration
 * @ProjectName config-client
 * @Description: 用于手动刷新配置
 * @date 2018/11/2215:09
 */
//不加这个注解，会弹出用户名密码登录
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //用于关闭端点的安全校验
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
    }
}
