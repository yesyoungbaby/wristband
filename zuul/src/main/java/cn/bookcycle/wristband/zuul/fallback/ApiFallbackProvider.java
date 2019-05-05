package cn.bookcycle.wristband.zuul.fallback;

import org.springframework.stereotype.Component;
//import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;

/**
 * @author yesyoungbaby
 * @Title: ApiFallbackProvider
 * @ProjectName wristband
 * @Description: 某一个服务挂掉或者请求不到的时候，如果不做任何处理，服务网关请求不到会抛出500错误，对用户不友好。
 *                 为了返回友好性提示，zuul为我们提供了一个名叫 ZuulFallbackProvider的接口，通过它我们就可以对这些请求不到的服务进行错误处理。
 * @date 2018/11/1611:39
 */
/*@Component
public class ApiFallbackProvider implements ZuulFallbackProvider{

ZuulFallbackProvider找不到

    //getRoute 方法返回要处理错误的服务名，fallbackResponse 方法返回错误的处理规则。


}*/
