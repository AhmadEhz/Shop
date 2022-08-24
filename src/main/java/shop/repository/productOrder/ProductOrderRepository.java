package shop.repository.productOrder;

import shop.entity.Product;
import shop.entity.ProductOrder;
import shop.entity.ProductOrderList;
import shop.repository.BaseRepository;

import java.util.List;

public interface ProductOrderRepository extends BaseRepository<ProductOrder, ProductOrder> {

    ProductOrderList readAll(long orderId);
    long getLastGeneratedId();
    void deleteAll(long orderId);
}
