package ilhan.ensar.ReadingIsGood.model;

import ilhan.ensar.ReadingIsGood.controller.request.BookPostRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer availableAmount;

    public Book(BookPostRequest request) {
        super();

        name = request.getName();
        availableAmount = request.getAvailableAmount();
    }

    private Book() {
        super();
    }
}
