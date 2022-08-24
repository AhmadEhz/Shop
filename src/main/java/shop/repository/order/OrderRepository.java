package shop.repository.order;

import shop.entity.Order;
import shop.repository.BaseRepository;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order, Long> {
    List<Order> readAll(long CustomerId);
    long getLastGeneratedId();
}
