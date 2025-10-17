package coffeeshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CoffeeShop {
    private static final List<Billable> menu = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final List<Barista> baristas = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    public static final int MAX_ORDERS = 100;

    public static void main(String[] args) {
        preloadMenu();
        preloadBaristas();
        mainMenuLoop();
    }
    private static void mainMenuLoop() {
        while (true) {
            System.out.println("\n=== CS151 JAVA BEANS CAFE ===");
            System.out.println("1. Customer Mode");
            System.out.println("2. Barista Mode");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            String input = sc.nextLine();
            if (input.equals("1")) customerMode();
            else if (input.equals("2")) baristaMode();
            else if (input.equals("3")) exitApp();
            else System.out.println("Invalid choice.");
        }
    }
    private static void customerMode() {
        while (true) {
            System.out.println("\n=== CUSTOMER MODE ===");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Orders");
            System.out.println("4. Add funds");
            System.out.println("5. Return to Main Menu");
            System.out.print("Choice: ");
            String input = sc.nextLine();
            if (input.equals("1")) viewMenu();
            else if (input.equals("2")) placeOrder();
            else if (input.equals("3")) viewOrders();
            else if (input.equals("4")) addFunds();
            else if (input.equals("5")) break;
            else System.out.println("Invalid choice.");
        }
    }
    private static void baristaMode() {
        Barista currentBarista = selectBarista();
        if (currentBarista == null) return;

        while (true) {
            System.out.println("\n=== BARISTA PORTAL ===");
            System.out.println("1. Clock In");
            System.out.println("2. View All Orders");
            System.out.println("3. Complete an Order");
            System.out.println("4. View Performance");
            System.out.println("5. Clock Out");
            System.out.println("6. Return to Main Menu");
            System.out.print("Choice: ");
            String input = sc.nextLine();
            if (input.equals("1")) currentBarista.clockIn();
            else if (input.equals("2")) viewAllOrdersForBarista();
            else if (input.equals("3")) completeOrder(currentBarista);
            else if (input.equals("4")) currentBarista.viewPerformance();
            else if (input.equals("5")) currentBarista.clockOut();
            else if (input.equals("6")) break;
            else System.out.println("Invalid choice.");
        }
    }
    private static Barista selectBarista() {
        System.out.println("\nAvailable Baristas:");
        for (int i = 0; i < baristas.size(); i++) {
            System.out.println((i + 1) + ". " + baristas.get(i).getName() + " (ID: " + baristas.get(i).getEmployeeId() + ")");
        }
        System.out.print("Select barista by number: ");
        String input = sc.nextLine();
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= baristas.size()) {
                Barista selected = baristas.get(choice - 1);
                selected.greet();
                return selected;
            } else {
                System.out.println("Invalid selection.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return null;
        }
    }
    private static void viewAllOrdersForBarista() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }
        System.out.println("\nAll Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
    private static void completeOrder(Barista barista) {
        if (orders.isEmpty()) {
            System.out.println("No orders to complete.");
            return;
        }
        
        System.out.println("\nAvailable Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
        
        System.out.print("Enter order ID to complete: ");
        String input = sc.nextLine();
        try {
            int orderId = Integer.parseInt(input);
            Order targetOrder = null;
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    targetOrder = order;
                    break;
                }
            }
            if (targetOrder != null) {
                barista.makeOrder(targetOrder);
            } else {
                System.out.println("Order ID " + orderId + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid order ID.");
        }
    }
    private static void preloadBaristas() {
        baristas.add(new Barista("Alice Johnson", "1234567890", "EMP001", "Morning"));
        baristas.add(new Barista("Bob Smith", "2345678901", "EMP002", "Evening"));
        baristas.add(new Barista("Carol Davis", "3456789012", "EMP003", "Night"));
    }
    private static void preloadMenu() {
        menu.add(new MenuItem("Latte", 4.50));
        menu.add(new MenuItem("Espresso", 3.00));
        menu.add(new MenuItem("Americano", 3.75));
        menu.add(new MenuItem("Cappuccino", 4.25));
        menu.add(new MenuItem("Croissant", 2.50));
    }
    private static void viewMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.size(); i++) {
            Billable item = menu.get(i);
            System.out.printf("%d. %s - $%.2f%n", i + 1, item.getItemName(), item.getUnitPrice());
        }
    }

    private static void addFunds() {
        String name = getValidatedName();
        if(name == null) {
            System.out.println("Order cancelled");
            return;
        }

        String phone = getValidatedPhone();
        if(phone == null) {
            System.out.println("Order cancelled");
            return;
        }
        Customer customer = new Customer(name, phone, 0);
        System.out.println("Enter the amount of money you have: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Enter a number");
            sc.next();
        }
        double funds = sc.nextDouble();
        sc.nextLine();

        try {
            customer.add(funds);
            System.out.printf("Funds added: $%.2f", customer.getFunds());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
       
    }

    private static void placeOrder() {
        if (orders.size() >= MAX_ORDERS) {
            System.out.println("Order capacity reached.");
            return;
        }
        String name = getValidatedName();
        if (name == null) {
            System.out.println("Order cancelled");
            return;
        }
        String phone = getValidatedPhone();
        if (phone == null) {
            System.out.println("Order cancelled");
            return;
        }
        Customer customer = new Customer(name, phone);
        customer.greet();
        Order order = new Order(customer);
        System.out.print("Enter starting amount: ");
        double amt = 0;
        if (sc.hasNextDouble()) {
            amt = sc.nextDouble();
        } else {
            System.out.println("Invalid amount. Cannot start with $0.00");
        }
        sc.nextLine();
        customer.add(amt);
        
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
            double total = order.getTotal();
            System.out.printf("Total: $%.2f", total);
            System.out.println();

            try {
                customer.spend(total);
            } catch (NotEnoughException e){
                System.out.printf("Insuffcient funds");
                System.out.println();
                System.out.print("Add more funds? (y/n): ");
                String response = sc.nextLine().trim().toLowerCase();
                if ("y".equals(response)) {
                    double added = 0;
                    while (true) {
                        System.out.print("Added amount: ");
                        if (sc.hasNextDouble()) {
                            added = sc.nextDouble();
                            sc.nextLine();
                            break;
                        } else {
                            System.out.println("Enter a number");
                            sc.next();
                        }
                    }

                    try {
                        customer.add(added);
                        customer.spend(total);
                    } catch (Exception ex) {
                        System.out.println("Payment failed. Order cancelled");
                        return;
                    }
                } else {
                    System.out.println("Order cancelled (not enough)");
                    return;
                }
            }
            orders.add(order);
            int pointsEarned = (int) total;
            customer.addPoints(pointsEarned);
            System.out.println("Payment accepted. Order has been placced!");
            System.out.printf("Remaining funds: $%.2f", customer.getFunds()); 
            System.out.println(" Earned " + pointsEarned + "points. Total points: " + customer.getPoints());
            

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