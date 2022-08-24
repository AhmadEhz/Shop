package shop.service.customer;

import shop.entity.Customer;
import shop.repository.customer.CustomerRepository;
import shop.repository.customer.CustomerRepositoryImpl;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    public boolean checkUsername (String username) {
        return customerRepository.checkUsername(username);
    }
    public Customer load(Customer customer) {
        return customerRepository.read(customer);
    }
}
