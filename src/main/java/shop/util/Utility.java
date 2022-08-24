package shop.util;

import shop.entity.Product;
import shop.entity.ProductOrder;

public class Utility {
    public static ProductOrder getProductOrder(Product product, long orderId) {
        return new ProductOrder(orderId,product.getId(),product.getName(),
                product.getCategory(),product.getType(),1,product.getPrice());
    }
}
