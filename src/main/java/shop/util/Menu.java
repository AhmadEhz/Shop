package shop.util;

import shop.entity.Customer;
import shop.entity.Order;
import shop.entity.OrderStatus;
import shop.service.customer.CustomerService;
import shop.service.customer.CustomerServiceImpl;
import shop.service.order.OrderService;
import shop.service.order.OrderServiceImpl;

public class Menu {
    private static CustomerService customerService = new CustomerServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();
    public static void mainMenu(){
        checkConnection();
        while(true) {
            print(mainMenu);
            switch (Input.intScanner()) {
                case 0 -> {return;}
                case 1 -> loginMenu();
            }
        }
    }

    private static void loginMenu() {
        while (true) {
            print("Enter username and password (Separate with space) (0 for exit) :");
            try {
                String [] userPassword = Input.getUserPassword();
                if (userPassword==null)
                    return;
                Customer customer = customerService.login(new Customer(userPassword[0],userPassword[1]));
                customer(customer);
                return;
            }
            catch (RuntimeException e) {
                print(e.getMessage());
            }
        }
    }

    private static void customer(Customer customer) {
        orderService.add(customer.getId());
        while (true) {
            print(customerMenu);
            switch (Input.intScanner()) {
                case 1 -> showOrder(customerService.loadPendingOrder(customer.getId()));
            }
        }
    }

    private static void showOrder(Order order) {
        print(order);
        while (true) {
            print(orderMenu);
            switch (Input.intScanner()) {
                case 1 -> {
                    orderService.setStatus(order, OrderStatus.WAITING);
                    print("Set! waiting to accepted.");
                    return;
                }
                case 2 -> editOrder(order);
                case 3 -> {
                    orderService.clear(order);
                    print("Cleared");
                }
                case 0 -> {return;}
            }
        }
    }

    private static void editOrder(Order order) {
        print(order);
    }

    private static void checkConnection() {
        try {
            DbConfig.getConnection();
        }
        catch (ExceptionInInitializerError e) {
            System.err.print("Can't connect to database.\nCheck your setting and run the program again.");
            System.exit(1);
        }
    }
    private static void print(Object o) {
        System.out.println(o);
    }
    private static final String mainMenu = """
            1- Login
            2- Sign up
            0- Exit""";
    private static final String customerMenu= """
            1- View current order
            2- View all products
            3- View previous order
            0- Exit""";
    private static final String orderMenu = """
           1- Save and confirm
           2- Edit products
           3- Delete all products
           0- Save and exit""";
}
