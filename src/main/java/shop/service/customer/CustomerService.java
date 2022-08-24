package shop.service.customer;

import shop.entity.Customer;
import shop.entity.Order;

public interface CustomerService {
    //boolean checkUsername(String username);
    Customer login(Customer customer);
    Order loadPendingOrder(long customerId);
}
