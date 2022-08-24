package shop.entity;

public class Product {
    private long id;
    private String name;
    private String category;
    private String type;
    private int quantity;
    private boolean isExist;
    private int price;

    public Product() {
    }

    public Product(long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Product(long id, String name, String category, String type, int quantity, boolean isExist, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
        this.quantity = quantity;
        this.isExist = isExist;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    @Override
    public String toString() {
        return id + "- " + name + " | Category: " + category + '\n' +
               "Type: " + type + " | Quantity: " + quantity + " --> " + price;
    }
}
