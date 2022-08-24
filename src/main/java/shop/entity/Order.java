package shop.entity;

import java.util.List;

public class Order {
    private long id;
    private ProductOrderList products;
    private OrderStatus status;
    private int totalPrice;
    private int productNumbers;
    private long customerId;

    public Order() {
    }

    public Order(long customerId) {
        this.customerId = customerId;
    }

    public Order(long id, int totalPrice, OrderStatus status, int productNumbers, long customerId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.productNumbers = productNumbers;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductOrderList getProducts() {
        return products;
    }

    public void setProducts(ProductOrderList products) {
        this.products = products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getProductNumbers() {
        return productNumbers;
    }

    public void setProductNumbers(int productNumbers) {
        this.productNumbers = productNumbers;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public boolean addProduct(ProductOrder productOrder) {
       return products.add(productOrder);
    }
    @Override
    public String toString() {
        return id +
               "- Status: " + status + "\n" +
               "- TotalPrice: " + totalPrice +
               "Number: " + productNumbers +
               "Products:\n" + products;

    }
}
