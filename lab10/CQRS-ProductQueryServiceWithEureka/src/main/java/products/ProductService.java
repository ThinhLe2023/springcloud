package products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class ProductService {
	@Autowired
	StockFeignClient stockClient;

	@Autowired
	ProductRepository repository;

	public void saveProduct(Product p){
		repository.save(p);
	}

	int getQuantityByProductNumber(String productNumber){
		return stockClient.getStock(productNumber);
	}

	public Product findByProductNumber(String productNumber) {
		return repository.findByProductNumber(productNumber);
	}

	@FeignClient("StockService")
	interface StockFeignClient {
		@RequestMapping("/stock/{productNumber}")
		public Integer getStock(@PathVariable("productNumber") String productNumber);
	}

}
