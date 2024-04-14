package stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import stock.StockService;


@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	StockService stockService;

	@GetMapping("/{productNumber}")
	public ResponseEntity<?> getStock(@PathVariable("productNumber") String productNumber) {
		int stock =stockService.getNumberOnStock(productNumber);
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createOrUpdateStock(@RequestBody Stock stock) {
		Stock s =stockService.createStock(stock);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@DeleteMapping("/{productNumber}")
	public ResponseEntity<?> deleteStock(@PathVariable("productNumber") String productNumber) {
		stockService.deleteStock(productNumber);
		return ResponseEntity.ok().build();
	}
}
