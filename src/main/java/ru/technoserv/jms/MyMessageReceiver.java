package ru.technoserv.jms;

import javax.jms.JMSException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;


//@Component
public class MyMessageReceiver {

    static final Logger LOG = LoggerFactory.getLogger(MyMessageReceiver.class);

    private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";



   // @JmsListener(destination = "test")
    public void receiveMessage(final Message<String> message) throws JMSException {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}", headers);

        System.out.println("+++++++++++++++++++++\nGot message, YAHOOO!!");



        System.out.println("+++++++++++++++++++++\nRead!!");
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}