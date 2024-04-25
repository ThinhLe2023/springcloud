package products.event;

import products.Product;

public class ProductCreatedEvent extends Event{
    private String productNumber;
    private Product product;

    public ProductCreatedEvent(String productNumber, Product product) {
        super();
        this.productNumber = productNumber;
        this.product = product;
    }
}

