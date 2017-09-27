package ru.technoserv.jms;

import javax.jms.JMSException;
import javax.xml.soap.*;

import com.sun.xml.internal.ws.handler.SOAPMessageContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.server.SoapMessageDispatcher;
import ru.technoserv.endpoint.EmployeeEndpoint;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;


@Component
public class MyMessageReceiver {

    static final Logger LOG = LoggerFactory.getLogger(MyMessageReceiver.class);

    private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";




    public void receiveMessage(final Message<String> message) throws JMSException {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}", headers);

        System.out.println("+++++++++++++++++++++\nGot message, YAHOOO!!");
        //soapMessageDispatcher.receive();
        //someAction

        System.out.println("+++++++++++++++++++++\nRead!!");
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}