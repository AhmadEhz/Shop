package shop.service.productOrder;

import shop.entity.ProductOrder;
import shop.entity.ProductOrderList;
import shop.repository.productOrder.ProductOrderRepository;
import shop.repository.productOrder.ProductOrderRepositoryImpl;

public class ProductOrderServiceImpl implements ProductOrderService{
    ProductOrderRepository productOrderRepository = new ProductOrderRepositoryImpl();

    @Override
    public ProductOrderList loadAll(long orderId) {
        return productOrderRepository.readAll(orderId);
    }

    @Override
    public ProductOrder load(long productId) {
       return productOrderRepository.read();
    }
}
