package products;

import feign.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		Product p =productService.createProduct(product);
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}

	@DeleteMapping("/{productNumber}")
	public ResponseEntity<?> deleteProduct(@PathVariable("productNumber") String productNumber) {
		productService.removeProduct(productNumber);
		return ResponseEntity.ok().build();
	}
}
