package shop.entity;

public class ProductOrder {

    private long id;
    private long orderId;
    private long productId;
    private String category;
    private String type;
    private int count;
    private int price;

    public ProductOrder() {
    }
    public ProductOrder(long id) {
        this.id = id;
    }

    public ProductOrder(long orderId, long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public ProductOrder(long id, long orderId, long productId, String category, String type, int count, int price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.category = category;
        this.type = type;
        this.count = count;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

