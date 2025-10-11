package coffeeshop;

public class Barista {
    private String name;
    private String phone;
    private String employeeId;
    private String shift;
    private boolean isClockedIn;
    private int ordersCompleted;

    private static int instanceCount = 0;
    public static final int MAX_BARISTAS = 100;

    public Barista(String name, String phone, String employeeID, String shift) {
        this.name = name;
        this.phone = phone;
        this.employeeId = employeeID;
        this.shift = shift.toUpperCase();
        this.isClockedIn = false;
        ordersCompleted = 0;

        if (instanceCount >= MAX_BARISTAS) {
            throw new IllegalStateException("Cannot create more than " + MAX_BARISTAS + " baristas");
        }
        instanceCount++;
    }

    // PLEASE RESOLVE ORDER.JAVA
    // ALSO SETSTATUS() WITHIN ORDER.JAVA
    public void makeOrder(Order order) {
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

    public void clockOut() {
        if (!isClockedIn) {
            System.out.println(name + " is clocked out");
            return;
        }

        isClockedIn = false;
        System.out.println(name + " clocked out for today. Completed " + ordersCompleted + " orders");
    }

    public void changeShift(String newShift) {
        if (newShift == null || newShift.trim().isEmpty()) {
            throw new IllegalArgumentException("Shift cannot be null or empty");
        }

        String oldShift = this.shift;
        this.shift = newShift.toUpperCase();
        System.out.println(name + " shift changed from " + oldShift + " to " + this.shift);
    }

    public void viewPerformance() {
        System.out.println(name + "'s performance");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Shift: " + shift);
        System.out.println("Orders completed: " + ordersCompleted);
        System.out.println("Current status: " + (isClockedIn ? "Clocked IN" : "Clocked OUT"));
    }

    // A few setters/getters here n' there!

    public void greet() {
        System.out.println("Hi! Welcome to the CS151 Coffeeshop! I'm " + name + ", and your barista for the shift: " + shift);
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }
        this.name = name;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) { // I am using regex here for easy reuse
            throw new IllegalArgumentException("Phone must be exactly 10 digits!");
        }
        this.phone = phone;
    }

    public String getEmployeeId() { return employeeId; }

    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty!");
        }
        this.employeeId = employeeId;
    }

    public String getShift() { return shift; }

    public void setShift(String shift) {
        if (shift == null || shift.trim().isEmpty()) {
            throw new IllegalArgumentException("Shift cannot be null or empty!");
        }
        this.shift = shift.toUpperCase();
    }

    public boolean isClockedIn() { return isClockedIn; }
    public int getOrdersCompleted() { return ordersCompleted; }
    public static int getInstanceCount() { return instanceCount; }
    public static int getRemainingCapacity() { return MAX_BARISTAS - instanceCount; }
    @Override
    public String toString() {
        return "Barista[ID: " + employeeId + ", Name: " + name + ", Shift: " + shift +
                ", Clocked In: " + isClockedIn + ", Orders Completed: " + ordersCompleted;
    }

}
