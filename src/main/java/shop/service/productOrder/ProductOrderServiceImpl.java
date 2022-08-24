package shop.service.productOrder;

import shop.entity.ProductOrder;
import shop.entity.ProductOrderList;
import shop.repository.productOrder.ProductOrderRepository;
import shop.repository.productOrder.ProductOrderRepositoryImpl;

public class ProductOrderServiceImpl implements ProductOrderService {
    ProductOrderRepository productOrderRepository = new ProductOrderRepositoryImpl();
    @Override
    public ProductOrder load(long orderId, long productId) {
        return productOrderRepository.read(new ProductOrder(orderId, productId));
    }
    @Override
    public void deleteAll(long orderId) {
        productOrderRepository.deleteAll(orderId);
    }
    public void delete(long id) {
        productOrderRepository.delete(new ProductOrder(id));
    }

    @Override
    public ProductOrderList loadAll(long orderId) {
        return productOrderRepository.readAll(orderId);
    }


}
