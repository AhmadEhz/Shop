package shop.service.order;

import shop.entity.Order;
import shop.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    Order load(long id);
    List<Order> loadAll(long customerId);
    void add(Order order);
    void delete (Order order);
    void setStatus(Order order, OrderStatus orderStatus);
    void setTotalPrice(Order order, int newPrice);
    long getLastGeneratedId();
}
