package shop.repository.productOrder;

import shop.entity.ProductOrder;
import shop.entity.ProductOrderList;
import shop.exception.NotFoundException;
import shop.util.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductOrderRepositoryImpl implements ProductOrderRepository {
    private long lastGeneratedId;

    @Override
    public ProductOrder read(ProductOrder productOrder) {
        String query = """
                select pro_ord.id, order_id, product_id, category, type, count, pro.price, pro_ord.price from product_order as pro_ord
                join product pro on pro_ord.product_id = pro.id
                join orders ord on pro_ord.order_id = ord.id
                where order_id = ? and pro.id = ?;
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, productOrder.getOrderId());
            ps.setLong(2, productOrder.getProductId());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return getProductOrder(rs);
             else throw new NotFoundException("This product not found!");
        } catch (SQLException e) {
            throw new RuntimeException("Can't read from product_order", e.getCause());
        }
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
                where id =? and order_id = ? and product_id = ?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, productOrder.getId());
            ps.setLong(2, productOrder.getOrderId());
            ps.setLong(3,productOrder.getProductId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete from product_order", e);
        }
    }
    @Override
    public void deleteAll(long orderId) {
        String query = """
                delete from product_order
                where order_id = ?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1,orderId);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException("Can't delete products of this order");
        }
    }

    @Override
    public ProductOrderList readAll(long orderId) {
        String query = """
                select pro_ord.id, order_id, product_id, category, type, count, pro.price, pro_ord.price from product as pro
                join product_order as pro_ord on pro.id = pro_ord.product_id
                join orders as ord on ord.id = pro_ord.order_id
                where order_id = ?;
                """;
        ProductOrderList products = new ProductOrderList();
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                products.add(getProductOrder(rs));

            rs.close();
        } catch (SQLException e) {
            throw new NotFoundException("this order not found.");
        }
        if (products.isEmpty())
            throw new NotFoundException("Not found any product in this order!");
        return products;
    }

    private ProductOrder getProductOrder(ResultSet rs) throws SQLException {
        return new ProductOrder(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getString("status").equals("PENDING") ?
                        rs.getInt(7) : //If order is pending to pay, set the current product price.
                        rs.getInt(8)); //Else set the buy price.

    }

    public long getLastGeneratedId() {
        return lastGeneratedId;
    }
}
