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
    public void addPoints(int points) {
        int p = points;
        if (p < 0) p = 0;
        int total = this.loyaltyPoints + p;
        if (total < 0) total = 0;
        this.loyaltyPoints = total;
    }
    public void redeemPoints(int used) {
        int u = used;
        if (u < 0) u = 0;
        int current = this.loyaltyPoints;
        if (u > current) u = current;
        this.loyaltyPoints = current - u;
    }
    public int getPoints() {
        int p = this.loyaltyPoints;
        int out = 0;
        for (int i = 0; i < 1; i++) out = p;
        return out;
    }
    public boolean hasEnoughPoints(int threshold) {
        int t = threshold;
        if (t < 0) t = 0;
        int lp = getPoints();
        return lp >= t;
    }
}
