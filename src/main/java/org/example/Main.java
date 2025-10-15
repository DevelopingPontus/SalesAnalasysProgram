package org.example;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Changeable lists
        List<Product> products = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        HashMap<String, List<Order>> ordersByCustomer = new HashMap<>();

        products.add(new Product(1, "Wireless Headphones", "Electronics", 150));
        products.add(new Product(2, "Yoga Mat", "Fitness", 25));
        products.add(new Product(3, "Electric Kettle", "Electronics", 40));
        products.add(new Product(4, "Running Shoes", "Footwear", 80));
        products.add(new Product(5, "Fiction Book", "Books", 15));
        products.add(new Product(6, "Smartphone Case", "Accessories", 20));
        products.add(new Product(7, "Gaming Mouse", "Electronics", 60));
        products.add(new Product(8, "Blender", "Electronics", 75));
        products.add(new Product(9, "Backpack", "Bags", 45));
        products.add(new Product(10, "LED Desk Lamp", "Electronics", 30));

        orders.add(new Order(101, Arrays.asList(products.get(1), products.get(2)), "Alice Johnson"));
        orders.add(new Order(102, Arrays.asList(products.get(1), products.get(3)), "Bob Smith"));
        orders.add(new Order(103, Arrays.asList(products.get(4), products.get(6)), "Charlie Brown"));
        orders.add(new Order(104, Arrays.asList(products.get(5), products.get(7)), "Diana Prince"));
        orders.add(new Order(105, Arrays.asList(products.get(8), products.get(5), products.get(4)), "Edward Elric"));
        orders.add(new Order(106, Arrays.asList(products.get(0), products.get(8), products.get(2)), "Fiona Green"));
        orders.add(new Order(107, Arrays.asList(products.get(2), products.get(9)), "George Clooney"));
        orders.add(new Order(108, Arrays.asList(products.get(1), products.get(4)), "Hannah Lee"));
        orders.add(new Order(109, Arrays.asList(products.get(3), products.get(5)), "Isaac Newton"));
        orders.add(new Order(110, Arrays.asList(products.get(6), products.get(8)), "Jane Doe"));
        orders.add(new Order(111, Arrays.asList(products.get(2), products.get(1)), "Jane Doe"));
        orders.add(new Order(112, Arrays.asList(products.get(3), products.get(7)), "Jane Doe"));

        //För att datan inte laddats in via fil blir det inte så många try catch i projektet.

        //Stoppar in ordrarna i en map där kundnamnet är nyckeln och ordrarna är värden
        orders.forEach(order -> ordersByCustomer.computeIfAbsent(order.getCustomerName(), k -> new ArrayList<>()).add(order));
        System.out.println(ordersByCustomer);


        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a category for the products you want to see");
        //Electronics can for example be asked for
        //fungerar med stora och små bokstäver
        String wantedCategory = scanner.nextLine().toUpperCase();

        try {
            AnalysingFunctions.filteredAndSortedProducts(products, wantedCategory);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            System.out.println("Enter a valid category");
            wantedCategory = scanner.nextLine();
            AnalysingFunctions.filteredAndSortedProducts(products, wantedCategory);
        }

        System.out.println("Enter a customer name for order history");
        //Jane Doe kan till exempel efterfrågas
        //fungerar med stora och små bokstäver
        String customer = scanner.nextLine().toUpperCase();
        try {
            AnalysingFunctions.customersOrdersValue(orders, customer);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            System.out.println("Enter a valid customer");
            customer = scanner.nextLine();
            AnalysingFunctions.customersOrdersValue(orders, customer);
        }

        AnalysingFunctions.mostBought(orders);

    }
}