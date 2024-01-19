package ilhan.ensar.ReadingIsGood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer availableAmount;
}
