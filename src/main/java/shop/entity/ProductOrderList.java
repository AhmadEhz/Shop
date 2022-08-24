package shop.entity;


import shop.service.order.OrderService;
import shop.service.order.OrderServiceImpl;
import shop.service.product.ProductService;
import shop.service.product.ProductServiceImpl;

import java.util.ArrayList;

public class ProductOrderList extends ArrayList<ProductOrder> {
    OrderService orderService = new OrderServiceImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    public boolean add(ProductOrder productOrder) {
        Product product = productService.load(productOrder.getOrderId());
        if (orderService.numberOfProduct(productOrder.getOrderId()) >= 5 || product.getQuantity() <= productOrder.getCount())
            return false;
        else super.add(productOrder);
        productOrder.setCount(productOrder.getCount()+1);
        return true;
    }

    @Override
    public String toString() {
        String string = "";
        for (String s : (String[]) this.toArray()) {
            string += s + "\n";
        }
        return string;
    }
}
