package ru.technoserv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//@EnableWs
//@Configuration
//@ComponentScan(value = "ru")
public class SoapServiceConfig extends WsConfigurerAdapter {

    @Bean(name = "empl")
    public DefaultWsdl11Definition employee(XsdSchema employeeSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(employeeSchema);

        definition.setPortTypeName("EmployeesPort");
        definition.setLocationUri("/employee/ws/");
        definition.setTargetNamespace("http://tscintern.ru/employees");

        return definition;
    }

    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("employees.xsd"));
    }

    @Bean
    public WebServiceMessageFactory messageFactory(){
        WebServiceMessageFactory factory = new SaajSoapMessageFactory();
        return factory;
    }

}
