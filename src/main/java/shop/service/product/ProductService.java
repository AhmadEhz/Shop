package shop.service.product;

import shop.entity.Product;
import shop.entity.ProductList;

public interface ProductService {
    ProductList loadAll();

    Product load(long id);
}
