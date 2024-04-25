package products;


public class Stock {
    private int quantity;
    private String productNumber;

    public Stock( int quantity, String productNumber) {

        this.quantity = quantity;
        this.productNumber = productNumber;
    }

    public Stock() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }
}
