package shop.repository.order;

import shop.entity.Order;
import shop.entity.OrderList;
import shop.entity.OrderStatus;
import shop.exception.NotFoundException;
import shop.util.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Order read(Long id) {
        String query = """
                select * from orders
                where id = ?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return getOrder(rs);
        } catch (SQLException e) {
            throw new NotFoundException("Order not found");
        }
    }

    @Override
    public void create(Order order) {// If customer have a pending order, this method not executed (Checked in OrderService)
        String query = """
                insert into orders (customer_id)
                values (?)
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, order.getCustomerId());
            ps.execute();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            order.setId(generatedKey.getLong("id"));
            ps.close();
            generatedKey.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't create order.");
        }
    }

    @Override
    public void update(Order order) {
        String query = """
                update orders
                set status = ? :: order_status, total_price = ?
                where id = ? and customer_id = ?;
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setString(1, order.getStatus().name());
            ps.setInt(2, order.getTotalPrice());
            ps.setLong(3, order.getId());
            ps.setLong(4, order.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't update order.");
        }
    }

    @Override
    public void delete(Order order) {
        String query = """
                delete from orders
                where id = ? and customer_id = ?;
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, order.getId());
            ps.setLong(2, order.getCustomerId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete order");
        }
    }

    @Override
    public OrderList readAll(long customerId) {//Only read prescriptions that finalized
        String query = """
                select * from orders
                where customer_id = ? and status != 'PENDING' ;
                """;
        OrderList orders = new OrderList();
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();
                while (rs.next())
                    orders.add(getOrder(rs));
        } catch (SQLException e) {
        throw new NotFoundException("You have not any finalized order!");
        }
        return orders;
    }
    @Override
    public Order readPendingOrder(long customerId) { //Each customer have a pending order.
        String query = """
                select * from orders
                where customer_id = ? and status = 'PENDING'
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1,customerId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Order order = getOrder(rs);
            ps.close();
            return order;
        }
        catch (SQLException e) {
            throw new RuntimeException("Can't read pending order!");
        }
    }

    private Order getOrder(ResultSet rs) throws SQLException {
        return new Order(rs.getLong("id"),
                rs.getInt("total_price"),
                OrderStatus.valueOf(rs.getString("status")),
                rs.getInt("product_numbers"),
                rs.getLong("customer_id"));
    }
}
