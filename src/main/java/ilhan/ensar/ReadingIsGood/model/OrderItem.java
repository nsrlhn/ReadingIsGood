package ilhan.ensar.ReadingIsGood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    @Column(nullable = false)
    private Integer amount;
}
