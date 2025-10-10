package coffeeshop;

public class MenuItem implements Billable {
    private String name;
    private String description;
    private double price;
    private boolean available = true;

    // make item with name, description, and price
    public MenuItem(String name, String description, double price) {
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    // name check
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidOrderException("name can’t be empty");
        }
        this.name = name.trim();
    }

    // description check
    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new InvalidOrderException("description can’t be empty");
        }
        this.description = description.trim();
    }

    // price check + round
    public void setPrice(double price) {
        if (price < 0) {
            throw new InvalidOrderException("price can’t be negative");
        }
        this.price = Math.round(price * 100.0) / 100.0;
    }

    // change price (reuses validation)
    public void updatePrice(double newPrice) {
        setPrice(newPrice);
    }

    // mark sold out / back in stock
    public void setAvailability(boolean flag) {
        this.available = flag;
    }

    // quick % off helper
    public double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new InvalidOrderException("discount must be 0..100");
        }
        double discounted = price * (1 - percent / 100.0);
        return Math.round(discounted * 100.0) / 100.0; // round to cents
    }

    // basic math for total
    @Override
    public double calculateTotal(int qty) {
        if (qty <= 0) {
            throw new InvalidOrderException("quantity must be positive");
        }
        if (!available) {
            throw new InvalidOrderException("item '" + name + "' is unavailable");
        }
        double total = price * qty;
        return Math.round(total * 100.0) / 100.0;
    }

    // billable interface stuff
    @Override
    public String getItemName() { return name; }

    @Override
    public double getUnitPrice() { return price; }

    @Override
    public String getDescription() { return description; }

    @Override
    public boolean isAvailable() { return available; }

    // print info clean
    @Override
    public String toString() {
        return String.format("%s - $%.2f%s", name, price, available ? "" : " (UNAVAILABLE)");
    }

    // compare two items
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) o;
        return Double.compare(that.price, price) == 0 &&
                available == that.available &&
                name.equals(that.name) &&
                description.equals(that.description);
    }

    // so hashmaps/sets work right
    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, description, price, available);
    }
}
