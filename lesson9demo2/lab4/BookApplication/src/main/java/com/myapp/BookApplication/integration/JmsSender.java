package com.myapp.BookApplication.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.BookApplication.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(Book contact, String action)  {
        try {
            //convert book to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String contactString = objectMapper.writeValueAsString(contact);
            System.out.println("Sending a JMS message when " + action +" with book:  "+ contactString);
//            jmsTemplate.convertAndSend("testQueue", contactString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
