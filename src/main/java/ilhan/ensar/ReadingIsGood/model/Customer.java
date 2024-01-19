package ilhan.ensar.ReadingIsGood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String familyName;
}
