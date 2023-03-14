package com.duoer.campus.web.interceptor;

import com.alibaba.fastjson2.JSON;
import com.duoer.campus.BaseContext;
import com.duoer.campus.entity.User;
import com.duoer.campus.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 添加管理员权限拦截器，用于拦截非管理员用户访问管理员资源的请求
 *
 * @author duoer
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取当前的session，若其isAdmin属性为false或null，则拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        System.out.println(token);

        String userJSON = JwtUtils.parseJWT(token);
        if (StringUtils.isEmpty(userJSON)) {
            throw new JwtException("not admin");
        }

        userJSON = redisTemplate.opsForValue().get("user_token_" + token);
        if (StringUtils.isEmpty(userJSON)) {
            throw new JwtException("not login");
        }

        User user = JSON.parseObject(userJSON, User.class);
        if (user == null || !user.getIsAdmin()) {
            throw new JwtException("not admin");
        }

        BaseContext.set(user);
        return true;
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
