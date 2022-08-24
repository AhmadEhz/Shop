package shop.repository.order;

import shop.entity.Order;
import shop.entity.OrderList;

import shop.repository.BaseRepository;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order, Long> {
    OrderList readAll(long CustomerId);

    Order readPendingOrder(long customerId);
}
