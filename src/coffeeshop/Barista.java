package coffeeshop;

public class Barista {
    private String name;
    private String phone;
    private String employeeID;
    private String shift;
    private boolean isClockedIn;
    private int ordersCompleted;

    private static int instanceCount = 0;
    public static final int MAX_BARISTAS = 100;

    public Barista(String name, String phone, String employeeID, String shift) {
        this.name = name;
        this.phone = phone;
        this.employeeID = employeeID;
        this.shift = shift.toUpperCase();
        this.isClockedIn = false;
        ordersCompleted = 0;

        if (instanceCount >= MAX_BARISTAS) {
            throw new IllegalStateException("Cannot create more than " + MAX_BARISTAS + " baristas");
        }
        instanceCount++;
    }

    public void completeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if(!isClockedIn) {
            System.out.println(name + " cannot complete order while clocked out");
            return;
        }

        order.setStatus("Complete");
        ordersCompleted++;
        System.out.println("Order completed by " + name + "! Total order completed: "+ ordersCompleted);

    }

    public void clockIn() {
        if (isClockedIn) {
            System.out.println(name + " is clocked in");
            return;
        }

        isClockedIn = true;
        System.out.println(name + " clocked in for " + shift);
    }



}
