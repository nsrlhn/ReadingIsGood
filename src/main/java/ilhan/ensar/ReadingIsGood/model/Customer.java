package ilhan.ensar.ReadingIsGood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;
}
