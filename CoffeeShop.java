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
}