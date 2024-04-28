package it.koby.transaction.entities;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import it.koby.transaction.core.entities.Auditable;
import it.koby.transaction.core.users.*;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Transaction extends Auditable {

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private Double amount;

    @CreationTimestamp
    private Date transactionDate;
    
}
