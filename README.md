# Java Beans Cafe - Coffee Shop Management System

## Overview

Java Beans Cafe is a comprehensive coffee shop management system developed for **CS151 – Object-Oriented Design (Fall 2025)**. This application simulates a complete coffee shop operation with an integrated umbrella user interface that seamlessly handles both customer-facing operations and barista workflow management. The system demonstrates advanced object-oriented programming principles including abstraction, inheritance, polymorphism, and robust exception handling.

The application features a unified interface that allows users to switch between **Customer Mode** for menu browsing, order placement, and loyalty point management, and **Barista Mode** for shift management, order completion tracking, and performance monitoring. This dual-mode design provides a realistic simulation of coffee shop operations while showcasing sophisticated software architecture.

## Key Features

- **Dual-Mode Interface**: Seamless switching between Customer and Barista operational modes
- **Customer Management**: Registration, validation, and loyalty point system with automatic point earning
- **Order Processing**: Complete order lifecycle from creation to completion with real-time status tracking
- **Menu Management**: Dynamic menu system with pricing, availability, and discount capabilities
- **Barista Operations**: Clock in/out functionality, shift management, and performance tracking
- **Input Validation**: Comprehensive validation for names, phone numbers, and quantities using regex patterns
- **Instance Limiting**: Resource management with 100-instance limits for all major classes
- **Exception Handling**: Custom exception classes for robust error management
- **Order Tracking**: Real-time order status updates and completion monitoring

## Class Summary

| Class | Purpose | Key Features |
|-------|---------|--------------|
| **Person** | Abstract base class for all persons | Phone validation, instance counting, name management |
| **Customer** | Customer entity extending Person | Loyalty points, validation methods, greeting functionality |
| **Barista** | Barista entity with shift management | Clock in/out, order completion, performance tracking |
| **MenuItem** | Menu item with pricing and availability | Price calculation, discount application, availability control |
| **Order** | Order management and processing | Item addition, total calculation, status tracking |
| **CoffeeShop** | Main application controller | UI management, mode switching, data coordination |
| **Billable** | Interface for purchasable items | Standardized pricing and availability methods |
| **InvalidOrderException** | Custom exception for invalid orders | Order validation error handling |
| **MenuItemNotFoundException** | Custom exception for missing items | Menu item lookup error handling |
| **OrderLimitExceededException** | Custom exception for order limits | Order capacity error handling |

## Directory Structure

```
/Users/apple/Desktop/m1/
├── CS151_UML.png
├── README.md
└── src/
    └── coffeeshop/
        ├── Barista.class
        ├── Barista.java
        ├── Billable.class
        ├── Billable.java
        ├── CoffeeShop.class
        ├── CoffeeShop.java
        ├── Customer.class
        ├── Customer.java
        ├── InstanceLimitTest.class
        ├── InstanceLimitTest.java
        ├── InvalidOrderException.class
        ├── InvalidOrderException.java
        ├── MenuItem.class
        ├── MenuItem.java
        ├── MenuItemNotFoundException.class
        ├── MenuItemNotFoundException.java
        ├── Order.class
        ├── Order.java
        ├── OrderLimitExceededException.class
        ├── OrderLimitExceededException.java
        ├── Person.class
        └── Person.java
```

## Team Contributions

- **Member 1 (Shresthkumar Karnani)**: Implemented Person.java, Customer.java, JUnit tests for instance limits, and integrated comprehensive validation systems including regex-based phone number validation and name validation methods.

- **Member 2(Thomas)**: Built Order.java and MenuItem.java with sophisticated total computation logic, implementing the Billable interface and developing robust pricing calculation methods with discount capabilities.

- **Member 3(Anan)**: Created and handled custom exceptions including InvalidOrderException, MenuItemNotFoundException, and OrderLimitExceededException, establishing comprehensive error handling throughout the application.

- **Member 4(Tanishka Kakkar)**: Implemented Barista.java and unified both barista and customer logic under CoffeeShop.java, creating the umbrella interface that seamlessly integrates both operational modes with intuitive user experience design.

## Design Highlights

### Encapsulation
The system demonstrates strong encapsulation through private fields with controlled access via getter and setter methods. Each class maintains its own state and provides controlled interfaces for interaction, ensuring data integrity and preventing unauthorized access.

### Inheritance
The Person abstract class serves as the foundation for Customer, showcasing proper inheritance hierarchy. The abstract greet() method enforces polymorphic behavior, while shared functionality like name and phone management is efficiently inherited.

### Polymorphism
Polymorphism is demonstrated through the Billable interface implementation by MenuItem, allowing for flexible pricing calculations. The abstract Person class enables polymorphic behavior in customer management, while method overriding provides specialized implementations.

### Exception Handling
The application implements comprehensive exception handling with three custom exception classes, each designed for specific error scenarios. Input validation prevents invalid data entry, while try-catch blocks ensure graceful error recovery throughout the application.

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal

### Compilation
```bash
cd /Users/apple/Desktop/m1
javac src/coffeeshop/*.java
```

### Execution
```bash
java -cp src coffeeshop.CoffeeShop
```

### Running Tests
```bash
java -cp src coffeeshop.InstanceLimitTest
```

## Future Enhancements

- **Persistent Storage**: Integration with database systems for data persistence across application sessions
- **Graphical User Interface**: Modern GUI implementation using JavaFX or Swing for enhanced user experience
- **Analytics Dashboard**: Comprehensive reporting system for sales analysis, customer behavior tracking, and performance metrics
- **Multi-location Support**: Extension to support multiple coffee shop locations with centralized management
- **Payment Integration**: Real payment processing capabilities with multiple payment methods
- **Inventory Management**: Advanced stock tracking and automatic reorder functionality
- **Mobile Application**: Cross-platform mobile app for both customers and baristas

## Summary

This Java Beans Cafe project successfully demonstrates mastery of object-oriented programming principles required for CS151. The application showcases effective use of abstraction through the Person class, inheritance in the Customer hierarchy, polymorphism via the Billable interface, and comprehensive exception handling throughout the system. The unified umbrella interface elegantly manages both customer and barista workflows, while robust validation and instance limiting ensure system reliability. The project exemplifies collaborative software development, clean code architecture, and practical application of computer science principles in a real-world simulation environment.
