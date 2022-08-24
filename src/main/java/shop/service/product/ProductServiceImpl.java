package shop.service.product;

import shop.entity.Product;
import shop.entity.ProductList;
import shop.repository.product.ProductRepository;
import shop.repository.product.ProductRepositoryImpl;


public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository = new ProductRepositoryImpl();
    @Override
    public ProductList loadAll() {
        return productRepository.readAll();
    }
    @Override
    public Product load(long id) {
        return productRepository.read(id);
    }
}
