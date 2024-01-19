package ilhan.ensar.ReadingIsGood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

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

    public enum Status {
        PROCESS, COMPLETED, CANCELLED
    }
}
