package ru.technoserv.config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.server.EndpointMapping;
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.server.SoapMessageDispatcher;
import org.springframework.ws.transport.jms.WebServiceMessageListener;
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
        return dispatcher;
    }

    @Bean
    public WebServiceMessageListener getListener(){
        WebServiceMessageListener listener = new WebServiceMessageListener();
        listener.setMessageFactory(messageFactory());
        listener.setMessageReceiver(dispatcher());
        return listener;
    }

    @Bean
    public WebServiceMessageFactory messageFactory(){
        return new SaajSoapMessageFactory();
    }

    @Bean
    public SoapMessageDispatcher dispatcher(){
        SoapMessageDispatcher dispatcher = new SoapMessageDispatcher();
        List<EndpointMapping> list = new ArrayList<>();
        PayloadRootAnnotationMethodEndpointMapping map = new PayloadRootAnnotationMethodEndpointMapping();
        map.setDefaultEndpoint(new EmployeeEndpoint());
        list.add(map);
        dispatcher.setEndpointMappings(list);


        return dispatcher;
    }
}