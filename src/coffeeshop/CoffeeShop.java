package coffeeshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeShop {
    private static final List<Billable> menu = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    public static final int MAX_ORDERS = 100;

    public static void main(String[] args) {
        preloadMenu();
        menuLoop();
    }

    private static void menuLoop() {
        while (true) {
            System.out.println("\n=== CS 151 JAVA BEANS CAFE ==="); //added cs151 for fun!
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
        /*
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
        */
        // building MenuItem objects
        menu.add(new MenuItem("Latte", 4.50));
        menu.add(new MenuItem("Espresso", 3.00));
        menu.add(new MenuItem("Americano", 3.75));
        menu.add(new MenuItem("Cappuccino", 4.25));
        menu.add(new MenuItem("Croissant", 2.50));
    }

    private static void viewMenu() {
        System.out.println("\nMenu:");
        /*
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
        */
        //printing using the interface instead of reflection
        for (int i = 0; i < menu.size(); i++) {
            Billable item = menu.get(i);
            System.out.printf("%d. %s - $%.2f%n", i + 1, item.getItemName(), item.getUnitPrice());
        }
    }
    private static void placeOrder() {
        if (orders.size() >= MAX_ORDERS) {
            System.out.println("Order capacity reached.");
            return;
        }

        // FIX: validate customer name
        String name = getValidatedName();
        if (name == null) {
            System.out.println("Order cancelled");
            return;
        }

        // FIX: validate phone number
        String phone = getValidatedPhone();
        if (phone == null) {
            System.out.println("Order cancelled");
            return;
        }

        // naturally, if all is well we create new customer!
        Customer customer = new Customer(name, phone);
        customer.greet();
        Order order = new Order(customer);

        while (true) {
            viewMenu();
            System.out.print("Enter item number (0 to finish): ");
            String in = sc.nextLine();
            if (in.equals("0")) break;

            int num;
            try {
                num = Integer.parseInt(in);
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            if (num < 1 || num > menu.size()) {
                System.out.println("Invalid choice.");
                continue;
            }

            int qty = getValidatedQuantity();
            if (qty == -1) {
                System.out.println("Invalid quantity.");
                continue;
            }

            if (!order.addItem(menu.get(num - 1), qty)) {
                System.out.println("Failed to add item.");
            } else {
                System.out.println("Added item!");
            }

            if (order.getItemCount() >= Order.MAX_ITEMS_PER_ORDER) {
                System.out.println("Order capacity reached.");
                break;
            }
        }

        if (order.getItemCount() > 0) {
            System.out.printf("Total: $%.2f\n", order.getTotal());
            orders.add(order);

            int pointsEarned = (int) order.getTotal();
            customer.addPoints(pointsEarned);
            System.out.println("Order placed!");
            System.out.println("Earned " + pointsEarned + " points. Total points: " + customer.getPoints());
        } else {
            System.out.println("Order cancelled");
        }

    }

    private static String getValidatedName() {
        while (true) {
            System.out.print("Name: ");
            String name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty! Please enter a name!");
                continue;
            }

            if (!Customer.isValidName(name)) {
                System.out.println("Invalid name. Name must be more than 2 characters!");
                System.out.println("Try again? Input (y/n)");
                String response = sc.nextLine().trim().toLowerCase();
                if (!response.equals("y")) {
                    return null;
                }
            } else {
                return name;
            }
        }
    }

    private static String getValidatedPhone() {
        while (true) {
            System.out.print("Phone Number: ");
            String phone = sc.nextLine().trim();

            if (phone.isEmpty()) {
                System.out.println("Phone number cannot be empty! Please enter a valid phone number!");
                continue;
            }

            if (!Customer.isValidPhone(phone)) {
                System.out.println("Invalid phone number. Please enter a valid 10-digit phone number!");
                System.out.println("Try again? Input (y/n)");
                String response = sc.nextLine().trim().toLowerCase();
                if (!response.equals("y")) {
                    return null;
                }
            } else {
                return phone;
            }
        }
    }

    private static int getValidatedQuantity() {
        while (true) {
            System.out.print("Quantity: ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Quantity cannot be empty! Please enter a valid quantity!");
                continue;
            }

            try {
                int qty = Integer.parseInt(input);
                if (qty < 1) {
                    System.out.println("Quantity cannot be less than 1. Please enter a valid quantity!");
                    continue;
                }
                if (qty > 99) {
                    System.out.println("Quantity cannot be more than 99. Please enter a valid quantity!");
                    continue;
                }
                return qty;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number!");
                System.out.println("Try again? Input (y/n)");
                String response = sc.nextLine().trim().toLowerCase();
                if (!response.equals("y")) {
                    return -1;
                }
            }
        }
    }

    private static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        for (Order o : orders) System.out.println(o);
    }
    private static void exitApp() {
        System.out.println("Goodbye.");
        sc.close();
        System.exit(0);
    }
}
