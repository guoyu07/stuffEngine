package ru.technoserv.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

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

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        // @EnableWs, @Configuration, @ComponentScan
        context.setConfigLocation(SoapServiceConfig.class.getName());

        // use MessageDispatcherServlet instead of standard DispatcherServlet for SOAP messages
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        // register MessageDispatcherServlet as Web Service entry point
        final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("MessageDispatcherServlet",
                servlet);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/ws");
    }

}
