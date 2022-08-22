package shop.repository.productOrder;

import shop.entity.Product;
import shop.entity.ProductOrder;
import shop.repository.BaseRepository;

import java.util.List;

public interface ProductOrderRepository extends BaseRepository<ProductOrder, Long> {

    List<ProductOrder> readAll(long orderId);
}
