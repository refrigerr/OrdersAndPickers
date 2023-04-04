import org.example.Order;
import org.example.Picker;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class TestPicker {
    Picker picker = new Picker("pickerId", LocalTime.of(9,0));

    @Test
    public void testAddOrderOneOrder_ExceptOneMoreOrder(){
        int before = picker.getOrders().size();
        picker.addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        int after = picker.getOrders().size();
        assertEquals(before+1,after);
    }
    @Test
    public void testAddOrderMultipleOrders_ExceptMultipleMoreOrders(){
        int before = picker.getOrders().size();
        int ordersToAdd = 5;
        for (int i =0;i<ordersToAdd;i++)
            picker.addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        int after = picker.getOrders().size();
        assertEquals(before+ordersToAdd,after);
    }
    @Test
    public void testAvailableAtWithoutOrders_ExceptStartOfShift(){
        assertEquals(picker.getStart(),picker.availableAt());
    }
    @Test
    public void testAvailableAtWithOrders_ExceptsSumOfPickingTime(){
        for (int i =0;i<5;i++)
            picker.addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        assertEquals(picker.getStart().plus(Duration.ofMinutes(5)), picker.availableAt());

    }
    @Test
    public void testGetTotalValueWithoutOrders_ExceptZero(){
        assertEquals(BigDecimal.ZERO,picker.getTotalValue());

    }
    @Test
    public void testGetTotalValueWithOrders_ExceptSumOfValues(){
        for (int i =0;i<5;i++)
            picker.addOrder(new Order("orderId", BigDecimal.ONE, Duration.ofMinutes(1),LocalTime.of(9,15)));
        assertEquals(BigDecimal.valueOf(5), picker.getTotalValue());

    }

}
