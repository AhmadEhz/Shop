package shop.entity;


import java.util.ArrayList;

public class ProductOrderList extends ArrayList<ProductOrder> {
    @Override
    public boolean add(ProductOrder product) {
        //todo : check quantity, then add this product to ProductList
        return true;
    }
}
