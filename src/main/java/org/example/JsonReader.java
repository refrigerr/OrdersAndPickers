package org.example;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {
    //creates objectMapper and finds and registers modules, it is needed to recognize different
    //types of variables such as BigDecimal, LocalTime and Duration
    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    //reads list of orders from json file
    public static List<Order> readOrdersFromJsonFile(String pathToFile) {
        File jsonFile = new File(pathToFile);
        try {
            return objectMapper.readValue(jsonFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("Couldn't read a file containing orders");
            return null;
        }
    }
    //reads shop configuration from json file
    public static ShopConfiguration readWorkShiftsFromJsonFile(String pathToFile){
        File jsonFile = new File(pathToFile);
        try {
            return objectMapper.readValue(jsonFile, ShopConfiguration.class);
        } catch (IOException e) {
            System.out.println("Couldn't read a file containing shop configuration");
            return null;
        }
    }
}
