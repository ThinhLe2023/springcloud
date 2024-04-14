package products.event;

import products.Product;

public class ProductUpdatedEvent extends Event{
    private String productNumber;
    private Product product;
}
