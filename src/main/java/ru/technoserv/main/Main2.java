package ru.technoserv.main;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.jms.spec.JMSSpecConstants;
import ru.technoserv.ws.HelloWorld;

public class Main2 {

    public static void main(String[] args){
        // You just need to set the address with JMS URI
        String address = "jms:jndi:dynamicQueues/AratQueue"
                + "?jndiInitialContextFactory=org.apache.activemq.jndi.ActiveMQInitialContextFactory"
                + "&jndiConnectionFactoryName=ConnectionFactory"
                + "&jndiURL=tcp://localhost:61616";
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        // And specify the transport ID with SOAP over JMS specification
        factory.setTransportId(JMSSpecConstants.SOAP_JMS_SPECIFICATION_TRANSPORTID);
        factory.setServiceClass(HelloWorld.class);
        factory.setAddress(address);
        HelloWorld client = (HelloWorld)factory.create();
        String reply = client.sayHello("Arat");
        System.out.println(reply);

    }
}
