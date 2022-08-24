package com.turing.onebox.common.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName SaTokenConfigure
 * @Description SaToken 权限配置
 * @Author Colin
 * @Date 2022/8/24 15:33
 * @Version 1.0
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册权限校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/login");
    }
}
