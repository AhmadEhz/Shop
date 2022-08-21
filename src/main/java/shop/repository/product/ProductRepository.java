package shop.repository.product;

import shop.entity.Product;
import shop.repository.BaseRepository;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product, Long> {
    List<Product> readAll();

    List<Product> readAll(long OrderId);
}
