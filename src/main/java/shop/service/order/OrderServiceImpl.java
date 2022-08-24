package shop.service.order;

import shop.entity.Order;
import shop.entity.OrderStatus;
import shop.exception.PermissionDeniedException;
import shop.repository.order.OrderRepository;
import shop.repository.order.OrderRepositoryImpl;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository = new OrderRepositoryImpl();
    public Order load(long id) {
    return orderRepository.read(id);
    }
    public List<Order> loadAll(long customerId) {
        return orderRepository.readAll(customerId);
    }
    public void add(Order order) {
        orderRepository.create(order);
    }
    public void delete(Order order) {
        Order loadedOrder = orderRepository.read(order.getId());
        if(loadedOrder.getStatus().equals(OrderStatus.PENDING))
            orderRepository.delete(order);
        else throw new PermissionDeniedException("You can't delete finalized orders.");
    }
    public void setStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
        orderRepository.update(order);
    }
    public void setTotalPrice(Order order, int newPrice) {
        order.setTotalPrice(newPrice);
        orderRepository.update(order);
    }
    public long getLastGeneratedId() {
        return orderRepository.getLastGeneratedId();
    }
}
