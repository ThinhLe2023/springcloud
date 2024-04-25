package products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(Product p, String action)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String contactString = objectMapper.writeValueAsString(p);
            System.out.println("Sending a JMS message when " + action +" with book:  "+ contactString);
            jmsTemplate.convertAndSend("productQueue", contactString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
