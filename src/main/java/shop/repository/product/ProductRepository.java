package shop.repository.product;

import shop.entity.Product;
import shop.entity.ProductList;
import shop.repository.BaseRepository;

public interface ProductRepository extends BaseRepository<Product, Long> {
    ProductList readAll();
}
