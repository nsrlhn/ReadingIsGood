package ilhan.ensar.ReadingIsGood.controller;

import ilhan.ensar.ReadingIsGood.controller.response.CustomerOrderStatistic;
import ilhan.ensar.ReadingIsGood.model.Customer;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.model.OrderItem;
import ilhan.ensar.ReadingIsGood.service.CustomerService;
import ilhan.ensar.ReadingIsGood.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"statistic"})
@Tag(name = "Statistic")
public class StatisticController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("customerMonthlyOrder/{id}")
    @Operation(description = "Get Customer’s Monthly Order Statistics")
    public Map<YearMonth, CustomerOrderStatistic> getCustomerMonthlyOrder(@PathVariable Long id) {
        // Get
        Customer customer = customerService.getOrThrow(id);
        List<Order> orderList = orderService.getAll(customer);

        // Prepare
        Map<YearMonth, CustomerOrderStatistic> response = new HashMap<>();
        for (Order order : orderList) {
            YearMonth month = YearMonth.from(order.getDate());

            CustomerOrderStatistic statistic;
            if (response.containsKey(month)) {
                statistic = response.get(month);
            } else {
                statistic = new CustomerOrderStatistic(month.getMonth());
                response.put(month, statistic);
            }

            statistic.increaseTotalOrderCount(1);
            statistic.increaseTotalBookCount(order.getOrderItems().stream().map(OrderItem::getAmount).reduce(0, Integer::sum));
            statistic.increaseTotalPurchasedAmount(order.getOrderItems().stream().map(OrderItem::getCost).reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        return response;
    }

}
