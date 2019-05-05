package cn.bookcycle.wristband.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yesyoungbaby
 * @Title: MyFilter
 * @ProjectName MavenModule
 * @Description: 实现接口拦截，强制加token
 * @date 2018/10/3019:51
 */
@Component
public class TokenFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(TokenFilter.class);

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
         pre：路由之前
         routing：路由之时
         post： 路由之后
         error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     *
     * run：过滤器的具体逻辑。可以很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();

        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

      /*
        String token = request.getParameter("token");
        if(!"12345".equals(token)){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                context.getResponse().getWriter().write("token is invalid.");
            }catch (Exception e){}
        }
       */

        Object accessToken = request.getParameter("token");//
        if(accessToken == null){
            log.warn("token is empty!");
            rc.setSendZuulResponse(false);
            rc.setResponseStatusCode(401);
            try{
                rc.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return null;
        }

        //只要token不为空，就能通过拦截
        log.info("ok");
        return null;
    }
}
