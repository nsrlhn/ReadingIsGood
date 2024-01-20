package ilhan.ensar.ReadingIsGood.model;

import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private Integer availableAmount;

    @Column(nullable = false)
    private BigDecimal price;

    public Book(BookPostRequest request) {
        super();

        name = request.getName();
        availableAmount = request.getAvailableAmount();
        price = request.getPrice();
    }

    private Book() {
        super();
    }
}
