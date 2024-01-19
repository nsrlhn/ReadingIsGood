package ilhan.ensar.ReadingIsGood.model;

import ilhan.ensar.ReadingIsGood.controller.request.CustomerPostRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String familyName;

    public Customer(CustomerPostRequest request) {
        super();

        this.firstName = request.getFirstName();
        this.middleName = request.getMiddleName() == null ? "" : request.getMiddleName();
        this.familyName = request.getFamilyName();
    }

    private Customer() {
        super();
    }
}
