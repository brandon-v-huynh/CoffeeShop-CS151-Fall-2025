package coffeeshop;

public class MenuItem implements Billable {
    private String name;
    private String description;
    private double price;
    private boolean available = true;

    public MenuItem(String name, String description, double price) {
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    // setters w/ basic validation
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidOrderException("name can’t be empty");
        }
        this.name = name.trim();
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new InvalidOrderException("description can’t be empty");
        }
        this.description = description.trim();
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new InvalidOrderException("price can’t be negative");
        }
        this.price = Math.round(price * 100.0) / 100.0;
    }
    // change price
    public void updatePrice(double newPrice) {
        setPrice(newPrice);
    }

    // mark sold out / back in stock
    public void setAvailability(boolean flag) {
        this.available = flag;
    }

    public double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new InvalidOrderException("discount must be 0..100");
        }
        double discounted = price * (1 - percent / 100.0);
        return Math.round(discounted * 100.0) / 100.0; // round to cents
    }

    @Override
    public double calculateTotal(int qty) {
        if (qty <= 0) {
            throw new InvalidOrderException("quantity must be positive");
        }
        if (!available) {
            throw new InvalidOrderException("item '" + name + "' is unavailable");
        }

        double total = price * qty;
        // round to two decimals
        return Math.round(total * 100.0) / 100.0;
    }


    @Override
    public String getItemName() { return name; }

    @Override
    public double getUnitPrice() { return price; }

    @Override
    public String getDescription() { return description; }

    @Override
    public boolean isAvailable() { return available; }
}
