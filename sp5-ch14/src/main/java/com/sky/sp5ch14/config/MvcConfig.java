package com.sky.sp5ch14.config;

import com.sky.sp5ch14.interceptor.AuthCheckInterceptor;
import com.sky.sp5ch14.interceptor.LoggerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthCheckInterceptor authCheckInterceptor;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/register/**")
                .excludePathPatterns("/practice/**");

        registry.addInterceptor(authCheckInterceptor)
                .addPathPatterns("/edit/**");

    }
}
