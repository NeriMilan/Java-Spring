package com.shangma.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInit  extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 加载间接依赖容器中的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MapperConfig.class,ServiceConfig.class};
    }

    // 加载直接依赖容器中的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // 设置dispatcherServlet的路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
