package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Algorithm {
    //stores global orders
    private final LinkedList<Order> orders;
    //stores global solution for problem
    private List<Picker> bestPickers;
    //stores shop configuration (pickers, startTime and endTime)
    ShopConfiguration shopConfiguration;

    public Algorithm(List<Order> orders, ShopConfiguration shopConfiguration){
        this.orders = new LinkedList<>(orders);
        this.shopConfiguration = shopConfiguration;
        bestPickers = resetPickers();
    }
    //main algorithm
    public void solve(int mode){
        Random random = new Random();
        int x,y;
        //loop of iterations, 2000 is hardcoded, could be any other huge number
        //I chose it because it finds solution for small instances of problem
        //and doesn't exceed 20s for logic-bomb (lats for around 12-13s on my pc)
        for (int i=0;i<2000;i++){
            //local solution
            List<Picker> pickers = resetPickers();
            //copy of global solutions to operate on
            List<Order> orderList = new ArrayList<>(orders);
            //orders to remove assigned orders
            List<Order> toRemove = new ArrayList<>();
            for (Picker picker: pickers){
                for (Order order: orderList){
                    if(!picker.availableAt().plus(order.getPickingTime()).isAfter(order.getCompleteBy())
                            && !picker.availableAt().plus(order.getPickingTime()).isAfter(shopConfiguration.getPickingEndTime())){
                        //add order to picker
                        picker.addOrder(order);
                        //assign order to be removed
                        toRemove.add(order);
                    }
                }
                //remove all assigned orders
                orderList.removeAll(toRemove);
                //clear orders to be removed
                toRemove.clear();
            }
            //for mode 0 solve for number of orders
            if(mode==0){
                if (calculateNumberOfOrders(pickers)>calculateNumberOfOrders(bestPickers)){
                    bestPickers = new ArrayList<>(pickers);
                }
            }else{ //for any other mode solve for collective value of orders
                if (calculateValueOfOrders(pickers).compareTo(calculateValueOfOrders(bestPickers))>0){
                    bestPickers = new ArrayList<>(pickers);
                }
            }
            //swap 2 random orders in List to generate new instance
            do {
                x = random.nextInt(orders.size());
                y = random.nextInt(orders.size());
            }while (x==y);
            Order orderX = orders.get(x);
            Order orderY = orders.get(y);
            orders.set(x,orderY);
            orders.set(y,orderX);
        }
    }

    //creates new list of pickers without assigned orders
    private List<Picker> resetPickers(){
        List<Picker> pickers = new ArrayList<>();
        for (String string: shopConfiguration.getPickers()){
            pickers.add(new Picker(string, shopConfiguration.getPickingStartTime()));
        }
        return pickers;
    }
    //calculates total number of assigned orders
    //static for tests
    public static int calculateNumberOfOrders(List<Picker> pickers){
        int sum = 0;
        for (Picker picker: pickers){
            sum += picker.getOrders().size();
        }
        return sum;
    }
    //calculates total value of assigned orders
    //static for tests
    public static BigDecimal calculateValueOfOrders(List<Picker> pickers){
        BigDecimal sum = BigDecimal.ZERO;
        for (Picker picker: pickers){
           sum = sum.add(picker.getTotalValue());
        }
        return sum;
    }
    public List<Order> getOrders() {
        return orders;
    }
    //displays result of algorithm
    public void displayResult(){
        for (Picker picker:bestPickers){
            picker.printPicker();
        }
    }

    public List<Picker> getBestPickers() {
        return bestPickers;
    }
}
