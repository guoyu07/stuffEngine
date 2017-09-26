package ru.technoserv.config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.EndpointMapping;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping;
import org.springframework.ws.soap.server.SoapMessageDispatcher;
import ru.technoserv.endpoint.EmployeeEndpoint;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJms
@ComponentScan("ru")
public class MessagingListnerConfiguration {

    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1");
        return factory;
    }

    @Bean
    public SoapMessageDispatcher soapMessageDispatcher(){
        SoapMessageDispatcher dispatcher =new SoapMessageDispatcher();
        dispatcher.setEndpointMappings(getMapping());
        return dispatcher;
    }

    public List<EndpointMapping> getMapping(){
        List<EndpointMapping> mapping = new ArrayList();
        PayloadRootAnnotationMethodEndpointMapping enpointRoot = new PayloadRootAnnotationMethodEndpointMapping();
        enpointRoot.setDefaultEndpoint(new EmployeeEndpoint());
        return mapping;

    }
}