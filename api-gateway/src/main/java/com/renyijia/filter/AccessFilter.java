package com.renyijia.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-15
 * @email : zhou_wenya@163.com
 */
public class AccessFilter extends ZuulFilter {

    private List<String> urlGreens = Arrays.asList("/login");


    /**
     * 过滤器类型 pre:前置
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行改该过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String servletPath = request.getServletPath();
        if (urlGreens.stream().filter(s -> servletPath.contains(s)).count() == 0) {
            //从header中获取token
            String token = request.getHeader("token");
            //如果header中不存在token，则从参数中获取token
            if (StringUtils.isBlank(token)) {
                token = request.getParameter("token");
            }
            //token为空
            if (StringUtils.isBlank(token)) {
                theCorresponding(ctx);
                return null;
            }
        }
        ctx.setSendZuulResponse(true);
        return null;
    }

    /**
     * 异常返回
     *
     * @param ctx
     */
    private void theCorresponding(RequestContext ctx) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
    }
}
