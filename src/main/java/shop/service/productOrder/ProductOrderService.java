package shop.service.productOrder;

import shop.entity.ProductOrder;
import shop.entity.ProductOrderList;

public interface ProductOrderService {
    ProductOrderList loadAll(long orderId);
    ProductOrder load(long orderId, long productId);
    void delete(long id);
    void deleteAll(long orderId);
}
