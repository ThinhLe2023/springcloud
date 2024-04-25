package stock;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, Long> {
    Stock findByProductNumber(String productNumber);

    void deleteByProductNumber(String productNumber);
}
