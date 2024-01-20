package ilhan.ensar.ReadingIsGood.controller.response;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Month;

@Getter
public class CustomerOrderStatistic implements Serializable {

    private final Month month;
    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalPurchasedAmount;

    public CustomerOrderStatistic(Month month) {
        this.month = month;
        this.totalOrderCount = 0;
        this.totalBookCount = 0;
        this.totalPurchasedAmount = BigDecimal.ZERO;
    }

    public void increaseTotalOrderCount(int increment) {
        totalOrderCount += increment;
    }

    public void increaseTotalBookCount(int increment) {
        totalBookCount += increment;
    }

    public void increaseTotalPurchasedAmount(BigDecimal increment) {
        this.totalPurchasedAmount = this.totalPurchasedAmount.add(increment);
    }
}
