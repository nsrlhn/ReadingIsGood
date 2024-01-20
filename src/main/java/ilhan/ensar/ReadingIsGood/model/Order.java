package ilhan.ensar.ReadingIsGood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ilhan.ensar.ReadingIsGood.controller.request.OrderPostRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Customer customer;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public Order(Customer customer, OrderPostRequest request) {
        super();

        this.customer = customer;
        this.status = request.getStatus();
        this.date = LocalDate.now();
    }

    private Order() {
        super();
    }

    public enum Status {
        PROCESS, COMPLETED, CANCELLED
    }
}
