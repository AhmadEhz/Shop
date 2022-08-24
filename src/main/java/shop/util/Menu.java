package shop.util;

import shop.entity.Customer;
import shop.entity.Order;
import shop.entity.OrderStatus;
import shop.entity.Product;
import shop.exception.NotFoundException;
import shop.service.customer.CustomerService;
import shop.service.customer.CustomerServiceImpl;
import shop.service.order.OrderService;
import shop.service.order.OrderServiceImpl;
import shop.service.product.ProductService;
import shop.service.product.ProductServiceImpl;
import shop.service.productOrder.ProductOrderService;
import shop.service.productOrder.ProductOrderServiceImpl;

public class Menu {
    private static final CustomerService customerService = new CustomerServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static final ProductOrderService productOrderService = new ProductOrderServiceImpl();

    public static void mainMenu() {
        checkConnection();
        while (true) {
            print(mainMenu);
            switch (Input.intScanner()) {
                case 0 -> {
                    return;
                }
                case 1 -> loginMenu();
            }
        }
    }

    private static void loginMenu() {
        while (true) {
            print("Enter username and password (Separate with space) (0 for exit) :");
            try {
                String[] userPassword = Input.getUserPassword();
                if (userPassword == null)
                    return;
                Customer customer = customerService.login(new Customer(userPassword[0], userPassword[1]));
                customer(customer);
                return;
            } catch (RuntimeException e) {
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
                case 2 -> allProduct(customer);
                case 0 -> {return;}
            }
        }
    }

    private static void showOrder(Order order) {
        while (true) {
            print(order);
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
                case 0 -> {
                    return;
                }
            }
        }
    }

    private static void allProduct(Customer customer) {
        Order order = null;
        try {
            print(productService.loadAll());
             order = orderService.loadPendingOrder(customer.getId());
        }
        catch (RuntimeException e){
            print(e.getMessage());
        }
        while (true) {
            print("Select a product to add to your order");
            int selectedProduct = Input.intScanner();

            if (selectedProduct == 0)
                return;
            try {
                Product product = productService.load(selectedProduct);

                if (!order.addProduct(Utility.getProductOrder(product, order.getId())))
                    throw new RuntimeException("Can't add this product!");
                productOrderService.add(selectedProduct, order.getId());
                print("Added!");
            } catch (RuntimeException e) {
                print(e.getMessage());
            }
        }
    }

    private static void editOrder(Order order) {
        print(order);
    }

    private static void print(Object o) {
        System.out.println(o);
    }

    private static void checkConnection() {
        try {
            DbConfig.getConnection();
        } catch (ExceptionInInitializerError e) {
            System.err.print("Can't connect to database.\nCheck your setting and run the program again.");
            System.exit(1);
        }
    }

    private static final String mainMenu = """
            1- Login
            2- Sign up
            0- Exit""";
    private static final String customerMenu = """
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
