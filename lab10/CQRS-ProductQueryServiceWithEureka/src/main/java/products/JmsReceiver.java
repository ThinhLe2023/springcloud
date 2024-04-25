package products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ProductService service;

    @JmsListener(destination = "productQueue")
    public void receiveProductMsg(String message)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(message, Product.class);
            System.out.println("Receiving a JMS message : " + product);
            service.saveProduct(product);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
    }

    @JmsListener(destination = "stockQueue")
    public void receiveStockMsg(String message)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Stock stock = objectMapper.readValue(message, Stock.class);
            System.out.println("Receiving a Stock JMS message : " + stock);
            Product product = service.findByProductNumber(stock.getProductNumber());
            product.setQuantity(stock.getQuantity());
            product.setProductNumber(stock.getProductNumber());
            System.out.println("Insert a product message : " + product);
            service.saveProduct(product);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
    }
}
