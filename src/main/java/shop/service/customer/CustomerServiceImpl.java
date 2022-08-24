package shop.service.customer;

import shop.entity.Customer;
import shop.entity.Order;
import shop.repository.customer.CustomerRepository;
import shop.repository.customer.CustomerRepositoryImpl;
import shop.service.order.OrderService;
import shop.service.order.OrderServiceImpl;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    OrderService orderService = new OrderServiceImpl();
    /*public boolean checkUsername (String username) {
        return customerRepository.checkUsername(username);
    }*/
    @Override
    public Customer login(Customer customer) {
        return customerRepository.read(customer);
    }
    @Override
    public Order loadPendingOrder(long customerId) {
        return orderService.loadPendingOrder(customerId);
    }
}
