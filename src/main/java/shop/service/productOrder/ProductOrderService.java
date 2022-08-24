package shop.service.productOrder;

import shop.entity.ProductOrder;
import shop.entity.ProductOrderList;

public interface ProductOrderService {
    ProductOrderList loadAll(long orderId);
    ProductOrder load(long id);
}
