import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int counter = 1;
    private int orderId;
    private Customer customer;
    private List<Object> items;
    private List<Integer> quantities;
    private String status;
    public static final int MAX_ITEMS_PER_ORDER = 10;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.status = "Pending";
        this.orderId = nextId();
    }

    private static synchronized int nextId() {
        int c = counter;
        counter = c + 1;
        return c;
    }
    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        if (status == null) return "";
        return status;
    }

}