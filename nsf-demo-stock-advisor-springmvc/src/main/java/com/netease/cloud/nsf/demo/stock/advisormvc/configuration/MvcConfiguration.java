package com.netease.cloud.nsf.demo.stock.advisormvc.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@PropertySource(value= {"classpath:application.properties"})
public class MvcConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
    
    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
