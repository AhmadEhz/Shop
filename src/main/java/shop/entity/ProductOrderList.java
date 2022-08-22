package shop.entity;


import java.util.ArrayList;

public class ProductOrderList extends ArrayList<Product> {
    public boolean add(Product product) {
        //todo : check quantity, the add this product to ProductList
        return true;
    }
}
