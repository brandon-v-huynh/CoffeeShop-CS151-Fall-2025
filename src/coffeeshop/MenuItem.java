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

    // ----- setters with basic validation -----
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

    // ----- simple getters + interface stubs -----
    @Override
    public double calculateTotal(int qty) {
        return 0; // placeholder for now
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
