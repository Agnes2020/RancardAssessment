package it.koby.transaction.core.users;

import it.koby.transaction.core.entities.Auditable;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class User extends Auditable {

    private String name;
    private String surname;
    private Double balance;
}
