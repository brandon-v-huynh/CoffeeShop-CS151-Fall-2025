package coffeeshop;

public class MenuItem implements Billable {
    private static int instanceCount = 0;
    public static final int MAX_MENU_ITEMS = 100;

    private String name;
    private String description;
    private double price;
    private boolean available = true;

    // make item with name, description, and price
    public MenuItem(String name, String description, double price) {
        // stop here if hit the cap
        if (instanceCount >= MAX_MENU_ITEMS) {
            throw new InvalidOrderException("menu capacity reached (max " + MAX_MENU_ITEMS + ")");
        }
        setName(name);
        setDescription(description);
        setPrice(price);
        instanceCount++; // track the instances
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
        this.price = Math.round(price * 100.0) / 100.0; // round to cents
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
        return Math.round(total * 100.0) / 100.0; // round to cents
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

    // look up an item by key and throw an exception if its not there
    public static MenuItem requireFound(java.util.Map<String, MenuItem> menuByKey, String key) {
        MenuItem item = (menuByKey == null) ? null : menuByKey.get(key);
        if (item == null) throw new MenuItemNotFoundException(key);
        return item;
    }

    // guard against adding too many items to an order
    public static void enforceOrderLimit(int currentCount, int addQty, int maxItemsPerOrder) {
        if (addQty <= 0) {
            throw new InvalidOrderException("quantity must be positive");
        }
        if (currentCount + addQty > maxItemsPerOrder) {
            throw new OrderLimitExceededException(maxItemsPerOrder);
        }
    }

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
