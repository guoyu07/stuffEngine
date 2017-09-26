package ru.technoserv.config;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class SoapMvcConfig extends AbstractAnnotationConfigMessageDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SoapServiceConfig.class };
    }

    @Override
    protected String getServletName() {
        return "soap";
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/ws/*" };
    }
}
