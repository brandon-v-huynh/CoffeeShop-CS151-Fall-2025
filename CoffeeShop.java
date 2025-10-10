import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeShop {
    private static final List<Object> menu = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    public static final int MAX_ORDERS = 100;

    public static void main(String[] args) {
        preloadMenu();
        menuLoop();
    }

    private static void menuLoop() {
        while (true) {
            System.out.println("\n=== JAVA BEANS CAFE ===");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Orders");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            String input = sc.nextLine();
            if (input.equals("1")) viewMenu();
            else if (input.equals("2")) placeOrder();
            else if (input.equals("3")) viewOrders();
            else if (input.equals("4")) exitApp();
            else System.out.println("Invalid choice.");
        }
    }

    private static void preloadMenu() {
        try {
            Class<?> cls = Class.forName("MenuItem");
            menu.add(cls.getConstructor(String.class, double.class).newInstance("Latte", 4.50));
            menu.add(cls.getConstructor(String.class, double.class).newInstance("Espresso", 3.00));
            menu.add(cls.getConstructor(String.class, double.class).newInstance("Americano", 3.75));
            menu.add(cls.getConstructor(String.class, double.class).newInstance("Cappuccino", 4.25));
            menu.add(cls.getConstructor(String.class, double.class).newInstance("Croissant", 2.50));
        } catch (Exception e) {
            menu.add("Latte");
            menu.add("Espresso");
            menu.add("Americano");
            menu.add("Cappuccino");
            menu.add("Croissant");
        }
    }

    private static void viewMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.size(); i++) {
            Object o = menu.get(i);
            String name = o.toString();
            double price = 0;
            try {
                Class<?> c = o.getClass();
                Object p = c.getMethod("getPrice").invoke(o);
                if (p instanceof Number) price = ((Number)p).doubleValue();
            } catch (Exception ignored) { }
            System.out.printf("%d. %s - $%.2f\n", i + 1, name, price);
        }
    }
    private static void placeOrder() {
        if (orders.size() >= MAX_ORDERS) {
            System.out.println("Order capacity reached.");
            return;
        }
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        Customer customer = new Customer(name, phone);
        customer.greet();
        Order order = new Order(customer);

}