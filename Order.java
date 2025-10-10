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

    public void setStatus(String s) {
        String v = s == null ? "" : s.trim();
        if (v.isEmpty()) v = "Pending";
        this.status = v;
    }

    public Customer getCustomer() {
        return customer;
    }
    public boolean addItem(Object menuItem, int qty) {
        if (menuItem == null) return false;
        if (qty <= 0) return false;
        if (this.items.size() >= MAX_ITEMS_PER_ORDER) return false;
        this.items.add(menuItem);
        this.quantities.add(qty);
        return true;
    }

    public double getTotal() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            int q = quantities.get(i);
            double price = 0.0;
            if (o != null) {
                try {
                    Class<?> c = o.getClass();
                    try {
                        java.lang.reflect.Method m = c.getMethod("calculateTotal", int.class);
                        Object v = m.invoke(o, q);
                        if (v instanceof Number) {
                            sum += ((Number) v).doubleValue();
                        }
                    } catch (NoSuchMethodException e) {
                        java.lang.reflect.Method gp = c.getMethod("getPrice");
                        Object p = gp.invoke(o);
                        if (p instanceof Number) {
                            price = ((Number) p).doubleValue();
                            sum += price * q;
                        }
                    }
                } catch (Exception ignore) { }
            }
        }
        return sum;
    }
}