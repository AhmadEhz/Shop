package shop.repository.product;

import shop.entity.Product;
import shop.entity.ProductOrderList;
import shop.exception.NotFoundException;
import shop.util.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Product read(Long id) {
        return null;
    }

    @Override
    public void create(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public List<Product> readAll() {
        String query = """
                select * from product
                """;
        ProductOrderList products = new ProductOrderList();
        try(PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                product.setCount(rs.getInt("quantity"));
                product.setExist(rs.getBoolean("is_exist"));
                product.setPrice(rs.getInt("price"));
                product.setCount(rs.getInt("quantity"));
                products.add(product);
            }
            return products;
        }
        catch (SQLException e) {
            throw new NotFoundException("Nothing any product to show.");
        }
    }


    private Product setProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("category"));
    }
}
