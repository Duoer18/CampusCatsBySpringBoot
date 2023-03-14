package com.duoer.campus.web.interceptor;

import com.alibaba.fastjson2.JSON;
import com.duoer.campus.BaseContext;
import com.duoer.campus.entity.User;
import com.duoer.campus.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录校验拦截器，用于拦截未登录游客的增删改请求
 *
 * @author duoer
 */
@Component
public class LogInInterceptor implements HandlerInterceptor {
    /**
     * 首先获取请求方式，若为get且不以user结尾，则直接放行；
     * 否则，获取当前session，若其username属性为空，则拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        System.out.println(token);

        String method = request.getMethod();
        if (method.equalsIgnoreCase("GET") && !request.getRequestURI().endsWith("user")) {
            return true;
        } else {
            String userJSON = JwtUtils.parseJWT(token);
            if (StringUtils.isEmpty(userJSON)) {
                throw new JwtException("not login");
            }
            User user = JSON.parseObject(userJSON, User.class);
//            HttpSession session = request.getSession();
//            boolean signed = session.getAttribute("username") != null;
//            if (!signed) {
//                response.sendRedirect("/CampusCats/login");
//            }
            BaseContext.set(user);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        BaseContext.remove();
    }
}
