package com.hello.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //настройка корневого контекста. Здесь инфраструктурные бины, которые будут доступны всем DispatcherServlet'ам
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{HibernateConfig.class, SecurityConfig.class, ServiceConfig.class};
    }

    //Настройка контекста, который относится к отдельному DispatcherServlet'y
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //настройка маппинга DispatcherServlet'a
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
