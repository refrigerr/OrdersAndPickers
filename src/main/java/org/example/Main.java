package org.example;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        //read data
        ShopConfiguration shopConfiguration = JsonReader.readWorkShiftsFromJsonFile(args[0]);
        List<Order> orders = JsonReader.readOrdersFromJsonFile(args[1]);
        //checks if data is ok
        if (orders == null || shopConfiguration == null)
            System.exit(1);
        if(shopConfiguration.getPickers().size()>50){
            System.out.println("Too much pickers");
            System.exit(1);
        }
        //creates Algorithm instance and passes
        Algorithm algorithm = new Algorithm(Utils.sortOrders(orders),shopConfiguration);
        int mode = 0; // mode = 0 solves problem from exercise 1, any other value assigned
        // to mode solves problem from exercise 2
        // mode number is not passed as argument because it is not specified in exercise
        algorithm.solve(mode);
        algorithm.displayResult();

    }
}