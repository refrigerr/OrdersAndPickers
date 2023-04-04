import org.example.Algorithm;
import org.example.Order;
import org.example.Picker;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestAlgorithm {
    @Test
    public void testCalculateNumberOfOrdersWithoutOrders_ExceptZero(){
        List<Picker> pickers = new ArrayList<>();
        pickers.add(new Picker("id1", LocalTime.of(9,0)));
        pickers.add(new Picker("id2", LocalTime.of(9,0)));
        assertEquals(0,Algorithm.calculateNumberOfOrders(pickers));
    }
    @Test
    public void testCalculateNumberOfOrdersWithOrders_ExceptSumOfOrders(){
        List<Picker> pickers = new ArrayList<>();
        pickers.add(new Picker("id1", LocalTime.of(9,0)));
        pickers.add(new Picker("id2", LocalTime.of(9,0)));
        pickers.get(0).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(0).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(1).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(1).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(1).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        assertEquals(5,Algorithm.calculateNumberOfOrders(pickers));
    }
    @Test
    public void testCalculateValueOfOrdersWithoutOrders_ExceptZero(){
        List<Picker> pickers = new ArrayList<>();
        pickers.add(new Picker("id1", LocalTime.of(9,0)));
        pickers.add(new Picker("id2", LocalTime.of(9,0)));
        assertEquals(BigDecimal.ZERO,Algorithm.calculateValueOfOrders(pickers));
    }
    @Test
    public void testCalculateValueOfOrdersWithOrders_ExceptSumOfValues(){
        List<Picker> pickers = new ArrayList<>();
        pickers.add(new Picker("id1", LocalTime.of(9,0)));
        pickers.add(new Picker("id2", LocalTime.of(9,0)));
        pickers.get(0).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(0).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(1).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(1).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        pickers.get(1).addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        assertEquals(BigDecimal.valueOf(5),Algorithm.calculateValueOfOrders(pickers));
    }
}
