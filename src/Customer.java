public class Customer extends Person {
    private int loyaltyPoints;

    public Customer(String name, String phone) {
        super(name, phone);
        this.loyaltyPoints = 0;
    }
}
