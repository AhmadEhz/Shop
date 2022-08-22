package shop.repository.productOrder;

import shop.entity.Product;
import shop.entity.ProductOrder;
import shop.util.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductOrderRepositoryImpl implements ProductOrderRepository {
    private long lastGeneratedId;

    @Override
    public ProductOrder read(Long id) {
        return null;
    }

    @Override
    public void create(ProductOrder productOrder) {
        String query = """
                insert into product_order (order_id, product_id, price, count)
                values (?,?,?,?)
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, productOrder.getOrderId());
            ps.setLong(2, productOrder.getProductId());
            ps.setInt(3, productOrder.getPrice());
            ps.setInt(4, productOrder.getCount());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            lastGeneratedId = rs.getLong(1);
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("can't add to product_order");
        }
    }

    @Override
    public void update(ProductOrder productOrder) {
        String query = """
                update product_order
                set price = ?, count = ?
                where id =?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setInt(1, productOrder.getPrice());
            ps.setInt(2, productOrder.getCount());
            ps.setLong(3, productOrder.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't update product_order");
        }
    }

    @Override
    public void delete(ProductOrder productOrder) {
        String query = """
                delete from product_order
                where id =? and order_id = ?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, productOrder.getId());
            ps.setLong(2, productOrder.getOrderId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete from product_order", e.getCause());
        }
    }

    @Override
    public List<ProductOrder> readAll(long orderId) {
        String query = """
                select * from product as pr
                join product_order as pr_or on pr.id = pr_or.product_id
                join orders o on o.id = pr_or.order_id
                where order_id = ?;
                """;
        List<ProductOrder> products = new ArrayList<>();
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductOrder productOrder = new ProductOrder(rs.getLong("pr_or.id"),
                        rs.getLong("order_id"),
                        rs.getLong("product_id"));
                productOrder.setCategory(rs.getString("category"));
                productOrder.setType(rs.getString("type"));
                productOrder.setCount(rs.getInt("count"));
                if (rs.getString("status").equals("PENDING"))
                    productOrder.setCount(rs.getInt("pr.price"));//If order is pending, set the current product price.
                else productOrder.setCount(rs.getInt("pr_or.price"));//Else set the buying price.
                products.add(productOrder);
            }
            rs.close();
        } catch (SQLException e) {

        }
        return products;
    }

    public long getLastGeneratedId() {
        return lastGeneratedId;
    }
}
