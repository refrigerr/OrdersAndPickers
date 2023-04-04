import org.example.Order;
import org.example.Utils;
import org.junit.Test;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
public class TestUtils {

    @Test
    public void testSortOrders_ExceptSortedList(){
        Order order1= new  Order("orderId1", BigDecimal.ONE, Duration.ofMinutes(1), LocalTime.of(10,15));
        Order order2= new  Order("orderId2", BigDecimal.ONE, Duration.ofMinutes(4), LocalTime.of(7,15));
        Order order3= new  Order("orderId3", BigDecimal.ONE, Duration.ofMinutes(2), LocalTime.of(6,45));
        Order order4= new  Order("orderId4", BigDecimal.ONE, Duration.ofMinutes(3), LocalTime.of(6,45));
        Order order5= new  Order("orderId5", BigDecimal.ONE, Duration.ofMinutes(1), LocalTime.of(7,15));
        Order order6= new  Order("orderId6", BigDecimal.ONE, Duration.ofMinutes(1), LocalTime.of(6,45));
        Order order7= new  Order("orderId7", BigDecimal.ONE, Duration.ofMinutes(1), LocalTime.of(6,55));

        List<Order> ordersToSort = new ArrayList<>();
        ordersToSort.add(order1);
        ordersToSort.add(order2);
        ordersToSort.add(order3);
        ordersToSort.add(order4);
        ordersToSort.add(order5);
        ordersToSort.add(order6);
        ordersToSort.add(order7);


        List<Order> sortedOrders = new ArrayList<>();
        sortedOrders.add(order6);
        sortedOrders.add(order3);
        sortedOrders.add(order4);
        sortedOrders.add(order7);
        sortedOrders.add(order5);
        sortedOrders.add(order2);
        sortedOrders.add(order1);



        assertThat(Utils.sortOrders(ordersToSort)).containsExactlyElementsOf(sortedOrders);

    }
}
