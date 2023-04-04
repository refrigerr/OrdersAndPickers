package org.example;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.LinkedList;


public class Picker {
    //picker's id
    private final String pickerId;
    //list of picker's orders and time each order should be picked up
    private final LinkedList<OrderAndPickingTime> orders;
    //start of a shift
    private final LocalTime start;

    public Picker(String pickerId, LocalTime start){
        this.pickerId = pickerId;
        orders = new LinkedList<>();
        this.start = start;
    }

    public LinkedList<OrderAndPickingTime> getOrders() {
        return orders;
    }
    //adds order to list, if it is first order, then it's pick up time is start of a shift
    //if it is not a first order, then it's pick up time is sum of previous order pick up time and previous order duration of picking up
    public void addOrder(Order order){
        orders.add(new OrderAndPickingTime(order, orders.size()==0?start:orders.peekLast().getStartPickingTime().plus(orders.peekLast().getOrder().getPickingTime())));
    }
    //returns at what time picker is available for next order (last taken order pick up time plus it's duration)
    public LocalTime availableAt(){
        if (orders.size()==0)
            return start;
        return orders.peekLast().getStartPickingTime().plus(orders.peekLast().getOrder().getPickingTime());
    }

    //prints picker id and all of their orders
    public void printPicker(){
        for (OrderAndPickingTime orderAndPickingTime: orders){
            System.out.println(pickerId+" "+orderAndPickingTime.getOrder().getOrderId()+" "+orderAndPickingTime.getStartPickingTime());
        }
    }
    //class that stores order and order's pick up time
    private class OrderAndPickingTime{
        private final Order order;
        private final LocalTime startPickingTime;

        public OrderAndPickingTime(Order order, LocalTime startPickingTime) {
            this.order = order;
            this.startPickingTime = startPickingTime;
        }

        public Order getOrder() {
            return order;
        }

        public LocalTime getStartPickingTime() {
            return startPickingTime;
        }
    }
    //return total value of picker's orders
    public BigDecimal getTotalValue(){
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderAndPickingTime order: orders){
            sum = sum.add(order.getOrder().getOrderValue());
        }
        return sum;
    }

    public LocalTime getStart() {
        return start;
    }

    @Override
    public String toString() {
        return "Picker{" +
                "pickerId='" + pickerId + '\'' +
                ", orders=" + orders +
                '}';
    }
}
