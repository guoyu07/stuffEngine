package ru.technoserv.main;

import com.sun.xml.internal.ws.transport.http.server.EndpointImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.jms.spec.JMSSpecConstants;
import ru.technoserv.ws.HelloWorld;
import ru.technoserv.ws.HelloWorldImpl;

import javax.jms.ConnectionFactory;
import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args){
        String address = "jms:jndi:dynamicQueues/AratQueue"
                + "?jndiInitialContextFactory"
                + "=org.apache.activemq.jndi.ActiveMQInitialContextFactory"
                + "&jndiConnectionFactoryName=ConnectionFactory&jndiURL=tcp://localhost:61616";
        HelloWorldImpl implementor = new HelloWorldImpl();
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(HelloWorld.class);
        svrFactory.setAddress(address);
// And specify the transport ID with SOAP over JMS specification
        svrFactory.setTransportId(JMSSpecConstants.SOAP_JMS_SPECIFICATION_TRANSPORTID);
        svrFactory.setServiceBean(implementor);
        svrFactory.create();
    }

}
