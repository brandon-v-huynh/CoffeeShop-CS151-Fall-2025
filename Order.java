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
}