package shop.repository.product;

import shop.entity.Product;
import shop.entity.ProductList;
import shop.exception.NotFoundException;
import shop.util.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Product read(Long id) {
        String query = """
                select * from product
                where id = ?
                """;
        Product product = null;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            product = getProduct(rs);

        } catch (SQLException e) {
            throw new NotFoundException("This product not found!");
        }
        return product;
    }

    @Override
    public void create(Product product) {
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public void update(Product product) {
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public void delete(Product product) {
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public ProductList readAll() {
        String query = """
                select * from product
                """;
        ProductList products = new ProductList();
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                products.add(getProduct(rs));

            rs.close();
            if (products.isEmpty())
                throw new NotFoundException("Not found any product!");
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Can't read from product_order!", e.getCause());
        }
    }


    private Product getProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("type"),
                rs.getInt("quantity"),
                rs.getBoolean("is_exist"),
                rs.getInt("price")
        );

    }
}
