package products;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {
    void deleteByProductNumber(String productNumber);

    Product findByProductNumber(String productNumber);
}
