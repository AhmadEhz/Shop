package shop.entity;

import java.util.List;

public class Order {
    private long id;
    private List<Product> products;
    private OrderStatus status;
    private int totalPrice;
    private long customerId;
    public Order () {}
    public Order (long id, int totalPrice, long customerId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
