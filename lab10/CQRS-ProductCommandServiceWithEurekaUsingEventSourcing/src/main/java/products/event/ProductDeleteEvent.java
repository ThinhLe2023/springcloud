package products.event;

import products.Product;

public class ProductDeleteEvent extends Event{
    private String productNumber;
    private Product product;
}
