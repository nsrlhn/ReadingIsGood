package ilhan.ensar.ReadingIsGood.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDate date;

    public enum Status {
        PROCESS, COMPLETED, CANCELLED
    }
}
