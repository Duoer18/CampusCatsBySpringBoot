package com.duoer.campus.config;

import com.duoer.campus.web.interceptor.LogInInterceptor;
import com.duoer.campus.web.interceptor.NoCacheInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LogInInterceptor logInInterceptor;
    @Autowired
    private NoCacheInterceptor noCacheInterceptor;

    /**
     * 添加登录校验拦截器，用于拦截未登录游客的增删改请求（根据请求方式进行拦截，具体方案见LoginInterceptor）；
     * 添加管理员权限拦截器，用于拦截非管理员用户访问管理员资源的请求
     * 添加禁用缓存拦截器，防止管理员页面和获取的用户信息被缓存
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInInterceptor)
                .addPathPatterns("/feedings/**", "/appearances/**", "/cats/**");
        registry.addInterceptor(noCacheInterceptor)
                .addPathPatterns("/pages/admin/*", "/users");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
