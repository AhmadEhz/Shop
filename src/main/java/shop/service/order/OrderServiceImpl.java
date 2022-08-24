package shop.service.order;

import shop.entity.Order;
import shop.entity.OrderList;
import shop.entity.OrderStatus;
import shop.exception.PermissionDeniedException;
import shop.repository.order.OrderRepository;
import shop.repository.order.OrderRepositoryImpl;
import shop.service.productOrder.ProductOrderService;
import shop.service.productOrder.ProductOrderServiceImpl;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository = new OrderRepositoryImpl();
    private final ProductOrderService productOrderService = new ProductOrderServiceImpl();

    public Order load(long id) {
        return orderRepository.read(id);
    }

    public OrderList loadAll(long customerId) {
        return orderRepository.readAll(customerId);
    }

    public void add(long customerId) {
        if (!pendingOrderIsExist(customerId))
            orderRepository.create(new Order(customerId));
    }

    public void clear(Order order) {
        order = orderRepository.read(order.getId());
        if (order.getStatus().equals(OrderStatus.PENDING))
            productOrderService.deleteAll(order.getId());
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

    public Order loadPendingOrder(long customerId) {
        Order order = orderRepository.readPendingOrder(customerId);
            order.setProducts(productOrderService.loadAll(order.getId()));


        return order;
    }

    private boolean pendingOrderIsExist(long customerId) {
        try {
            orderRepository.readPendingOrder(customerId);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    public int numberOfProduct (long id) {
        return orderRepository.read(id).getProductNumbers();
    }
}
