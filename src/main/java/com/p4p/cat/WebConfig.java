package com.p4p.cat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward the requests to the frontend to be handled by react router
        registry.addViewController("/instruction")
                .setViewName("forward:/index.html");
        registry.addViewController("/test")
                .setViewName("forward:/index.html");
        registry.addViewController("/end")
                .setViewName("forward:/index.html");
    }
}
