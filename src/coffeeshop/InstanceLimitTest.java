package coffeeshop;

public class InstanceLimitTest {

    public static void main(String[] args) {
        System.out.println("Starting comprehensive instance limit testing...");
        System.out.println("This test will verify that our classes properly enforce their limits");
        System.out.println("We will test Customer, Barista, and MenuItem classes");
        System.out.println("Each class should allow exactly 100 instances and reject the 101st");
        System.out.println("Let's begin the testing process...\n");
        
        boolean customerTestPassed = false;
        boolean baristaTestPassed = false;
        boolean menuItemTestPassed = false;
        
        System.out.println("=== PHASE 1: CUSTOMER CLASS TESTING ===");
        customerTestPassed = testCustomerInstanceLimit();
        
        System.out.println("\n=== PHASE 2: BARISTA CLASS TESTING ===");
        baristaTestPassed = testBaristaInstanceLimit();
        
        System.out.println("\n=== PHASE 3: MENUITEM CLASS TESTING ===");
        menuItemTestPassed = testMenuItemInstanceLimit();
        
        System.out.println("\n=== FINAL RESULTS SUMMARY ===");
        if (customerTestPassed) {
            System.out.println("Customer class test: PASSED");
        } else {
            System.out.println("Customer class test: FAILED");
        }
        
        if (baristaTestPassed) {
            System.out.println("Barista class test: PASSED");
        } else {
            System.out.println("Barista class test: FAILED");
        }
        
        if (menuItemTestPassed) {
            System.out.println("MenuItem class test: PASSED");
        } else {
            System.out.println("MenuItem class test: FAILED");
        }
        
        if (customerTestPassed && baristaTestPassed && menuItemTestPassed) {
            System.out.println("\n🎉 ALL TESTS COMPLETED SUCCESSFULLY! 🎉");
            System.out.println("All classes properly enforce their 100 instance limits!");
        } else {
            System.out.println("\n❌ SOME TESTS FAILED! ❌");
            System.out.println("Please check the error messages above.");
        }
    }

    public static boolean testCustomerInstanceLimit() {
        System.out.println("Now testing Customer class instance limit...");
        System.out.println("Creating customers one by one to verify counting works...");
        
        Customer[] customers = new Customer[Customer.MAX_CUSTOMERS];
        boolean allGood = true;
        
        for (int i = 0; i < Customer.MAX_CUSTOMERS; i++) {
            try {
                customers[i] = new Customer("Customer" + i, "1234567890");
                int currentCount = Customer.getInstanceCount();
                if (currentCount != i + 1) {
                    System.err.println("PROBLEM: Expected count " + (i + 1) + " but got " + currentCount);
                    allGood = false;
                    break;
                }
                if (i % 10 == 0) {
                    System.out.println("Created " + (i + 1) + " customers so far...");
                }
            } catch (Exception e) {
                System.err.println("Unexpected error creating customer " + i + ": " + e.getMessage());
                allGood = false;
                break;
            }
        }
        
        if (!allGood) {
            System.out.println("Customer creation test failed!");
            return false;
        }
        
        System.out.println("Successfully created 100 customers. Now testing 101st...");
        
        try {
            Customer customer101 = new Customer("Customer101", "1234567890");
            System.err.println("MAJOR PROBLEM: Should not have been able to create 101st customer!");
            return false;
        } catch (IllegalStateException e) {
            String message = e.getMessage();
            if (message.contains("Cannot create more than") && message.contains("persons")) {
                System.out.println("Perfect! Customer class correctly rejected 101st instance");
                System.out.println("Exception message: " + message);
                return true;
            } else {
                System.err.println("Wrong exception message: " + message);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Wrong type of exception: " + e.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean testBaristaInstanceLimit() {
        System.out.println("Now testing Barista class instance limit...");
        System.out.println("Creating baristas with different shifts and IDs...");
        
        Barista[] baristas = new Barista[Barista.MAX_BARISTAS];
        boolean allGood = true;
        String[] shifts = {"Morning", "Afternoon", "Evening", "Night"};
        
        for (int i = 0; i < Barista.MAX_BARISTAS; i++) {
            try {
                String shift = shifts[i % shifts.length];
                String empId = "EMP" + String.format("%03d", i);
                baristas[i] = new Barista("Barista" + i, "1234567890", empId, shift);
                int currentCount = Barista.getInstanceCount();
                if (currentCount != i + 1) {
                    System.err.println("PROBLEM: Expected count " + (i + 1) + " but got " + currentCount);
                    allGood = false;
                    break;
                }
                if (i % 15 == 0) {
                    System.out.println("Created " + (i + 1) + " baristas so far...");
                }
            } catch (Exception e) {
                System.err.println("Unexpected error creating barista " + i + ": " + e.getMessage());
                allGood = false;
                break;
            }
        }
        
        if (!allGood) {
            System.out.println("Barista creation test failed!");
            return false;
        }
        
        System.out.println("Successfully created 100 baristas. Now testing 101st...");
        
        try {
            Barista barista101 = new Barista("Barista101", "1234567890", "EMP101", "Morning");
            System.err.println("MAJOR PROBLEM: Should not have been able to create 101st barista!");
            return false;
        } catch (IllegalStateException e) {
            String message = e.getMessage();
            if (message.contains("Cannot create more than") && message.contains("baristas")) {
                System.out.println("Excellent! Barista class correctly rejected 101st instance");
                System.out.println("Exception message: " + message);
                return true;
            } else {
                System.err.println("Wrong exception message: " + message);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Wrong type of exception: " + e.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean testMenuItemInstanceLimit() {
        System.out.println("Now testing MenuItem class instance limit...");
        System.out.println("Creating menu items with various prices and descriptions...");
        
        MenuItem[] menuItems = new MenuItem[MenuItem.MAX_MENU_ITEMS];
        boolean allGood = true;
        String[] categories = {"Coffee", "Tea", "Pastry", "Sandwich", "Salad"};
        
        for (int i = 0; i < MenuItem.MAX_MENU_ITEMS; i++) {
            try {
                String category = categories[i % categories.length];
                String name = category + "Item" + i;
                String description = "Delicious " + category.toLowerCase() + " item number " + i;
                double price = 1.0 + (i * 0.5);
                menuItems[i] = new MenuItem(name, description, price);
                
                if (i % 20 == 0) {
                    System.out.println("Created " + (i + 1) + " menu items so far...");
                }
            } catch (Exception e) {
                System.err.println("Unexpected error creating menu item " + i + ": " + e.getMessage());
                allGood = false;
                break;
            }
        }
        
        if (!allGood) {
            System.out.println("MenuItem creation test failed!");
            return false;
        }
        
        System.out.println("Successfully created 100 menu items. Now testing 101st...");
        
        try {
            MenuItem item101 = new MenuItem("ExtraItem101", "This should not be allowed", 101.0);
            System.err.println("MAJOR PROBLEM: Should not have been able to create 101st menu item!");
            return false;
        } catch (InvalidOrderException e) {
            String message = e.getMessage();
            if (message.contains("menu capacity reached") && message.contains("max")) {
                System.out.println("Fantastic! MenuItem class correctly rejected 101st instance");
                System.out.println("Exception message: " + message);
                return true;
            } else {
                System.err.println("Wrong exception message: " + message);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Wrong type of exception: " + e.getClass().getSimpleName());
            return false;
        }
    }
}