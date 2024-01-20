package ilhan.ensar.ReadingIsGood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ilhan.ensar.ReadingIsGood.controller.request.OrderItemPostRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    @Column(nullable = false)
    private Integer amount;

    public OrderItem(Order order, Book book, OrderItemPostRequest request) {
        super();

        this.order = order;
        this.book = book;
        this.amount = request.getAmount();
    }

    private OrderItem() {
        super();
    }
}
