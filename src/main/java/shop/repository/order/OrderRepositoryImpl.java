package shop.repository.order;

import shop.entity.Order;
import shop.exception.NotFoundException;
import shop.util.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private long lastGeneratedId;

    @Override
    public Order read(Long id) {
        String query = """
                select * from orders
                where id = ?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return setOrder(rs);
        } catch (SQLException e) {
            throw new NotFoundException("Order not found");
        }
    }

    @Override
    public void create(Order order) {
        String query = """
                insert into orders (total_price, status, customer_id)
                values (?,? :: order_status,?)
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getTotalPrice());
            ps.setString(2, order.getStatus().name());
            ps.setLong(3, order.getCustomerId());
            ps.execute();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            lastGeneratedId = generatedKey.getLong(1);
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
            ps.setLong(2,order.getCustomerId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete order");
        }
    }

    @Override
    public List<Order> readAll(long CustomerId) {
        return null;
    }

    private Order setOrder(ResultSet rs) throws SQLException {
        return new Order(rs.getLong("id"),
                rs.getInt("total_price"),
                rs.getLong("customer_id"));
    }

    public long getLastGeneratedId() {
        return lastGeneratedId;
    }
}
