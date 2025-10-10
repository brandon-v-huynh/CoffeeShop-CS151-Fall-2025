public class Customer extends Person {
    private int loyaltyPoints;

    public Customer(String name, String phone) {
        super(name, phone);
        this.loyaltyPoints = 0;
    }

    @Override
    public void greet() {
        String n = getName();
        if (n == null) n = "";
        String message = "Hello " + n + ", welcome to Java Beans Cafe";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) sb.append(message.charAt(i));
        System.out.println(sb.toString());
    }
}
