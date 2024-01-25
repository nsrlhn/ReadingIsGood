package ilhan.ensar.ReadingIsGood.model;

import ilhan.ensar.ReadingIsGood.model.listener.EntityListener;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@EntityListeners(EntityListener.class)
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String toString() {
        return this.getClass().getSimpleName() + "(id=" + this.getId() + ")";
    }
}
