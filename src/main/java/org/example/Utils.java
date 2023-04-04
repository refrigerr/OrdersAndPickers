package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Utils {

    //sorts orders firstly by time they should be completed, then by how long it takes to complete them
    //in most cases it ensures that almost a maximum number of orders can be completed
    public static List<Order> sortOrders(List<Order> orders){
        Comparator<Order> comparator = Comparator.comparing(Order::getCompleteBy);
        comparator = comparator.thenComparing(Order::getPickingTime);
        Stream<Order> orderStream = orders.stream().sorted(comparator);
        return orderStream.toList();
    }

}
