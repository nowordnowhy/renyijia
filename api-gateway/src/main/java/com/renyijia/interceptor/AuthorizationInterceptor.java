package com.renyijia.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.renyija.common.utils.RedisUtils;
import com.renyijia.annotation.AuthRequired;
import com.renyijia.common.exception.RRException;
import entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-07-17
 * @email : zhou_wenya@163.com
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String origin = request.getHeader("Origin");
		/*System.err.println("preHandle--->origin==="+origin);
		String domain = request.getServerName();
		System.err.println("preHandle--->domain===="+domain);*/
        //允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.addHeader("Access-Control-Allow-Headers", "api-key, authorization, Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
		/*if(StringUtils.equals("/user/login", request.getRequestURI())){
			System.err.println("preHandle------------------getRequestURI:"+request.getRequestURI());
		}*/

        try {

            if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                return true;
            }

            AuthRequired authRequired = ((HandlerMethod) handler).getMethodAnnotation(AuthRequired.class);
            if (authRequired == null) {
                return true;
            }


            //从header中获取token
            String token = request.getHeader("token");
            //如果header中不存在token，则从参数中获取token
            if (StringUtils.isBlank(token)) {
                token = request.getParameter("token");
            }

            //token为空
            if (StringUtils.isBlank(token)) {
                throw new RRException("token不能为空", org.apache.http.HttpStatus.SC_UNAUTHORIZED);
            }
            //查询token信息
            String userInfo = redisUtils.get(token);
            //System.err.println("preHandle------token:"+token);
            if (StringUtils.isBlank(token)) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "请先登录认证");
            }

//            UserInfo userInfo = CasUtil.getUserInfo(token);

            if (userInfo == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "请先登录认证");
            } else {
                User user = JSONObject.parseObject(userInfo, User.class);

//                String authStr = authRequired.auth();
                //System.err.println("authStr===="+authStr);
                return true;
     	        /*boolean flag = authUtil.checkAuth(userInfo.getUserId(), authStr);
     	        if(flag){
     	        	return true;
     	        }else{
     	        	response.sendError(HttpStatus.UNAUTHORIZED.value(),"您没有权限");
     	        }*/
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
