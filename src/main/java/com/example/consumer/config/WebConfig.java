package com.example.consumer.config;

import com.example.consumer.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/web/login", "/web/logout",
                        "css/**", "/*.ico", "/error", "/login");
    }
}
