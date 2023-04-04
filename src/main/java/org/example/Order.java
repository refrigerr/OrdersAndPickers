package org.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class Order {
    //order's id
    private String orderId;
    //order's value
    private BigDecimal orderValue;
    //how long it takes to complete order
    private Duration pickingTime;
    //by what time should order be completed
    private LocalTime completeBy;

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public Duration getPickingTime() {
        return pickingTime;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

    public Order() {

    }

    public Order(String orderId, BigDecimal orderValue, Duration pickingTime, LocalTime completeBy) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderId +
                ", orderValue=" + orderValue +
                ", pickingTime=" + pickingTime +
                ", completeBy=" + completeBy +
                '}';
    }

}
