package shop.service.productOrder;

import shop.entity.ProductOrder;
import shop.entity.ProductOrderList;
import shop.repository.productOrder.ProductOrderRepository;
import shop.repository.productOrder.ProductOrderRepositoryImpl;
import shop.service.product.ProductService;
import shop.service.product.ProductServiceImpl;

public class ProductOrderServiceImpl implements ProductOrderService {
    ProductOrderRepository productOrderRepository = new ProductOrderRepositoryImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    public ProductOrder load(long orderId, long productId) {
        return productOrderRepository.read(new ProductOrder(orderId, productId));
    }

    public void add(long productId, long orderId) {
        ProductOrder productOrder = new ProductOrder(orderId, productId);
        productOrder.setCount(1);
        productOrder.setPrice(productService.load(productId).getPrice());
        productOrderRepository.create(productOrder);
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
