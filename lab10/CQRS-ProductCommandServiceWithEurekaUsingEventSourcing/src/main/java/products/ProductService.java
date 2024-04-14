package products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import products.event.EventRepository;
import products.event.ProductCreatedEvent;

@Service
public class ProductService {
	@Autowired
	StockFeignClient stockClient;

	@Autowired
	ProductRepository repository;

	@Autowired
	JmsSender sender;

	@Autowired
	EventRepository eventRepository;

	public Product createProduct(Product product) {
		sender.sendMessage(product, "CREATION");
		eventRepository.save(new ProductCreatedEvent(product.getProductNumber(), product));
		return repository.save(product);
	}

	public void removeProduct(String productNumber) {
		Product product = repository.findByProductNumber(productNumber);
		eventRepository.save(new ProductCreatedEvent(productNumber, product));
		repository.deleteByProductNumber(productNumber);
	}

	@FeignClient("StockService")
	interface StockFeignClient {
		@RequestMapping("/stock/{productNumber}")
		public Integer getStock(@PathVariable("productNumber") String productNumber);
	}

}
