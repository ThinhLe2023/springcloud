package stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	JmsSender sender;
	public int getNumberOnStock(String productNumber) {
		return stockRepository.findByProductNumber(productNumber).getQuantity();
	}

	public Stock createStock(Stock stock) {
		Stock existStock = stockRepository.findByProductNumber(stock.getProductNumber());
		if(existStock != null){
			existStock.setQuantity(stock.getQuantity());
			sender.sendMessage(existStock, "UPDATION");
			return stockRepository.save(existStock);
		}
		sender.sendMessage(stock, "CREATION");
		return stockRepository.save(stock);
	}

	public void deleteStock(String productNumber) {
		stockRepository.deleteByProductNumber(productNumber);
	}
}
