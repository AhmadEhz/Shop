package shop.service.order;

import shop.entity.Order;
import shop.entity.OrderList;
import shop.entity.OrderStatus;

public interface OrderService {
    Order load(long id);
    OrderList loadAll(long customerId);
    void add(long customerId);
    void clear(Order order);
    void setStatus(Order order, OrderStatus orderStatus);
    void setTotalPrice(Order order, int newPrice);
    Order loadPendingOrder(long customerId);
}
