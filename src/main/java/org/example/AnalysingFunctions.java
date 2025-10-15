package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysingFunctions {
    static Logger logger = LoggerFactory.getLogger(AnalysingFunctions.class);


    public static void filteredAndSortedProducts(List<Product> products, String wantedCategory) {
        logger.info("Starts filtering and sorting products");
        if (wantedCategory.equals("")) {
            throw new InvalidRequestException("No wanted Category is entered. Try again");
        }
        // Kollar om kategorin finns
        // Om inte, kastar en exception
        if (products.stream().noneMatch(p -> p.getCategory().toUpperCase().equals(wantedCategory))) {
            throw new InvalidRequestException("Wanted Category is not a valid category. Try again");
        }
        System.out.println("Filtered and sorted products: " + wantedCategory);
        // Streamar,
        // filtrerar efter kategori,
        // sorterar stigande efter varje produkts pris,
        // konverterar till en lista.
        products.stream()
                .filter(p -> p.getCategory().toUpperCase().equals(wantedCategory))
                .sorted(Comparator.comparingInt(Product::getPrice))
                .toList()
                .forEach(product -> System.out.println(product.getName() + ": " + product.getPrice()));
    }



    public static void customersOrdersValue(List<Order> orders, String customer) {
        logger.info("Starts calculating customers orders value");
        if(customer.equals("")){
            throw new InvalidRequestException("No wanted customer is entered. Try again");
        }
        // Kollar om kunden finns
        // Om inte, kastar en exception
        if (orders.stream().noneMatch(order -> order.getCustomerName().toUpperCase().equals(customer))) {
            throw new InvalidRequestException("Wanted customer is not a valid customer. Try again");
        }
        // Streamar ordrar,
        // filtrerar efter kundnamn,
        // mappar varje produkt i varje order till summerat order pris,
        // summerar alla priser.
        System.out.println("Total value of " + customer + "s orders : " +
                orders.stream()
                        .filter(order -> order.getCustomerName().toUpperCase().equals(customer))
                        .mapToInt(order -> order.getProducts().stream().mapToInt(Product::getPrice).sum())
                        .sum());
    }


    //Streamar ordrar,
    //formaterar streamen till stream av köpta produkter,
    //samlar och konverterar till map där produktens antal ökar med en varje gång den förekommer,
    //streamar varje entrySet
    //sorterar varje entrySet efter des värde,
    //tar de tre första
    public static void mostBought(List<Order> orders) {

        logger.info("Starts finding the most bought products");
        System.out.println("The most bought products: " +
                orders.stream()
                        .flatMap(order -> order.getProducts().stream())
                        .collect(Collectors.toMap(Product::getName, product -> 1, Integer::sum))
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(3)
                        .toList());
    }
}
